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

    private String operationName;
    private String spendProductName;
    private String manufacturedProductName;
    private String workerName;
    private BigDecimal operationPayment; //плата рабочему за оперецию(складывается цена израсходованный материалов + плата за операцию)
    private BigDecimal amountCost;//все затраты

}
