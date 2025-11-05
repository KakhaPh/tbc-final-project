package ge.tbc.testautomation.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommercialRatesResponse {
    private String ccyFrom;
    private String ccyTo;
    private double buy;
    private double sell;

    // Getters & Setters
    public String getCcyFrom() { return ccyFrom; }
    public void setCcyFrom(String ccyFrom) { this.ccyFrom = ccyFrom; }

    public String getCcyTo() { return ccyTo; }
    public void setCcyTo(String ccyTo) { this.ccyTo = ccyTo; }

    public double getBuy() { return buy; }
    public void setBuy(double buy) { this.buy = buy; }

    public double getSell() { return sell; }
    public void setSell(double sell) { this.sell = sell; }
}
