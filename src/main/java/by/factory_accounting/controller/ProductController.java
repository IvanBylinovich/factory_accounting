package by.factory_accounting.controller;

import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.entity.dto.ProductDTO;
import by.factory_accounting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public ModelAndView create(@ModelAttribute("product") @Valid ProductDTO productDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) return new ModelAndView("creationOfProduct");

        if(productService.existsByName(productDTO.getName())){
            model.addAttribute("message", "Such a product already exists!");
            return new ModelAndView("creationOfProduct");
        }

        productService.create(new Product(productDTO.getName(), productDTO.getUnit()));
        model.addAttribute("message","Product created successfully");
        return new ModelAndView("creationOfProduct");


    }

    @GetMapping("/filter")
    public ModelAndView showProductFilter(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return new ModelAndView("filterProduct");
    }
}
