package ru.slot.transactionalservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.slot.transactionalservice.controller.deposit.RequestDepositDto;
import ru.slot.transactionalservice.service.TransactionService;

import java.util.UUID;

@RestController
@RequestMapping(name = " /api/v1/deposit/")
public class TransactionDepositController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionDepositController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/api/v1/deposit/{accountId}")
    public UUID deposit(@PathVariable("accountId") Long accountId, @RequestBody RequestDepositDto responseDepositDto){
       return transactionService.deposit(accountId, responseDepositDto.getAmount());
    }


}
