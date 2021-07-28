package by.factory_accounting.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptOrderDTO {
    private BigDecimal quantity;
    private BigDecimal price;
    private String productName;
    private String unit;
}
