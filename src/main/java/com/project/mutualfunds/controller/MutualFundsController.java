package com.project.mutualfunds.controller;
import com.project.mutualfunds.service.MutualFundsService;
import com.project.mutualfunds.model.MutualFunds;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/group_4/mutualfunds")
public class MutualFundsController {

    @Autowired
    private MutualFundsService mutualFundsService;

    @GetMapping
    @ResponseBody
    public List<MutualFunds> getAllMutualFunds() {
        return mutualFundsService.getAllMutualFunds();
    }

    @GetMapping("/futurevalue")
    @ResponseBody
    public double calculateFutureValue(@RequestParam String ticker, @RequestParam double initialInvestment, @RequestParam Integer time) {
        return mutualFundsService.calculateFutureValue(ticker, initialInvestment, time);
    }

}
