package by.factory_accounting.controller;

import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.entity.accounting.ReceiptOrder;
import by.factory_accounting.entity.dto.ReceiptOrderDTO;
import by.factory_accounting.service.ProductService;
import by.factory_accounting.service.ReceiptOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
@RequestMapping("/receiptOrder")
public class ReceiptOrderController {

    @Autowired
    ProductService productService;
    @Autowired
    ReceiptOrderService receiptOrderService;

    @GetMapping("/create")
    public ModelAndView creation(Model model){
        model.addAttribute("receiptOrderDTO", new ReceiptOrderDTO());
        return  new ModelAndView("creationReceiptOrder");
    }
    @PostMapping("/create")
    public ModelAndView creation(ReceiptOrderDTO receiptOrderDTO, Model model) {
        Optional<Product> foundProduct = productService.findByName(receiptOrderDTO.getProductName());
        ReceiptOrder receiptOrder = new ReceiptOrder();

        if(foundProduct.isPresent()){
            receiptOrder.setProduct(foundProduct.get());
        }else {
            receiptOrder.setProduct(productService.create(new Product(receiptOrderDTO.getProductName(), receiptOrderDTO.getUnit())));
        }
        receiptOrder.setPrise(receiptOrderDTO.getPrice());
        receiptOrder.setQuantity(receiptOrderDTO.getQuantity());


        if (receiptOrderService.create(receiptOrder)) {
            model.addAttribute("message", "Receipt order created successfully");
            return new ModelAndView("creationReceiptOrder");
        }

        model.addAttribute("message", "Receipt order was not created");
        return new ModelAndView("creationReceiptOrder");
    }

    @GetMapping("/filter")
    public ModelAndView showProductFilter(Model model){
        model.addAttribute("receiptOrders", receiptOrderService.getAllReceiptOrder());
        return new ModelAndView("filterReceiptOrder");
    }
}
