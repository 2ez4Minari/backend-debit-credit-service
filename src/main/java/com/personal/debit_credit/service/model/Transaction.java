package com.personal.debit_credit.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Transaction {

    private UUID id;
    private UUID accountId;
    private TxnType type;
    private BigDecimal amount;
    private String description;
    private Date createdAt;
}
