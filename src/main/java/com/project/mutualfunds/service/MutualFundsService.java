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
                return 1.00;
            case "AGTHX":
                return 1.00;
            case "DODGX":
                return .98;
            case "TRBCX":
                return 1.05;
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
