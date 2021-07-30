package by.factory_accounting.repository;

import by.factory_accounting.entity.accounting.Operation;
import by.factory_accounting.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    boolean existsByName(String string);
}
