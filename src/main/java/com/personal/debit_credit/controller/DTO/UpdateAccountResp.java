package com.personal.debit_credit.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateAccountResp {

    private UUID id;
    private String accountName;
    private BigDecimal balance;
}
