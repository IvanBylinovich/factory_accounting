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

    private final OperationRepository operationRepository;
    private final ProductRepository productRepository;
    private final WorkerService workerService;

    @Autowired
    public OperationService(OperationRepository operationRepository, ProductRepository productRepository, WorkerService workerService) {
        this.operationRepository = operationRepository;
        this.productRepository = productRepository;
        this.workerService = workerService;
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
}
