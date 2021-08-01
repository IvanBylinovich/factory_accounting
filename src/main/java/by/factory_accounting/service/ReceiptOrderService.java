package by.factory_accounting.service;

import by.factory_accounting.entity.accounting.ReceiptOrder;
import by.factory_accounting.repository.ProductRepository;
import by.factory_accounting.repository.ReceiptOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptOrderService {

    @Autowired
    ReceiptOrderRepository orderRepository;
    @Autowired
   ProductRepository productRepository;

    public boolean create(ReceiptOrder order){

            orderRepository.save(order);
            return true;

    }
    public List<ReceiptOrder> getAllReceiptOrder(){
        return orderRepository.findAll();
    }
}
