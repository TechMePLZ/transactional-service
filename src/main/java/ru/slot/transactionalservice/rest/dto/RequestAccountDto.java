package ru.slot.transactionalservice.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RequestAccountDto {

    private String username;
    private BigDecimal balance;
    private String email;

}
