package com.learn.microservices.currencyconversionservice.controller;

import com.learn.microservices.currencyconversionservice.dto.CurrencyConversion;
import com.learn.microservices.currencyconversionservice.feign.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping(path = "/currency-converter/from/{from}/to/{to}/quantity/{qty}")
    public CurrencyConversion convert(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to, @PathVariable(name = "qty") BigDecimal quantity){

        Map<String,String> uriVariables=new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,uriVariables);
        CurrencyConversion response=responseEntity.getBody();
        return new CurrencyConversion(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
    }

    @GetMapping(path = "/currency-converter-feign/from/{from}/to/{to}/quantity/{qty}")
    public CurrencyConversion convertUsingFeign(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to, @PathVariable(name = "qty") BigDecimal quantity){

        CurrencyConversion response = currencyExchangeServiceProxy.getExchangeValue(from, to);
        logger.info("Response -> {}",response);
        return new CurrencyConversion(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
    }
}
