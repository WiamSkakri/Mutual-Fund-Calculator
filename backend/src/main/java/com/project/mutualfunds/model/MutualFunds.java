package com.project.mutualfunds.model;

public class MutualFunds {
    private String ticker;
    private String name;
    private double initialInvestment;


    public MutualFunds(double initialInvestment, String ticker, String name) {
        this.initialInvestment = initialInvestment;
        this.ticker = ticker;
        this.name = name;
        }

    public double getInitialInvestment() {
        return initialInvestment;
    }

    public void setInitialInvestment(double initialInvestment) {
        this.initialInvestment = initialInvestment;
    }
    
    public String getTicker() {
        return this.ticker;
    }
    public String getName() {
        return this.name;
    }
    

}