package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Finance")
@Feature("Currency Exchange")
@Story("Update conversion after changing currency and amount")
@Severity(SeverityLevel.NORMAL)
@Owner("Kakha Phutkaradze")
public class UpdateConversionResultTest extends BaseTest {

    @Test(description = "Updating the conversion result after changing the currency and amount [FIN-T2]")
    @Description("Checks that conversion result is correctly updated when currency and amount are changed.")
    public void updatingTheConversionResultAfterChangingTheCurrencyAndAmount() {
        currenciesSteps
                .selectOneCurrency()
                .selectEUR()
                .verifyConversion(Constants.EUR, 100, false)
                .checkValueIsCorrectlyIndicatedBelowCalculator(Constants.EUR, false)
                .enterDifferentAmountInFirstInput(200)
                .verifyConversion(Constants.EUR, 200, false);
    }
}