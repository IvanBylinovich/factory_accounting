package by.factory_accounting.tool;

import by.factory_accounting.entity.accounting.Operation;
import by.factory_accounting.entity.dto.OperationDTO;
import by.factory_accounting.service.ProductService;
import by.factory_accounting.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterDTO {

    private final WorkerService workerService;
    private final ProductService productService;

    @Autowired
    public ConverterDTO(WorkerService workerService, ProductService productService, ProductService productService1) {
        this.workerService = workerService;
        this.productService = productService1;
    }

    public Operation getOperationFromDTO(OperationDTO DTO) {
        return new Operation(
                DTO.getOperationName(),
                productService.findByName(DTO.getSpendProductName()).get(),
                DTO.getRequiredQuantityForProduction(),
                productService.findByName(DTO.getManufacturedProductName()).get(),
                workerService.findByName(DTO.getWorkerName()).get(),
                DTO.getOperationPayment());
    }

    public OperationDTO getDTOFromOperation(Operation operation) {
        return new OperationDTO(
                operation.getId(),
                operation.getName(),
                operation.getSpentProduct().getName(),
                operation.getRequiredQuantityForProduction(),
                operation.getManufacturedProduct().getName(),
                operation.getWorker().getName(),
                operation.getPayment());
    }

    public List<OperationDTO> getDTOListFromOperationList(List<Operation> operationList) {
        List<OperationDTO> operationDTOList = new ArrayList<>();

        for (Operation operation : operationList) {
            operationDTOList.add(getDTOFromOperation(operation));
        }
        return operationDTOList;
    }
}
