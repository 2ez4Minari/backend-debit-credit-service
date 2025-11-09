package com.personal.debit_credit.service;

import com.personal.debit_credit.controller.DTO.DeleteResp;
import com.personal.debit_credit.controller.DTO.TransactionReq;
import com.personal.debit_credit.controller.DTO.UpdateTransactionReq;
import com.personal.debit_credit.infra.repository.AccountRepository;
import com.personal.debit_credit.infra.repository.TransactionRepository;
import com.personal.debit_credit.service.model.Account;
import com.personal.debit_credit.service.model.Transaction;
import com.personal.debit_credit.service.model.TxnType;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public Transaction createNewTransaction(TransactionReq req) throws BadRequestException {
        Account customerAccount = accountRepository.findById(req.getAccountId());
        if (req.getType() == TxnType.DEBIT) {
            BigDecimal currentBalance = customerAccount.getBalance().subtract(req.getAmount());
            if (currentBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new BadRequestException("Insufficient balance!");
            }

            accountRepository.updateById(req.getAccountId(), currentBalance);
            return transactionRepository.save(req);
        } else {
            BigDecimal currentBalance = customerAccount.getBalance().add(req.getAmount());

            accountRepository.updateById(req.getAccountId(), currentBalance);
            return transactionRepository.save(req);
        }
    }

    public Transaction findTransactionByRequestId(UUID transactionId) {

        return transactionRepository.findById(transactionId);
    }

    public List<Transaction> findAllTransactions() {

        return transactionRepository.findAll();
    }

    public List<Transaction> findAllTransactionsByAccountId(UUID accountId) {

        return transactionRepository.findAllByAccountId(accountId);
    }

    public Transaction updateTransactionByRequestId(UUID transactionId, UpdateTransactionReq req) {
        return new Transaction();
    }

    public DeleteResp deleteTransactionById(UUID transactionId) throws BadRequestException {
        Transaction transaction = transactionRepository.findById(transactionId);
        Account customerAccount = accountRepository.findById(transaction.getAccountId());

        revertOrUpdateTransaction(transaction, customerAccount);
        transactionRepository.delete(transaction);

        return DeleteResp.builder()
                .message("Transaction deleted and balance reverted")
                .build();
    }

    private void revertOrUpdateTransaction(Transaction transaction, Account account) throws BadRequestException {
        UUID accountId = account.getId();
        BigDecimal amountToBeReverted = transaction.getAmount();


        if(transaction.getType() == TxnType.DEBIT) {

            BigDecimal currentBalance = account.getBalance().add(amountToBeReverted);
            accountRepository.updateById(account.getId(), currentBalance);
        } else {

            BigDecimal currentBalance = account.getBalance().subtract(amountToBeReverted);
            if (currentBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new BadRequestException("Insufficient balance!");
            }
            accountRepository.updateById(accountId, currentBalance);
        }
    }

}
