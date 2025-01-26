package com.project.mutualfunds.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;


@Component
public class FetchBeta implements BetaFetcher{

    private static final String NEWTON_API_URL = "https://api.newtonanalytics.com/stock-beta/";

    @Override
    public double getBeta(String ticker) {
        String index = "%5EGSPC";
        String interval = "1mo";
        int observations = 12;
        try {
            String url = String.format("%s?ticker=%s&index=%s&interval=%s&observations=%d", NEWTON_API_URL, ticker, index, interval, observations);
            HttpResponse<String> response;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

                response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();


            System.out.println("Response Body: " + responseBody);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            return jsonNode.get("data").asDouble();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching beta for " + ticker, e);
        }
    }

    public static void main(String[] args) {
        FetchBeta fetchBeta = new FetchBeta();
        double beta = fetchBeta.getBeta("TARZX");
        System.out.println("Beta1: " + beta);

        double beta2 = fetchBeta.getBeta("FNORX");

        System.out.println("Beta2: " + beta2);

         double beta3 = fetchBeta.getBeta("FPHAX");
         System.out.println("Beta: " + beta3);
//
//    //     double beta4 = fetchBeta.getBeta("TRBCX", "SPY", "1mo", 48);
//    //     System.out.println("Beta: " + beta4);
//
//    //     double beta5 = fetchBeta.getBeta("MUAIX", "SPY", "1mo", 48);
//    //     System.out.println("Beta: " + beta5);
//
//    //     double beta6 = fetchBeta.getBeta("JLGRX", "SPY", "1mo", 48);
//    //     System.out.println("Beta for JP Morgan Large Cap Growth R5: " + beta6);
//
//    //     double beta7 = fetchBeta.getBeta("TRAIX", "SPY", "1mo", 48);
//    //     System.out.println("Beta for T. Rowe Price Caital Appriciation I: " + beta7);
//
//    //     double beta8 = fetchBeta.getBeta("JUESX", "SPY", "1mo", 48);
//    //     System.out.println("Beta for JPMorgan US Equity I: " + beta8);
//
//    //     double beta9 = fetchBeta.getBeta("APGAX", "SPY", "1mo", 48);
//    //     System.out.println("AB Large Cap Growth Fund: " + beta9);
//
//    //     double beta10 = fetchBeta.getBeta("BSPAX", "SPY", "1mo", 48);
//    //     System.out.println("iShares S&P 500 Index  Investor A Beta: " + beta10);
//
//    //     double beta11 = fetchBeta.getBeta("BASIX", "SPY", "1mo", 48);
//    //     System.out.println("BlackRock Strategic Income Opps  Inv A: " + beta11);
//
    }
}