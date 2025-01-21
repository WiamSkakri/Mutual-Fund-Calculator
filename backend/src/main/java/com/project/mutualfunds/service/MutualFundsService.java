package com.project.mutualfunds.service;
import com.project.mutualfunds.model.MutualFunds;
import java.util.List;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class MutualFundsService {

    public List<MutualFunds> getAllMutualFunds() {
        return  Arrays.asList(
            new MutualFunds(10000, "VFINX", "Vanguard 500 Index Fund"),
            new MutualFunds(10000, "AGTHX", "American Funds Growth Fund of America"),
            new MutualFunds(10000, "DODGX", "Dodge & Cox Stock Fund"),
            new MutualFunds(10000,"TRBCX", "T. Rowe Price Blue Chip Growth Fund")


        );
    }

    public double getBeta(String ticker){
        switch (ticker) {
            case "VFINX":
                return -0.2696298026397144;
            case "AGTHX":
                return -1.1464763869101418;
            case "DODGX":
                return -0.3808955146329592;
            case "TRBCX":
                return -0.21723522868332373;
            case "MUAIX":
                return 0.006218114587946719;
            case "JLGRX":
                return -0.2846896983315522;
            case "TRAIX":
                return -0.28989806427829956;
            case "JUESX":
                return -0.16147479253232383;
            case "APGAX":
                return -0.2690227570138719;

            case "BSPAX":
                return -0.28989806427829956;
            
            case "BASIX":
                return -0.06815344761808527;
            default:
                return 0.0;
            
        }
    }

    public double getRiskFreeRate(String ticker){
        switch (ticker) {
            case "VFINX":
                return.03;
            case "AGTHX":
                return.03;
            case "DODGX":
                return.03;
            case "TRBCX":
                return.03;
            default:
                return 0.0;
        }
    }

    public double getMarketReturnRate(String ticker){
        switch (ticker) {
            case "VFINX":
                return.08;
            case "AGTHX":
                return.08;
            case "DODGX":
                return.08;
            case "TRBCX":
                return.08;
            default:
                return 0.0;
        }
    }


    public double calculateFutureValue(String ticker, double initialInvestment, Integer time) {
        double beta = getBeta(ticker);
        double riskFreeRate = getRiskFreeRate(ticker);
        double marketReturnRate = getMarketReturnRate(ticker);
        double futureValue = initialInvestment * Math.pow(1 + (marketReturnRate - riskFreeRate) * beta, time);
        return futureValue;
    }
    
}
