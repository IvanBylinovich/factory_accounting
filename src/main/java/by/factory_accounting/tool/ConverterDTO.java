package by.factory_accounting.tool;

import by.factory_accounting.entity.accounting.Operation;
import by.factory_accounting.entity.dto.OperationDTO;
import by.factory_accounting.service.ProductService;
import by.factory_accounting.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ConverterDTO {

    private final WorkerService workerService;
    private final ProductService productService;

    @Autowired
    public ConverterDTO(WorkerService workerService, ProductService productService, ProductService productService1) {
        this.workerService = workerService;
        this.productService = productService1;
    }

    public Operation  getOperationFromDTO(OperationDTO DTO){
        return new Operation(
                DTO.getOperationName(),
                productService.findByName(DTO.getSpendProductName()).get(),
                productService.findByName(DTO.getManufacturedProductName()).get(),
                workerService.findByName(DTO.getWorkerName()).get(),
                DTO.getOperationPayment());
    }
}
