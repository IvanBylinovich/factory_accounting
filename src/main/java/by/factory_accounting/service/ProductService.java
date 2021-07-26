package by.factory_accounting.service;

import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void create (Product product){
        productRepository.save(product);
    }
}
