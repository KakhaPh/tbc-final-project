package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Finance")
@Feature("Currency Exchange")
@Story("FIN-T3: Minimum conversion amount validation")
@Owner("Kakha Phutkaradze")
public class MinimumAmountConversionTest extends BaseTest {

    @Test(priority = 1, description = "Enter the minimum allowed amount (0.01) in the input field")
    @Severity(SeverityLevel.MINOR)
    @Description("Enters the minimum possible amount (0.01) into the first input field to test conversion handling of small values")
    public void enterMinimumAmountInInputField() {
        currenciesSteps.enterDifferentAmountInFirstInput(0.01);
    }

    @Test(priority = 2, description = "Verify conversion result for the minimum amount (0.01 USD)")
    @Severity(SeverityLevel.MINOR)
    @Description("Validates that the conversion result is correctly calculated and displayed for the minimum amount (0.01 USD)")
    public void verifyConversionForMinimumAmount() {
        currenciesSteps.verifyConversion(Constants.USD, 0.01, false);
    }
}
