package by.factory_accounting.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.config.ConfigFileApplicationListener;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {
    private List<String> spentProducts;
    private List<String> manufacturedProducts;


}
