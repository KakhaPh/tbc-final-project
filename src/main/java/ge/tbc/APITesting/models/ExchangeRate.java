package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRate {
    private double buyRate;

    public double getBuyRate() {
        return buyRate;
    }
}