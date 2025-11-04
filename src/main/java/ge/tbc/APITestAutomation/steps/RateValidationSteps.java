package steps;

import helpers.AuthData;
import helpers.AuthHelper;
import models.CommercialRate;
import pages.CommercialRatesListPage;
import pages.ExchangeRatePage;
import java.util.List;

public class RateValidationSteps {
    private final ExchangeRatePage exchangeRatePage = new ExchangeRatePage();
    private final CommercialRatesListPage commercialRatesListPage = new CommercialRatesListPage();
    private final AuthData authData;

    public RateValidationSteps() {
        this.authData = AuthHelper.getAuthTokens();
    }

    public double getSingleBuyRate() {
        return exchangeRatePage.getExchangeRate().getBuyRate();
    }

    public double getLatestCommercialBuyRate() {
        List<CommercialRate> rates = commercialRatesListPage.getCommercialRates(this.authData);
        if (rates == null || rates.isEmpty()) {
            throw new IllegalStateException("Commercial rates list is empty, cannot perform validation.");
        }
        double buyRate = rates.get(0).getBuy();
        System.out.println("\nINFO: Latest commercial 'buy' rate is " + buyRate);
        return buyRate;
    }
}