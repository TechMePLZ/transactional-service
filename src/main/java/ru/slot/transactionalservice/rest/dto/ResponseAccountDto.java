package ru.slot.transactionalservice.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ResponseAccountDto {
    private Long accountId;
    private String username;
    private BigDecimal balance;
    private String email;
    private LocalDateTime createdAt;
}
