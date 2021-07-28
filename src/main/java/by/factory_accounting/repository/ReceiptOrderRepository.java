package by.factory_accounting.repository;

import by.factory_accounting.entity.accounting.ReceiptOrderOfGoods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptOrderRepository extends JpaRepository<ReceiptOrderOfGoods, Long> {
}
