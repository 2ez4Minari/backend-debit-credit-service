package com.personal.debit_credit.infra.repository;

import com.personal.debit_credit.controller.DTO.TransactionReq;
import com.personal.debit_credit.service.model.Transaction;
import com.personal.debit_credit.service.model.TxnType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class TransactionRepository {

    List<Transaction> transactions = new ArrayList<>();

    public Transaction findById(UUID transactionId) {

        return transactions.stream()
                .filter(account -> account.getId().equals(transactionId))
                .findFirst()
                .orElse(null);
    }

    public List<Transaction> findAll() {

        return transactions;
    }

    public List<Transaction> findAllByAccountId(UUID accountId) {

        return transactions.stream()
                .filter(account -> account.getAccountId().equals(accountId))
                .toList();
    }

    public Transaction save(TransactionReq req) {

        Transaction newTransaction = Transaction.builder()
                .id(UUID.randomUUID())
                .accountId(req.getAccountId())
                .type(req.getType())
                .amount(req.getAmount())
                .description(req.getDescription())
                .createdAt(new Date())
                .build();

        transactions.add(newTransaction);

        return newTransaction;
    }

    public Transaction update(Transaction transaction, TxnType type, BigDecimal amount, String description) {

        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setDescription(description);

        return transaction;
    }

    public void delete(Transaction transaction) {

        transactions.remove(transaction);
    }
}
