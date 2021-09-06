package by.factory_accounting.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptOrderDTO {

    @NotNull(message = "Enter the product quantity")
    @Min(value = 1, message = "There must be at least 1 unit of goods")
    private BigDecimal quantity;

    @NotNull(message = "Enter the product quantity")
    @Min(value = 1, message = "Price must be more than 0")
    private BigDecimal price;

    @NotBlank(message = "Enter the product name")
    private String productName;
    private String unit;
}
