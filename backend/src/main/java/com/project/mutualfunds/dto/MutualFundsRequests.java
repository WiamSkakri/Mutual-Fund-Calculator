package com.project.mutualfunds.dto;

import com.project.mutualfunds.enums.Tickers;

public record MutualFundsRequests(
        Tickers ticker,
        String name,
        double InitialInvestment,
        Integer time
) {
}