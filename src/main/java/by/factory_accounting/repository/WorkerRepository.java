package by.factory_accounting.repository;

import by.factory_accounting.entity.accounting.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    boolean existsByName(String name);
     Optional<Worker> findByName(String name);
}
