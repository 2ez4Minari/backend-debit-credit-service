package com.personal.debit_credit.controller.DTO;

import com.personal.debit_credit.service.model.TxnType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TransactionReq {

    private UUID accountId;
    private TxnType type;
    private BigDecimal amount;
    private String description;
}
