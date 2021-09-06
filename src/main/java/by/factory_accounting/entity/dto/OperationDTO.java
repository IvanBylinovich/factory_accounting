package by.factory_accounting.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {

    private long id;

    @NotEmpty(message = "Enter your operation name!")
    private String operationName;

    @NotEmpty(message = "Enter your spend product name!")
    private String spendProductName;

    @NotNull(message = "Enter the product quantity")
    @Min(value = 1, message = "Price must be more than 0")
    private BigDecimal requiredQuantityForProduction;

    @NotEmpty(message = "Enter your manufactured product name!")
    private String manufacturedProductName;

    @NotEmpty(message = "Enter your worker name!")
    private String workerName;

    @NotNull(message = "Enter the operation payment")
    @Min(value = 1, message = "Price must be more than 0")
    private BigDecimal operationPayment; //плата рабочему за оперецию(складывается цена израсходованный материалов + плата за операцию)
    private BigDecimal amountCost;//все затраты

    public OperationDTO(long id, String operationName, String spendProductName, BigDecimal requiredQuantityForProduction, String manufacturedProductName, String workerName, BigDecimal operationPayment) {
        this.id = id;
        this.operationName = operationName;
        this.spendProductName = spendProductName;
        this.requiredQuantityForProduction = requiredQuantityForProduction;
        this.manufacturedProductName = manufacturedProductName;
        this.workerName = workerName;
    }

    public OperationDTO(String operationName, String spendProductName, BigDecimal requiredQuantityForProduction, String manufacturedProductName, String workerName, BigDecimal operationPayment) {
        this.operationName = operationName;
        this.spendProductName = spendProductName;
        this.requiredQuantityForProduction = requiredQuantityForProduction;
        this.manufacturedProductName = manufacturedProductName;
        this.workerName = workerName;
        this.operationPayment = operationPayment;
    }
}
