package by.factory_accounting.service;

import by.factory_accounting.entity.accounting.Operation;
import by.factory_accounting.entity.accounting.ReceiptOrder;
import by.factory_accounting.repository.OperationRepository;
import by.factory_accounting.repository.ProductRepository;
import by.factory_accounting.repository.ReceiptOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OperationService {

    private final Logger logger = Logger.getLogger("OperationServiceLogger");


    private final OperationRepository operationRepository;
    private final ProductRepository productRepository;
    private final WorkerService workerService;
    private final ReceiptOrderRepository receiptOrderRepository;
    private final ReceiptOrderService receiptOrderService;

    @Autowired
    public OperationService(OperationRepository operationRepository, ProductRepository productRepository, WorkerService workerService, ReceiptOrderRepository receiptOrderRepository, ReceiptOrderService receiptOrderService) {
        this.operationRepository = operationRepository;
        this.productRepository = productRepository;
        this.workerService = workerService;
        this.receiptOrderRepository = receiptOrderRepository;
        this.receiptOrderService = receiptOrderService;
    }

    public Optional<Operation> findByProductName(String name){
        return operationRepository.findByName(name);
    }

    public void createOperation(Operation operation){
       operationRepository.save(operation);
    }

    public boolean isExists(String name){
        return operationRepository.existsByName(name);
    }

    public Operation save(Operation operation){
       return operationRepository.save(operation);
    }

    public List<Operation> getAll(){
        return operationRepository.findAll();
    }

    //выполнение операции, расходует расходуемый товар(списывая его из приходов), производит другой товар расчитывая его стоимость, записывая его через новые приходы.
    public boolean performOperation(Operation operation, BigDecimal quantity){

        //лист для приходов которые будут расходываться при производстве
        List<ReceiptOrder> receiptOrderQueue = new ArrayList<>();
        List<BigDecimal> pricesFromOrders = new ArrayList<>();
        BigDecimal amountMaterialConsumption = new BigDecimal(0);
        BigDecimal newPrice = new BigDecimal(0);
        BigDecimal newQuantity;
        BigDecimal workerPayment;

        //вычисляем количество затрачиваемыч товар для указанного количества операций(перемножаем повторение на рассход)
        amountMaterialConsumption = amountMaterialConsumption.add(quantity).multiply(operation.getRequiredQuantityForProduction());


        System.out.println(amountMaterialConsumption + "вычисляем количество затрачиваемыч товар для указанного количества операций(перемножаем повторение на рассход)");



        for (ReceiptOrder order : receiptOrderRepository.findAllByProductAndQuantityIsNotNullOrderByPriseAsc(operation.getSpentProduct())){
            if(amountMaterialConsumption.compareTo(new BigDecimal(0)) > 0 ){
                receiptOrderQueue.add(order);
                amountMaterialConsumption =  amountMaterialConsumption.subtract(order.getQuantity());
            }else break; //означает что затрачеваемого товара хватила

            return false;// не хватило
        }
        logger.info(receiptOrderQueue.toString());



        System.out.println(receiptOrderQueue.toString());



        //повторяю все то же свамок тк BigDecimal инмьютебал
        BigDecimal amountMaterialConsumption2 = new BigDecimal(0);

        System.out.println(amountMaterialConsumption2 + "повторяю все то же свамок тк BigDecimal инмьютебал");


        //вычисляем количество затрачиваемыч товар для указанного количества операций(перемножаем повторение на рассход)
        amountMaterialConsumption2 = amountMaterialConsumption.add(quantity).multiply(operation.getRequiredQuantityForProduction());

        System.out.println(amountMaterialConsumption2 + "повторяю все то же свамок тк BigDecimal инмьютебал");

        //списывается с приходов затраченные материалы
        for (ReceiptOrder order : receiptOrderQueue){
            if(order.getQuantity().compareTo(amountMaterialConsumption2) == 0){
                amountMaterialConsumption2 = new BigDecimal(0);
                order.setQuantity(new BigDecimal(0));

                pricesFromOrders.add(order.getPrise());

            }else if(order.getQuantity().compareTo(amountMaterialConsumption2) < 0){
                amountMaterialConsumption2 = amountMaterialConsumption2.subtract(order.getQuantity());
                order.setQuantity(new BigDecimal(0));

                pricesFromOrders.add(order.getPrise());

            }else if(order.getQuantity().compareTo(amountMaterialConsumption2) > 0){
                order.setQuantity(order.getQuantity().subtract(amountMaterialConsumption2));
                amountMaterialConsumption2 = new BigDecimal(0);

                pricesFromOrders.add(order.getPrise());
            }
        }

        //выщитывем среднюю цену потраченных продуктов
        for(BigDecimal price : pricesFromOrders){
            newPrice = newPrice.add(price);
        }

        System.out.println(newPrice + "выщитывем среднюю цену потраченных продуктов");


        //выщитываем зарплату рабочему
        workerPayment = quantity.multiply(operation.getPayment());

        System.out.println(workerPayment + "зарплату рабочему");

        newPrice = newPrice.divide(new BigDecimal(
                pricesFromOrders.size()))
                .add(operation.getPayment())
                .add(workerPayment);

        //выщитываем количество произведенного товара
        newQuantity = quantity.multiply(operation.getRequiredQuantityForProduction());

        System.out.println(newQuantity + "количество произведенного товара");


        //сохраняем произведенный товар в базе посредство сохранения прихода
        receiptOrderService.create(new ReceiptOrder(
                operation.getManufacturedProduct(),
                newQuantity,
                newPrice));

        return true;
    }



}
