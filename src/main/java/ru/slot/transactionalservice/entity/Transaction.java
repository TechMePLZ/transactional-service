package ru.slot.transactionalservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID transactionId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    //true - депозит, false - вывод
    private boolean typeOperation;
    private  Long accountId;

    private String username;
    private BigDecimal balance;

    public Transaction(BigDecimal amount, LocalDateTime createdAt, boolean typeOperation, Long accountId, String username, BigDecimal balance) {
        this.amount = amount;
        this.createdAt = createdAt;
        this.typeOperation = typeOperation;
        this.accountId = accountId;
        this.username = username;
        this.balance = balance;
    }
}
