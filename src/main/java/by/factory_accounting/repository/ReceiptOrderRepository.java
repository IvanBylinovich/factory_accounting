package by.factory_accounting.repository;

import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.entity.accounting.ReceiptOrder;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Queue;

public interface ReceiptOrderRepository extends JpaRepository<ReceiptOrder, Long> {

    List<ReceiptOrder> findAllByProductOrderByPriseAsc(Product product);

    @Query("SELECT SUM(p.quantity) FROM ReceiptOrder p WHERE p.product = ?1 ")
    BigDecimal sumQuantity(Product product);
}
