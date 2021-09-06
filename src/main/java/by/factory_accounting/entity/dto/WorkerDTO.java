package by.factory_accounting.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDTO {


    @NotBlank(message = "Enter the product name")
    @Length(min = 2, max = 20, message = "The field must be at least 2 characters")
    private String name;

    @NotBlank(message = "Enter the specialization")
    private String specialization;

}
