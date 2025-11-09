package com.personal.debit_credit.service;

import com.personal.debit_credit.controller.DTO.DeleteResp;
import com.personal.debit_credit.controller.DTO.UpdateAccountResp;
import com.personal.debit_credit.infra.repository.AccountRepository;
import com.personal.debit_credit.service.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl {

    private final AccountRepository accountRepository;

    public Account createAccount(String accountName) {

        return accountRepository.save(accountName);
    }

    public Account getAccount(UUID accountId) {

        return accountRepository.findById(accountId);
    }

    public List<Account> getAllAccounts() {

        return accountRepository.findAll();
    }

    @SneakyThrows
    public UpdateAccountResp updateAccount(UUID accountId, String accountName) {

        Account updatedAccount = accountRepository.updateById(accountId, accountName);
        return UpdateAccountResp.builder()
                .accountName(updatedAccount.getAccountName())
                .id(updatedAccount.getId())
                .balance(updatedAccount.getBalance())
                .build();
    }

    @SneakyThrows
    public DeleteResp deleteAccount(UUID accountId) {

        accountRepository.deleteAccount(accountId);
        return DeleteResp.builder()
                .message("Account deleted successfully")
                .build();
    }
}
