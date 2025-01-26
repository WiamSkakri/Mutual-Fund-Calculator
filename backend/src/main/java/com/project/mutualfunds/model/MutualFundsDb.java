package com.project.mutualfunds.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MutualFundsDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticker;
    private String name;

    private double riskRate;
    private double marketRate;

    public MutualFundsDb() {
    }

    public MutualFundsDb(Long id, String ticker, String name, double riskRate, double marketRate) {
        this.id = id;
        this.ticker = ticker;
        this.name = name;
        this.riskRate = riskRate;
        this.marketRate = marketRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRiskRate() {
        return riskRate;
    }

    public void setRiskRate(double riskRate) {
        this.riskRate = riskRate;
    }

    public double getMarketRate() {
        return marketRate;
    }

    public void setMarketRate(double marketRate) {
        this.marketRate = marketRate;
    }
}
