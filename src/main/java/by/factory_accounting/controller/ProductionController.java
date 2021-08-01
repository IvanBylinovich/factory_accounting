package by.factory_accounting.controller;

import by.factory_accounting.entity.accounting.Operation;
import by.factory_accounting.entity.dto.ProductionDTO;
import by.factory_accounting.service.OperationService;
import by.factory_accounting.service.ReceiptOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/production")
public class ProductionController {

    @Autowired
    OperationService operationService;
    @Autowired
    ReceiptOrderService receiptOrderService;

    @GetMapping
    public ModelAndView production(Model model){
        model.addAttribute("productionDTO",new ProductionDTO());
        return new ModelAndView("productionOfGoods");
    }

    @PostMapping
    public ModelAndView productionPost(Model model, ProductionDTO productionDTO){

        Optional<Operation> operationOptional =  operationService.findByProductName(productionDTO.getOperationName());

        if(operationOptional.isPresent()){
            if(operationService.performOperation(operationOptional.get(), productionDTO.getQuantity())) {
                model.addAttribute("message", "Operation perform successfully");
                return new ModelAndView("productionOfGoods");
            }
            model.addAttribute("message", "Недостаточно сырья!");
            return new ModelAndView("productionOfGoods");
        }

        model.addAttribute("message", "Такой операции не существует, создайте её прежде чем выполнять!");
        return new ModelAndView("productionOfGoods");
    }
}
