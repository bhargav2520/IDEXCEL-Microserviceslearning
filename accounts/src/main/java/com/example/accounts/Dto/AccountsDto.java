package com.example.accounts.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @NotEmpty(message = "AccountNumber can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    private Long accountNumber;
    @NotEmpty(message  ="Accounttype cant be empty")
    private String accountType;
    @NotEmpty(message  ="branchAddress cant be empty")
    private String branchAddress;

}
