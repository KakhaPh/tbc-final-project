package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import org.testng.annotations.Test;

public class CurrencyExchangeTests extends BaseTest {

    @Test(description = "Check the default exchange rate [FIN-T1]")
    public void checkTheDefaultExchangeRate() {
        currenciesSteps
                .verifyPopularCurrencies()
                .verifyDefaultCurrencyValues()
                .verifyConversion(Constants.USD, 100, false)
                .checkValueIsCorrectlyIndicatedBelowCalculator(Constants.USD, false);
    }

    @Test(description = "Updating the conversion result after changing the currency and amount [FIN-T2]")
    public void updatingTheConversationResultAfterChangingTheCurrencyAndAmount() {
        currenciesSteps
                .selectOneCurrency()
                .selectEUR()
                .verifyConversion(Constants.EUR, 100, false)
                .checkValueIsCorrectlyIndicatedBelowCalculator(Constants.EUR, false)
                .enterDifferentAmountInFirstInput(200)
                .verifyConversion(Constants.EUR, 200, false);
    }

    @Test(description = "Minimum amount conversion [FIN-T3]")
    public void minimumAmountConversion() {
        currenciesSteps
                .enterDifferentAmountInFirstInput(0.01)
                .verifyConversion(Constants.USD, 0.01, false);
    }

    @Test(description = "Change conversion condition [FIN-T4]")
    public void changeConversionCondition() {
        currenciesSteps
                .changeCurrencyConversionCondition()
                .verifyConversion(Constants.USD, 100, true)
                .checkValueIsCorrectlyIndicatedBelowCalculator(Constants.USD, true);
    }
}
