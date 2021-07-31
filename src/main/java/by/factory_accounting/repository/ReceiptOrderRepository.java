package by.factory_accounting.repository;

import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.entity.accounting.ReceiptOrder;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Queue;

public interface ReceiptOrderRepository extends JpaRepository<ReceiptOrder, Long> {

    List<ReceiptOrder> findAllByProductAndQuantityIsNotNullOrderByPriseAsc(Product product);
}
