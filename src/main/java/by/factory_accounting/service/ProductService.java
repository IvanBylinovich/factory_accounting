package by.factory_accounting.service;

import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void receipt(List<Product> productList) {
        productRepository.saveAll(productList);
    }

    public Optional<Product> findByName(String name) {
        return Optional.ofNullable(productRepository.findByName(name));
    }

    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
}
