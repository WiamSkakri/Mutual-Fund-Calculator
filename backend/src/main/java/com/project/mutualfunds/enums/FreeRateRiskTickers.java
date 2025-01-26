package com.project.mutualfunds.enums;

public enum FreeRateRiskTickers {
    VFINX(.0427),
    AGTHX(.0335),
    DODGX(.0302),
    TRBCX(.0300),
    MUAIX(.0336),
    JLGRX(.0310),
    TRAIX(.0405),
    JUESX(.0307),
    APGAX(.0314),
    VFFSX(.0325),
    BHYIX(.0235),
    BASIX(.0403);




    private final double riskFreeRate;

    FreeRateRiskTickers(double riskFreeRate) {
        this.riskFreeRate = riskFreeRate;
    }

    public double getRiskFreeRate() {
        return this.riskFreeRate;
    }

    public static double getRiskFreeRateByTicker(String symbol) {
        try {
            return valueOf(symbol).getRiskFreeRate();
        } catch (IllegalArgumentException e) {
            return .0;
        }
    }
}
