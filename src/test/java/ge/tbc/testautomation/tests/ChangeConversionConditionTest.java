package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Finance")
@Feature("Currency Exchange")
@Story("Switch conversion direction (Sell/Buy)")
@Severity(SeverityLevel.CRITICAL)
@Owner("Kakha Phutkaradze")
public class ChangeConversionConditionTest extends BaseTest {

    @Test(description = "Change conversion condition [FIN-T4]")
    @Description("Checks conversion result when switching conversion condition (e.g., from buy to sell).")
    public void changeConversionCondition() {
        currenciesSteps
                .changeCurrencyConversionCondition()
                .verifyConversion(Constants.USD, 100, true)
                .checkValueIsCorrectlyIndicatedBelowCalculator(Constants.USD, true);
    }
}