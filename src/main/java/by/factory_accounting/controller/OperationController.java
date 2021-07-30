package by.factory_accounting.controller;

import by.factory_accounting.entity.accounting.Operation;
import by.factory_accounting.entity.accounting.Product;
import by.factory_accounting.entity.dto.OperationDTO;
import by.factory_accounting.repository.ProductRepository;
import by.factory_accounting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    ProductService productService;

    @GetMapping("/create")
    public ModelAndView createOperationGet(Model model) {

        model.addAttribute("operation", new OperationDTO());


//
//        List<Product> spentProducts = new ArrayList<>();
//        List<Product> manufacturedProducts = new ArrayList<>();
//
//
//    //    model.addAttribute("operation", new Operation());
//        model.addAttribute("product", new Product());
//        model.addAttribute("spentProducts", spentProducts);
//    //    model.addAttribute("manufacturedProducts", manufacturedProducts);
        return new ModelAndView("creationOfOperation");
    }
    @GetMapping("/create/addSpentProduct")
    public String addSpentProducts(Model model, Product product) {
        List<Product> spentProduct =(ArrayList<Product>) model.getAttribute("spentProduct");
        spentProduct.add(product);

        return "redirect:/operation/create";
    }

//    @PostMapping("/create")
//    public ModelAndView createOperationPost(Model model, Operation operation, List<Product> spentProducts ){
//        return new ModelAndView("creationOfOperation");
//    }

    @PostMapping("/create/addSpentProduct")
    public String createOperationPost(Model model, Product product, List<Product> spentProducts,  HttpSession session){

       // ArrayList<Product> products = (ArrayList<Product>)model.getAttribute("spentProducts");
        Optional<Product> foundedProduct = productService.findByName(product.getName());

        if(foundedProduct.isPresent()){
            spentProducts.add(foundedProduct.get());
            model.addAttribute("spentProducts", spentProducts);
            return "redirect:/operation/create";
        }

        model.addAttribute("message", "такого товара нет в базе, создайте его прежде чем добовлять");
        return "redirect:/operation/create";



    }

}
