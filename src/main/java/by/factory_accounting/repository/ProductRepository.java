package by.factory_accounting.repository;

import by.factory_accounting.entity.accounting.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);
    boolean existsByName(String name);

}
