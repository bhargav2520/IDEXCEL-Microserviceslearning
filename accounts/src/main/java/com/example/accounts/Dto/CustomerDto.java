package com.example.accounts.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {
    @Schema(
            description = "Name of the customer", example = "Eazy Bytes"
    )
    @NotEmpty(message = "name cant be empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")

    private String name;
    @Schema(
            description = "email which u use", example = "bhjargav@gmail.com"
    )
@NotEmpty(message="message cant be empty")
@Email(message = "email must be valid")
    private String email;
@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
