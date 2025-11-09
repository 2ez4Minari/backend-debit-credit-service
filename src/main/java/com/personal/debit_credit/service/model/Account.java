package com.personal.debit_credit.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Account {

    private UUID id;
    private String accountName;
    private BigDecimal balance;
    private Date createdAt;
    private Date updatedAt;
}
