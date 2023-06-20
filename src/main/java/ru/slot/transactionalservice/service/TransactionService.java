package ru.slot.transactionalservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.slot.transactionalservice.controller.deposit.ResponseDepositDto;
import ru.slot.transactionalservice.entity.Transaction;
import ru.slot.transactionalservice.repository.TransactionRepository;
import ru.slot.transactionalservice.rest.AccountServiceClient;
import ru.slot.transactionalservice.rest.dto.RequestAccountDto;
import ru.slot.transactionalservice.rest.dto.ResponseAccountDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountServiceClient accountServiceClient;
    private  final RabbitTemplate rabbitTemplate;

//    public static final String QUEUE_TRANSACTION = "slot.transaction.notify.queue";

    private static final String TOPIC_EXCHANGE_TRANSACION ="slot.transaction.notify.exchange";
    private static final String ROUTING_KEY_TRANSACTION = "slot.transaction.key";

  public UUID deposit(Long accountId, BigDecimal amount){

      if (accountId == null ) {
          throw new RuntimeException("Account is null ");
      }
      else{
          ResponseAccountDto accountDto = accountServiceClient.getAccountById(accountId);
          BigDecimal updatedBalance = accountDto.getBalance().add(amount);
          Transaction transaction = new Transaction(amount, LocalDateTime.now(), true, accountId, accountDto.getUsername() ,updatedBalance);
          accountDto.setBalance(updatedBalance);
          accountServiceClient.updateAccountById(accountId,new RequestAccountDto(accountDto.getUsername(), accountDto.getBalance(), accountDto.getEmail()));
          try {
              sendMessageByRabbit(accountDto.getUsername(), accountDto.getEmail(), amount);
          } catch (JsonProcessingException e) {
              throw new RuntimeException("чтото сломалося");
          }
          System.out.println("push into rabbit");
          return transactionRepository.save(transaction).getTransactionId();

      }

  }


    private ResponseDepositDto sendMessageByRabbit(String username, String email, BigDecimal amount) throws JsonProcessingException {
        ResponseDepositDto objectToMessage = new ResponseDepositDto(username, email, amount);
        ObjectMapper objectMapper = new ObjectMapper();
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_TRANSACION, ROUTING_KEY_TRANSACTION, objectMapper.writeValueAsString(objectToMessage));
        return  objectToMessage;
    }

}
