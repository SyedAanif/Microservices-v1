package com.learn.microservices.currencyconversionservice.feign;

import com.learn.microservices.currencyconversionservice.dto.CurrencyConversion;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service",url = "localhost:8000")
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    //@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    @GetMapping(path = "/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion getExchangeValue(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to);
}
