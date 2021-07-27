package by.factory_accounting.controller;

import by.factory_accounting.entity.accounting.ReceiptOrderOfGoods;
import by.factory_accounting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequestMapping("/receiptOrderOfGoods")
public class ReceiptOrderOfGoodsController {

    @Autowired
    ProductService productService;


    @GetMapping("/create")
    public ModelAndView creation(Model model){
        model.addAttribute("receiptOrderOfGoods", new ReceiptOrderOfGoods());
        model.addAttribute("products", productService.getAllProduct());
        return  new ModelAndView("creationReceiptOrderOfGoods");
    }

    @GetMapping("/filter")
    public ModelAndView showProductFilter(Model model){
        model.addAttribute("receiptOrdersOfGoods", productService.getAllProduct());
        return new ModelAndView("filterReceiptOrderOfGoods");
    }
}
