package com.javeriana.aes.managers.controller;

import com.javeriana.aes.managers.drools.RateDecisionMarketInput;
import com.javeriana.aes.managers.drools.RateDecisionMarketOutput;
import com.javeriana.aes.managers.service.impl.RateDecisionMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rates")
public class RatesController {

    private RateDecisionMarketService rateDecisionMarketService;

    @GetMapping
    public RateDecisionMarketOutput getSavingAccount(@RequestBody RateDecisionMarketInput rateDecisionMarketInput) {
        RateDecisionMarketOutput rateDecisionMarketOutput = new RateDecisionMarketOutput();
        rateDecisionMarketService.calculateRate(rateDecisionMarketInput, rateDecisionMarketOutput);
        return rateDecisionMarketOutput;
    }

    @Autowired
    public void setRateDecisionMarketService(RateDecisionMarketService rateDecisionMarketService) {
        this.rateDecisionMarketService = rateDecisionMarketService;
    }
}
