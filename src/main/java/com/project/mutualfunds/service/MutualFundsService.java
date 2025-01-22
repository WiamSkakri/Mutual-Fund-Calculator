package com.project.mutualfunds.service;

import org.springframework.stereotype.Service;

@Service
public class MutualFundsService {
    private final FetchBeta fetchBeta;
    private final MutualFundRepository mutualFundRepository;

    public MutualFundsService(FetchBeta fetchBeta, MutualFundRepository mutualFundRepository) {
        this.fetchBeta = fetchBeta;
        this.mutualFundRepository = mutualFundRepository;
    }

    // calculate future value
    public double calculateFutureValue(MutualFundsRequests mutualFundsRequests) {
        if (mutualFundsRequests == null) {
            throw new IllegalArgumentException("MutualFundsRequests cannot be null");
        }
        String ticker = mutualFundsRequests.ticker().name();
        String name = mutualFundsRequests.name();
        double riskFreeRate = FreeRateRiskTickers.getRiskFreeRateByTicker(ticker);
        double marketReturnRate = MarketRRTickers.getMarketReturnRateByTicker(ticker);
        double futureValue = mutualFundsRequests.InitialInvestment() * Math.pow(1 + (marketReturnRate - riskFreeRate) * fetchBeta.getBeta(ticker), mutualFundsRequests.time());
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
    public List<MutualFundsDb> getAllMutualFunds(){
        return mutualFundRepository.findAll();
    }

    public Optional<MutualFundsDb> getMutualFundsById(Long id){
        Optional<MutualFundsDb> mutualFundsDb = mutualFundRepository.findById(id);

        if (mutualFundsDb.isEmpty()) {
            throw new IllegalArgumentException("MutualFundsDb not found");
        }
        return mutualFundsDb;
    }

}
