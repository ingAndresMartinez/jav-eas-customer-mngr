package com.javeriana.aes.managers.service.impl;

import com.javeriana.aes.managers.drools.RateDecisionMarketInput;
import com.javeriana.aes.managers.drools.RateDecisionMarketOutput;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateDecisionMarketService {

    private KieContainer kieContainer;

    public double calculateRate(RateDecisionMarketInput rateDecisionMarketInput, RateDecisionMarketOutput rateDecisionMarketOutput) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("output", rateDecisionMarketOutput);
        kieSession.insert(rateDecisionMarketInput);
        kieSession.fireAllRules();
        kieSession.dispose();
        return rateDecisionMarketOutput.getRate();
    }

    @Autowired
    public void setKieContainer(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

}