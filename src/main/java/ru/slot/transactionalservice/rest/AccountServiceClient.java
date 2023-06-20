package ru.slot.transactionalservice.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.slot.transactionalservice.rest.dto.RequestAccountDto;
import ru.slot.transactionalservice.rest.dto.ResponseAccountDto;

@FeignClient(url = "http://localhost:8086", value = "accounts")
public interface AccountServiceClient {

    @RequestMapping(value = "/api/v1/account/{accountId}", method = RequestMethod.GET)
    ResponseAccountDto getAccountById(@PathVariable("accountId") Long accountId);

    @RequestMapping(value = "/api/v1/account/{accountId}", method = RequestMethod.PUT)
    ResponseAccountDto updateAccountById(@PathVariable("accountId") Long accountId, @RequestBody RequestAccountDto accountDto);

}
