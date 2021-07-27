package by.factory_accounting.service;

import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void create (Product product){
        productRepository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void receipt(List<Product> productList){
        productRepository.saveAll(productList);
    }
}
