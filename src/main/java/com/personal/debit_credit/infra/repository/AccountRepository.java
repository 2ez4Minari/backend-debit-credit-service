package com.personal.debit_credit.infra.repository;

import com.personal.debit_credit.service.model.Account;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class AccountRepository {

    private final List<Account> accounts = new ArrayList<>();

    public Account save(String accountName) {
        Account newAccount = Account.builder()
                .accountName(accountName)
                .id(UUID.randomUUID())
                .balance(new BigDecimal("0.00"))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        accounts.add(newAccount);
        return newAccount;
    }

    public Account findById(UUID accountId) {

        return accounts.stream()
                .filter(account -> account.getId().equals(accountId))
                .findFirst()
                .orElse(null);
    }

    public List<Account> findAll() {

        return accounts;
    }

    public Account updateById(UUID accountId, String accountName) throws BadRequestException {

        Account existingAccount = accounts.stream()
                .filter(account -> account.getId().equals(accountId))
                .findFirst()
                .orElse(null);

        if (existingAccount == null) {
            throw new BadRequestException("Invalid Account.");
        }

        existingAccount.setAccountName(accountName);
        existingAccount.setUpdatedAt(new Date());

        return existingAccount;
    }

    public Account updateById(UUID accountId, BigDecimal amount) throws BadRequestException {

        Account existingAccount = accounts.stream()
                .filter(account -> account.getId().equals(accountId))
                .findFirst()
                .orElse(null);

        if (existingAccount == null) {
            throw new BadRequestException("Invalid Account.");
        }

        existingAccount.setBalance(amount);
        existingAccount.setUpdatedAt(new Date());

        return existingAccount;
    }

    public void deleteAccount(UUID accountId) throws BadRequestException {

        Account existingAccount = accounts.stream()
                .filter(account -> account.getId().equals(accountId))
                .findFirst()
                .orElse(null);

        if (existingAccount == null) {
            throw new BadRequestException("Invalid Account.");
        }

        accounts.remove(existingAccount);
    }
}
