package com.personal.debit_credit.controller.DTO;

import com.personal.debit_credit.service.model.TxnType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateTransactionReq {

    private TxnType type;
    private BigDecimal amount;
    private String description;
}
