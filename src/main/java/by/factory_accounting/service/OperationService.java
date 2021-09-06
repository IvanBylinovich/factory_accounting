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

    public Optional<Operation> findByOperationName(String name) {
        return operationRepository.findByName(name);
    }

    public void createOperation(Operation operation) {
        operationRepository.save(operation);
    }

    public boolean isExists(String name) {
        return operationRepository.existsByName(name);
    }

    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }

    public List<Operation> getAll() {
        return operationRepository.findAll();
    }

    //выполнение операции, расходует товар-сырье(списывая его из приходов), производит другой товар расчитывая его стоимость, записывая его через новые приходы.
    public boolean performOperation(Operation operation, BigDecimal quantity) {

        //лист для приходов которые будут расходываться при производстве
        List<ReceiptOrder> receiptOrders = new ArrayList<>();
        List<BigDecimal> pricesFromOrders = new ArrayList<>();
        BigDecimal amountMaterialConsumption = new BigDecimal(0);
        BigDecimal performGoodsPrice = new BigDecimal(0);


        //вычисляем количество затрачиваемыч товар для указанного количества операций(перемножаем повторение на рассход)
        amountMaterialConsumption = amountMaterialConsumption.add(quantity).multiply(operation.getRequiredQuantityForProduction());

        System.out.println(amountMaterialConsumption + "вычисляем количество затрачиваемыч товар для указанного количества операций(перемножаем повторение на рассход");

        System.out.println(receiptOrderRepository.sumQuantity(operation.getSpentProduct()) + "вычисляем количество затрачиваемыч товар для указанного количества операций(перемножаем повторение на рассход");

        //проверяем хватает ли сырья
        if (amountMaterialConsumption.compareTo(receiptOrderRepository.sumQuantity(operation.getSpentProduct())) <= 0) {
        } else return false;

        receiptOrders.addAll(receiptOrderRepository.findAllByProductOrderByPriseAsc(operation.getSpentProduct()));

        //списывается с приходов затраченные материалы
        for (ReceiptOrder order : receiptOrders) {
            if (order.getQuantity().compareTo(amountMaterialConsumption) == 0) {
                amountMaterialConsumption = new BigDecimal(0);
                order.setQuantity(new BigDecimal(0));

                pricesFromOrders.add(order.getPrise());

            } else if (order.getQuantity().compareTo(amountMaterialConsumption) < 0) {
                amountMaterialConsumption = amountMaterialConsumption.subtract(order.getQuantity());
                order.setQuantity(new BigDecimal(0));

                pricesFromOrders.add(order.getPrise());

            } else if (order.getQuantity().compareTo(amountMaterialConsumption) > 0) {
                order.setQuantity(order.getQuantity().subtract(amountMaterialConsumption));
                amountMaterialConsumption = new BigDecimal(0);

                pricesFromOrders.add(order.getPrise());
            }
        }
        //сохраняем в базу список с изменненми приходами
        editReceiptOrders(receiptOrders);

        //выщитывем сумму цен из приходов для вычисления средней цены потраченных продуктов
        for (BigDecimal price : pricesFromOrders) {
            performGoodsPrice = performGoodsPrice.add(price);
        }
        System.out.println("=============================================");
        System.out.println(new BigDecimal(pricesFromOrders.size()) + "ордера");
        System.out.println("цена произведенный товар");
        System.out.println(performGoodsPrice.divide(new BigDecimal(pricesFromOrders.size())) + "цена произведенный товар");
        System.out.println("=============================================");


        performGoodsPrice = performGoodsPrice.divide(new BigDecimal(pricesFromOrders.size()));

        //выщитывем цену произведенных продуктов
        performGoodsPrice = performGoodsPrice.multiply(operation.getRequiredQuantityForProduction());
        performGoodsPrice = performGoodsPrice.add(operation.getPayment());

//        System.out.println("=============================================");
//        System.out.println(operation.getManufacturedProduct() + "произведенный товар");
//        System.out.println(quantity + "количество произведенного товара");
//        System.out.println(performGoodsPrice + "цена");
//        System.out.println("=============================================");


        //сохраняем произведенный товар в базе посредство сохранения прихода
        ReceiptOrder receiptOrder = new ReceiptOrder(operation.getManufacturedProduct(), quantity, performGoodsPrice);
        receiptOrderRepository.save(receiptOrder);


        return true;

    }

    public List<ReceiptOrder> editReceiptOrders(List<ReceiptOrder> receiptOrders) {
        return receiptOrderRepository.saveAll(receiptOrders);
    }


}
