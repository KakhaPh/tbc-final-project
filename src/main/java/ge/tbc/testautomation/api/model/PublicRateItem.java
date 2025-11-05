package ge.tbc.testautomation.api.model;
import com.fasterxml.jackson.annotation.JsonProperty;
public class PublicRateItem {

    @JsonProperty("iso1")
    private String iso1;

    @JsonProperty("iso2")
    private String iso2;

    private double buyRate;
    private double sellRate;
    private int conversionType;

    @JsonProperty("currencyWeight")
    private double currencyWeight;

    private String updateDate;

    // Getters & Setters
    public String getIso1() { return iso1; }
    public void setIso1(String iso1) { this.iso1 = iso1; }

    public String getIso2() { return iso2; }
    public void setIso2(String iso2) { this.iso2 = iso2; }

    public double getBuyRate() { return buyRate; }
    public void setBuyRate(double buyRate) { this.buyRate = buyRate; }

    public double getSellRate() { return sellRate; }
    public void setSellRate(double sellRate) { this.sellRate = sellRate; }

    public int getConversionType() { return conversionType; }
    public void setConversionType(int conversionType) { this.conversionType = conversionType; }

    public double getCurrencyWeight() { return currencyWeight; }
    public void setCurrencyWeight(double currencyWeight) { this.currencyWeight = currencyWeight; }

    public String getUpdateDate() { return updateDate; }
    public void setUpdateDate(String updateDate) { this.updateDate = updateDate; }
}
