package by.factory_accounting.service;

import by.factory_accounting.entity.accounting.ReceiptOrder;
import by.factory_accounting.repository.ReceiptOrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReceiptOrderServiceTest {

    @Autowired
    ReceiptOrderService receiptOrderService;
    @MockBean
    ReceiptOrderRepository receiptOrderRepository;

    @Test
    void createTrueRes() {
        ReceiptOrder receiptOrder = new ReceiptOrder();
        boolean isReceiptOrderCreated = receiptOrderService.create(receiptOrder);
        Assert.assertTrue(isReceiptOrderCreated);
        Mockito.verify(receiptOrderRepository, Mockito.times(1)).save(receiptOrder);
    }

    @Test
    void getAllReceiptOrder() {
    }
}