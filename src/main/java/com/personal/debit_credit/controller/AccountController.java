package com.personal.debit_credit.controller;

import com.personal.debit_credit.controller.DTO.AccountReq;
import com.personal.debit_credit.controller.DTO.DeleteResp;
import com.personal.debit_credit.controller.DTO.UpdateAccountResp;
import com.personal.debit_credit.service.AccountServiceImpl;
import com.personal.debit_credit.service.TransactionServiceImpl;
import com.personal.debit_credit.service.model.Account;
import com.personal.debit_credit.service.model.Transaction;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;
    private final TransactionServiceImpl transactionService;

    @PostMapping()
    public Account createAccount(@RequestBody @Valid AccountReq accountReq) {
        return accountService.createAccount(accountReq.getAccountName());
    }

    @GetMapping(value = "/{id}")
    public Account getAccount(@PathVariable(value = "id") UUID accountId) {
        return accountService.getAccount(accountId);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping(value = "/{id}")
    public UpdateAccountResp updateAccount(@RequestBody @Valid AccountReq accountReq, @PathVariable(value = "id") UUID accountId) {
        return accountService.updateAccount(accountId, accountReq.getAccountName());
    }

    @DeleteMapping(value = "/{id}")
    public DeleteResp deleteAccount(@PathVariable(value = "id") UUID accountId) {
        return accountService.deleteAccount(accountId);
    }

    @GetMapping(value = "/{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable(value = "id") UUID accountId) {
        return transactionService.findAllTransactionsByAccountId(accountId);
    }
}
