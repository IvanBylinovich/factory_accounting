package by.factory_accounting.service;

import by.factory_accounting.entity.accounting.Operation;
import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.repository.OperationRepository;
import by.factory_accounting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationService {

    private final OperationRepository userRepository;
    private final ProductRepository productRepository;
    private final WorkerService workerService;

    @Autowired
    public OperationService(OperationRepository userRepository, ProductRepository productRepository, WorkerService workerService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.workerService = workerService;
    }

//    public void createOperation(Operation operation){
//        List<Product> allProduct = new ArrayList<>();
//        allProduct.addAll(operation.getSpentProducts());
//        allProduct.addAll(operation.getManufacturedProducts());
//        productRepository.saveAll(allProduct);
//
//
//    }
}
