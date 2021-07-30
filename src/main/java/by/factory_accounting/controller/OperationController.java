package by.factory_accounting.controller;

import by.factory_accounting.entity.dto.OperationDTO;
import by.factory_accounting.service.OperationService;
import by.factory_accounting.service.ProductService;
import by.factory_accounting.service.WorkerService;
import by.factory_accounting.tool.ConverterDTO;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequestMapping("/operation")
public class OperationController {


    private final ProductService productService;
    private final WorkerService workerService;
    private final OperationService operationService;
    private final ConverterDTO converterDTO;


    public OperationController(ProductService productService, WorkerService workerService, OperationService operationService, ConverterDTO converterDTO) {
        this.productService = productService;
        this.workerService = workerService;
        this.operationService = operationService;
        this.converterDTO = converterDTO;
    }




    @GetMapping("/create")
    public ModelAndView createOperationGet(Model model) {

        model.addAttribute("operationDTO", new OperationDTO());
        return new ModelAndView("creationOfOperation");
    }

    @PostMapping("/create")
    public ModelAndView createOperationPost(Model model, OperationDTO operationDTO){
        if(
                productService.existsByName(operationDTO.getSpendProductName())
                && productService.existsByName(operationDTO.getManufacturedProductName())
                && workerService.existsByName(operationDTO.getWorkerName())
                && !(operationService.isExists(operationDTO.getOperationName()))){

            operationService.save(converterDTO.getOperationFromDTO(operationDTO));

            model.addAttribute("message", "Operation created successfully");
            return new ModelAndView("creationOfOperation");
        }
        model.addAttribute("message", "One of the components does not exist or the operation name is busy. " +
                "Create the missing component or come up with another name for the operation.");
        return new ModelAndView("creationOfOperation");
    }


}
