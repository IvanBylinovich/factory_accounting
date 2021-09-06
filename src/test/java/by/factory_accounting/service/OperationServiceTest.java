package by.factory_accounting.service;

import by.factory_accounting.entity.accounting.Operation;
import by.factory_accounting.repository.OperationRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
class OperationServiceTest {
    @Autowired
    private OperationService operationService;
    @Mock
    private OperationRepository operationRepository;


    @Test
    void findByOperationName() {

    }

    @Test
    void createOperation() {
    }

    @Test
    void isExists() {
    }

    @Test
    void save() {
    }

    @Test
    void getAll() {
    }

    @Test
    void performOperation() {
    }

    @Test
    void editReceiptOrders() {
    }
}