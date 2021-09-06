package by.factory_accounting.repository;

import by.factory_accounting.entity.accounting.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    boolean existsByName(String string);

    Optional<Operation> findByName(String name);
}
