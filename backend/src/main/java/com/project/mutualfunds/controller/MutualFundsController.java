package com.project.mutualfunds.controller;

import com.project.mutualfunds.dto.MutualFundsRequests;
import com.project.mutualfunds.model.MutualFundsDb;
import com.project.mutualfunds.service.MutualFundsService;
import com.project.mutualfunds.util.FetchBeta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/mutualfunds/requests")
public class MutualFundsController {

    private final MutualFundsService mutualFundsService;
    private final FetchBeta fetchBeta;

    public MutualFundsController(MutualFundsService mutualFundsService, FetchBeta fetchBeta) {
        this.mutualFundsService = mutualFundsService;
        this.fetchBeta = fetchBeta;

    }

    @PostMapping("/calculate/futureValue")
    private ResponseEntity<Double> createMutualFunds(@RequestBody MutualFundsRequests mutualFundsRequests) {
        return ResponseEntity.ok().body(mutualFundsService.calculateFutureValue(mutualFundsRequests, fetchBeta.getBeta(mutualFundsRequests.ticker().toString())));
    }


    @GetMapping("/allFunds")
    private List<MutualFundsDb> getAllMutualFundsRequests() {
        return mutualFundsService.getAllMutualFunds();

    }


    @GetMapping("/byId")
    private Optional<MutualFundsDb> getMutualFundsById(@RequestParam Long id){
        return mutualFundsService.getMutualFundsById(id);
    }


}
