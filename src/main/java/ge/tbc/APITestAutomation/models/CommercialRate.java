package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommercialRate {
    private double buy;

    public double getBuy() {
        return buy;
    }
}