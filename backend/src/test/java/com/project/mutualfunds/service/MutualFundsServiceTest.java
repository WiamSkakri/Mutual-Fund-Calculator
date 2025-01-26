package com.project.mutualfunds.service;

import com.project.mutualfunds.CustomExceptions.FetchBetaException;
import com.project.mutualfunds.CustomExceptions.FetchIdException;
import com.project.mutualfunds.CustomExceptions.MutualFundsRequestsException;
import com.project.mutualfunds.dto.MutualFundsRequests;
import com.project.mutualfunds.enums.Tickers;
import com.project.mutualfunds.model.MutualFundsDb;
import com.project.mutualfunds.repository.MutualFundRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class MutualFundsServiceTest {
    private MutualFundsService mutualFundsService;
    private MutualFundsRequests mutualFundsRequests;
    private MutualFundRepository mutualFundRepository;


    @BeforeEach
    void setUp() {
        mutualFundRepository = mock(MutualFundRepository.class);
        mutualFundsService = new MutualFundsService(mutualFundRepository);
        mutualFundsRequests = mock(MutualFundsRequests.class);


    }

    @Test
    void checkBetaIfLessThanOrEqualToZero(){
        FetchBetaException thrown = assertThrows(
                FetchBetaException.class, () -> mutualFundsService.calculateFutureValue(mutualFundsRequests, -1.0)
        );
        assertTrue((thrown.getMessage().contains("FetchBeta should be greater than 0")));
    }


    @Test
    void calculateFutureValueWhenMutualFundsRequestsIsNull() {

        //GIVEN

        MutualFundsRequestsException thrown = assertThrows(
                MutualFundsRequestsException.class,
                () -> mutualFundsService.calculateFutureValue(null, 2.4)
        );

        assertTrue(thrown.getMessage().contains("MutualFundsRequests cannot be null"));
        //WHEN
        //THEN

    }

    @Test
    void calculateFutureValueWhenMutualFundsRequestsIsNotNull() {

        //GIVEN
        MutualFundsRequests mutualFundsRequests =
                new MutualFundsRequests(Tickers.AGTHX,
                        "American Funds Growth Fund of America",
                        30000,
                        4
                );

        double actualFutureValue = mutualFundsService.calculateFutureValue(mutualFundsRequests, .4);

        //THEN
        assertEquals(43974.13270704568, actualFutureValue);

    }

    @Test
    void getGetAllMutualFundsInDb() {

        assertTrue(mutualFundsService.getAllMutualFunds() instanceof List<MutualFundsDb>);

    }

    @Test
    void getMutualFundsWhereIdIsNull() {
        FetchIdException thrown = assertThrows(
                FetchIdException.class,
                () -> mutualFundsService.getMutualFundsById(null)
        );
        assertTrue(thrown.getMessage().contains("Id cannot be null"));


    }

    @Test
    void getMutualFundsWhereIdIsNotPresent() {

        MutualFundsRequestsException thrown = assertThrows(
                MutualFundsRequestsException.class,
                () -> mutualFundsService.getMutualFundsById(1L)
        );
        assertTrue(thrown.getMessage().contains("MutualFundsDb not found"));

    }

    @Test
    void getMutualFundsWhereIdIsPresent() {

        MutualFundsDb fund = new MutualFundsDb(1L, "Ass", "ssd", 0.4, 0.6);
        Optional<MutualFundsDb> opt = Optional.of(fund);
        when(mutualFundRepository.findById(1L)).thenReturn(Optional.of(fund));

        assertEquals(opt, mutualFundsService.getMutualFundsById(1L));


    }

    @AfterEach
    void tearDown() {
        mutualFundRepository = null;
        mutualFundsService = null;
    }
}