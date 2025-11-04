package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Finance")
@Feature("Currency Exchange")
@Story("FIN-T1: Default Exchange Rate Verification")
@Owner("Kakha Phutkaradze")
public class CheckDefaultExchangeRateTest extends BaseTest {

    @Test(priority = 1, description = "Verify popular currencies section is displayed on page load")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validates that the popular currencies section is visible when the currency exchange page loads")
    public void verifyPopularCurrenciesAreDisplayed() {
        currenciesSteps.verifyPopularCurrencies();
    }

    @Test(priority = 2, description = "Verify default currency values match expected rates")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validates that the default currency values displayed match the expected exchange rates")
    public void verifyDefaultCurrencyValuesAreCorrect() {
        currenciesSteps.verifyDefaultCurrencyValues();
    }

    @Test(priority = 3, description = "Verify conversion calculation for 100 USD is accurate")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validates that converting 100 USD produces the correct calculated result")
    public void verifyUsdConversionCalculationIsAccurate() {
        currenciesSteps.verifyConversion(Constants.USD, 100, false);
    }

    @Test(priority = 4, description = "Verify conversion result is correctly displayed below calculator")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validates that the USD conversion result is properly indicated below the calculator widget")
    public void verifyConversionResultIsDisplayedBelowCalculator() {
        currenciesSteps.checkValueIsCorrectlyIndicatedBelowCalculator(Constants.USD, false);
    }
}