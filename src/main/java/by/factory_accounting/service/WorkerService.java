package by.factory_accounting.service;

import by.factory_accounting.entity.accounting.Worker;
import by.factory_accounting.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    public void create(Worker worker) {
        workerRepository.save(worker);
    }

    public Optional<Worker> getById(long id) {
        return workerRepository.findById(id);
    }

    public List<Worker> getAllWorker() {
        return workerRepository.findAll();
    }

    public boolean existsByName(String name) {
        return workerRepository.existsByName(name);
    }

    public Optional<Worker> findByName(String name) {
        return workerRepository.findByName(name);
    }
}
