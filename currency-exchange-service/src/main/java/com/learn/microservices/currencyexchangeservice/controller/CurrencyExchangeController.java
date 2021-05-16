package com.learn.microservices.currencyexchangeservice.controller;

import com.learn.microservices.currencyexchangeservice.dto.ExchangeValue;
import com.learn.microservices.currencyexchangeservice.repo.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to){

        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException(String.format("The combination of from: %s and to: %s couldn't be found.", from, to)));
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        logger.info("Exchange Value -> {}",exchangeValue);
        return exchangeValue;
    }
}
