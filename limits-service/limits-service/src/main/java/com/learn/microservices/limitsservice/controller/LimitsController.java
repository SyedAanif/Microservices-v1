package com.learn.microservices.limitsservice.controller;

import com.learn.microservices.limitsservice.config.LimitConfiguration;
import com.learn.microservices.limitsservice.dto.LimitValues;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private LimitConfiguration limitConfiguration;

    @GetMapping(path = "/limits")
    public LimitValues getLimitsConfig(){
        return new LimitValues(limitConfiguration.getMinimum(),limitConfiguration.getMaximum());
    }

    @GetMapping(path = "/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "faultToleranceFallback")
    public LimitValues faultTolerance(){
        throw new RuntimeException("Explaining Fault Tolerance...");
    }

    public LimitValues faultToleranceFallback(){
        return new LimitValues(9,999);
    }
}
