package by.factory_accounting.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.config.ConfigFileApplicationListener;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {

    private long id;
    private String operationName;
    private String spendProductName;
    private String manufacturedProductName;
    private String workerName;
    private BigDecimal operationPayment; //плата рабочему за оперецию(складывается цена израсходованный материалов + плата за операцию)
    private BigDecimal amountCost;//все затраты

    public OperationDTO(String operationName, String spendProductName, String manufacturedProductName, String workerName, BigDecimal operationPayment) {
        this.operationName = operationName;
        this.spendProductName = spendProductName;
        this.manufacturedProductName = manufacturedProductName;
        this.workerName = workerName;
        this.operationPayment = operationPayment;
    }

    public OperationDTO(long id, String operationName, String spendProductName, String manufacturedProductName, String workerName, BigDecimal operationPayment) {
        this.id = id;
        this.operationName = operationName;
        this.spendProductName = spendProductName;
        this.manufacturedProductName = manufacturedProductName;
        this.workerName = workerName;
        this.operationPayment = operationPayment;
    }
}
