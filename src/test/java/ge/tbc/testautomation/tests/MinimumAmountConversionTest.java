package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Finance")
@Feature("Currency Exchange")
@Story("Minimum conversion amount validation")
@Severity(SeverityLevel.MINOR)
@Owner("Kakha Phutkaradze")
public class MinimumAmountConversionTest extends BaseTest {
    @Test(description = "Minimum amount conversion [FIN-T3]")
    @Description("Verifies conversion calculation when minimum amount (0.01) is entered.")
    public void minimumAmountConversion() {
        currenciesSteps
                .enterDifferentAmountInFirstInput(0.01)
                .verifyConversion(Constants.USD, 0.01, false);
    }
}