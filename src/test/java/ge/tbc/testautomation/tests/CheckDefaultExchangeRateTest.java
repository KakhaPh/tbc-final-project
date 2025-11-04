package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Finance")
@Feature("Currency Exchange")
@Story("Verify default exchange rate on load")
@Severity(SeverityLevel.CRITICAL)
@Owner("Kakha Phutkaradze")
public class CheckDefaultExchangeRateTest extends BaseTest {

    @Test(description = "Check the default exchange rate [FIN-T1]")
    @Description("Verifies that the default popular currencies and exchange rate are correctly shown on load.")
    public void checkTheDefaultExchangeRate() {
        currenciesSteps
                .verifyPopularCurrencies()
                .verifyDefaultCurrencyValues()
                .verifyConversion(Constants.USD, 100, false)
                .checkValueIsCorrectlyIndicatedBelowCalculator(Constants.USD, false);
    }
}