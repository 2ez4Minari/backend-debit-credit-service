package com.personal.debit_credit.controller;

import com.personal.debit_credit.controller.DTO.DeleteResp;
import com.personal.debit_credit.controller.DTO.TransactionReq;
import com.personal.debit_credit.service.TransactionServiceImpl;
import com.personal.debit_credit.service.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/transactions")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    @PostMapping()
    public ResponseEntity<?> createNewTransaction(@RequestBody TransactionReq request) {
        try {
            return ResponseEntity.ok(transactionService.createNewTransaction(request));
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public Transaction getTransaction(@PathVariable(value = "id") UUID transactionId) {
        return transactionService.findTransactionByRequestId(transactionId);
    }

    @GetMapping()
    public List<Transaction> getTransactions() {
        return transactionService.findAllTransactions();
    }

    @SneakyThrows
    @DeleteMapping(value = "/{id}")
    public DeleteResp deleteTransaction(@PathVariable(value = "id") UUID transactionId) {
        return transactionService.deleteTransactionById(transactionId);
    }
}
