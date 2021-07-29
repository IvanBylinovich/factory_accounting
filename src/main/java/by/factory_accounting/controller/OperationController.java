package by.factory_accounting.controller;

import by.factory_accounting.entity.accounting.Operation;
import by.factory_accounting.entity.accounting.Product;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
@RequestMapping("/operation")
public class OperationController {

    @GetMapping("/create")
    public ModelAndView createOperation(Model model) {

        List<Product> spentProducts = new ArrayList<>();
        List<Product> manufacturedProducts = new ArrayList<>();

        model.addAttribute("operation", new Operation());
        model.addAttribute("product", new Product());
        model.addAttribute("spentProducts", spentProducts);
        model.addAttribute("manufacturedProducts", manufacturedProducts);
        return new ModelAndView("creationOfOperation");
    }
    @GetMapping("/create/addSpentProduct")
    public String addSpentProducts(Model model, Product product) {
        List<Product> spentProduct =(ArrayList<Product>) model.getAttribute("spentProduct");
        spentProduct.add(product);

        return "redirect:/operation/create";
    }

}
