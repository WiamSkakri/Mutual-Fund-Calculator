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
        if (fetchBeta <= 0) {
            throw new FetchBetaException("FetchBeta should be greater than 0");
        }
        String ticker = mutualFundsRequests.ticker().name();
        String name = mutualFundsRequests.name();
        double riskFreeRate = FreeRateRiskTickers.getRiskFreeRateByTicker(ticker);
        double marketReturnRate = MarketRRTickers.getMarketReturnRateByTicker(ticker);
        double futureValue = mutualFundsRequests.InitialInvestment() *
                Math.pow(1 + (marketReturnRate - riskFreeRate) * fetchBeta, mutualFundsRequests.time());
        SaveFunds saveFunds = new SaveFunds(riskFreeRate, marketReturnRate, name, ticker);
        saveMutualFundsToDb(saveFunds);
        return futureValue;

    }


    private void saveMutualFundsToDb(SaveFunds saveFunds) {

        MutualFundsDb mutualFunds = new MutualFundsDb();
        mutualFunds.setRiskRate(saveFunds.riskFreeRate());
        mutualFunds.setMarketRate(saveFunds.marketReturnRate());
        mutualFunds.setName(saveFunds.name());
        mutualFunds.setTicker(saveFunds.ticker());

        mutualFundRepository.save(mutualFunds);
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
