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
public class ProductionDTO {

    @NotEmpty(message = "Enter your operation name!")
    private String operationName;

    @NotNull(message = "Enter the product quantity")
    @Min(value = 0, message = "Price must be more than 0")
    private BigDecimal quantity;

}
