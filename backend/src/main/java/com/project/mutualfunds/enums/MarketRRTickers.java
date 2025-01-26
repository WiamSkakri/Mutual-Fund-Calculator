package com.project.mutualfunds.enums;

public enum MarketRRTickers {

    VFINX(.2484),
    AGTHX(.2843),
    DODGX(.1451),
    TRBCX(.3563),
    MUAIX(.0536),
    JLGRX(.3405),
    TRAIX(.1284),
    JUESX(.2397),
    APGAX(.2514),
    VFFSX(.25),
    BHYIX(.0863),
    BASIX(.053);


    private final double marketReturnRate;

    MarketRRTickers(double marketReturnRate) {
        this.marketReturnRate = marketReturnRate;
    }

    public double getMarketReturnRate() {
        return this.marketReturnRate;
    }

    public static double getMarketReturnRateByTicker(String symbol) {
        try {
            return valueOf(symbol).getMarketReturnRate();
        } catch (IllegalArgumentException e) {
            return .0;
        }
    }
}
