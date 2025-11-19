package com.project.mutualfunds.service;

import com.project.mutualfunds.CustomExceptions.FetchBetaException;
import com.project.mutualfunds.CustomExceptions.FetchIdException;
import com.project.mutualfunds.dto.MutualFundsRequests;
import com.project.mutualfunds.dto.SaveFunds;
import com.project.mutualfunds.enums.FreeRateRiskTickers;
import com.project.mutualfunds.enums.MarketRRTickers;
import com.project.mutualfunds.model.MutualFundsDb;
import com.project.mutualfunds.repository.MutualFundRepository;
import org.springframework.stereotype.Service;
import com.project.mutualfunds.CustomExceptions.MutualFundsRequestsException;


import java.util.List;
import java.util.Optional;

@Service
public class MutualFundsService {

    private final MutualFundRepository mutualFundRepository;

    public MutualFundsService(MutualFundRepository mutualFundRepository) {
        this.mutualFundRepository = mutualFundRepository;
    }

    // calculate future value
    public double calculateFutureValue(MutualFundsRequests mutualFundsRequests, double fetchBeta) {
        if (mutualFundsRequests == null) {
            throw new MutualFundsRequestsException("MutualFundsRequests cannot be null");
        }
        String ticker = mutualFundsRequests.ticker().name();
        double riskFreeRate = FreeRateRiskTickers.getRiskFreeRateByTicker(ticker);
        double marketReturnRate = MarketRRTickers.getMarketReturnRateByTicker(ticker);
        double futureValue = mutualFundsRequests.InitialInvestment() *
                Math.pow(Math.E, (riskFreeRate + (marketReturnRate - riskFreeRate) * Math.abs(fetchBeta)) * mutualFundsRequests.time());
        return futureValue;
    }


    // get list mutual funds
    public List<MutualFundsDb> getAllMutualFunds() {
        return mutualFundRepository.findAll();
    }

    public Optional<MutualFundsDb> getMutualFundsById(Long id) {
        if (id == null) {
            throw new FetchIdException("Id cannot be null");
        }
        Optional<MutualFundsDb> mutualFundsDb = mutualFundRepository.findById(id);

        if (mutualFundsDb.isEmpty()) {
            throw new MutualFundsRequestsException("MutualFundsDb not found");
        }
        return mutualFundsDb;
    }

}
