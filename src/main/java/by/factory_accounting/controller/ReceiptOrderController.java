package by.factory_accounting.controller;

import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.entity.accounting.ReceiptOrderOfGoods;
import by.factory_accounting.service.ProductService;
import by.factory_accounting.service.ReceiptOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequestMapping("/receiptOrder")
public class ReceiptOrderController {

    @Autowired
    ProductService productService;
    @Autowired
    ReceiptOrderService receiptOrderService;

    @GetMapping("/create")
    public ModelAndView creation(Model model){
        model.addAttribute("receiptOrderOfGoods", new ReceiptOrderOfGoods());
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.getAllProduct());
        return  new ModelAndView("creationReceiptOrder");
    }
    @PostMapping("/create")
    public ModelAndView creation(ReceiptOrderOfGoods receiptOrder, Product product, Model model) {

        receiptOrder.setProduct(productService.findByName(product.getName()));

        if (receiptOrderService.create(receiptOrder)) {
            model.addAttribute("message", "Receipt order created successfully");
            return new ModelAndView("creationReceiptOrder");
        }
        model.addAttribute("message", "Receipt order created successfully");
        return new ModelAndView("creationReceiptOrder");
    }

    @GetMapping("/filter")
    public ModelAndView showProductFilter(Model model){
        model.addAttribute("receiptOrders", productService.getAllProduct());
        return new ModelAndView("filterReceiptOrder");
    }
}
