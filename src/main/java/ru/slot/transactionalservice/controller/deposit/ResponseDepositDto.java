package ru.slot.transactionalservice.controller.deposit;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ResponseDepositDto {
    private String email;
    private String username;
    private BigDecimal amount;
}
