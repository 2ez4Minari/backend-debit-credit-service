package com.personal.debit_credit.controller.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountReq {

    @NotBlank(message = "Account Name must not be empty!")
    private String accountName;
}
