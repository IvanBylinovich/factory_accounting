package by.factory_accounting.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotEmpty(message = "Enter your username!")
    @Email(message = "The login must be email!")
    private String username;
    @NotEmpty(message = "Enter your password!")
    @Length(min = 8, message = "The field must be at least 8 characters")
    private String password;
}
