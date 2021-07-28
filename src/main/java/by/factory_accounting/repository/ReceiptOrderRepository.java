package by.factory_accounting.repository;

import by.factory_accounting.entity.accounting.ReceiptOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptOrderRepository extends JpaRepository<ReceiptOrder, Long> {

}
