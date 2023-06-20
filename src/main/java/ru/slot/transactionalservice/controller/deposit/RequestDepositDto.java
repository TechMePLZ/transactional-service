package ru.slot.transactionalservice.controller.deposit;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class RequestDepositDto {

    @NonNull
    private BigDecimal amount;

    public RequestDepositDto(@NonNull BigDecimal amount) {
        this.amount = amount;
    }
}
