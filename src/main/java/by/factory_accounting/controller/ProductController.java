package by.factory_accounting.controller;

import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/create")
    public ModelAndView createProduct(Model model){
        model.addAttribute("product", new Product());
        return new ModelAndView("creationOfProduct");
    }

    @PostMapping("/create")
    public ModelAndView create(Product product, Model model){
        try {

            productService.create(product);
            model.addAttribute("message","Product created successfully");
            return new ModelAndView("creationOfProduct");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
            return new ModelAndView("creationOfProduct");
        }
    }

    @GetMapping("/filter")
    public ModelAndView showProductFilter(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return new ModelAndView("filterProduct");
    }
}
