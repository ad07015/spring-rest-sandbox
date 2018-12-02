package com.in28minutes.springboot.microservice.example.forex.springbootmicroserviceforexservice.controller;

import com.in28minutes.springboot.microservice.example.forex.springbootmicroserviceforexservice.entity.ExchangeValue;
import com.in28minutes.springboot.microservice.example.forex.springbootmicroserviceforexservice.repo.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ForexController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        return exchangeValue;
    }

    @GetMapping("/currency-exchange")
    public ResponseEntity retrieveExchangeValueById(@RequestParam Long id) {
        Optional<ExchangeValue> exchangeValue = repository.findById(id);
        if (exchangeValue.isPresent()) {
            return new ResponseEntity<>(exchangeValue.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/currency-exchange")
    public ExchangeValue createCurrencyMapping(@RequestBody ExchangeValue newExchangeValue) {
        repository.save(newExchangeValue);
        return newExchangeValue;
    }
}
