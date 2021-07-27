package by.factory_accounting.service;

import by.factory_accounting.entity.accounting.ReceiptOrderOfGoods;
import by.factory_accounting.repository.ProductRepository;
import by.factory_accounting.repository.ReceiptOrderOfGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptOrderOfGoodsService {

    @Autowired
   ReceiptOrderOfGoodsRepository receiptOrderOfGoodsRepository;
    @Autowired
   ProductRepository productRepository;

    public void create(ReceiptOrderOfGoods receiptOrderOfGoods){
        receiptOrderOfGoodsRepository.save(receiptOrderOfGoods);
    }
}
