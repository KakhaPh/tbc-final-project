package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Finance")
@Feature("Currency Exchange")
@Story("FIN-T2: Update conversion after changing currency and amount")
@Owner("Kakha Phutkaradze")
public class UpdateConversionResultTest extends BaseTest {

    @Test(priority = 1, description = "Select one currency from the list")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Selects a single currency option to begin conversion testing")
    public void selectOneCurrency() {
        currenciesSteps.selectOneCurrency();
    }

    @Test(priority = 2, description = "Select EUR as the active currency")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Changes the selected currency to EUR for conversion")
    public void selectEURCurrency() {
        currenciesSteps.selectEUR();
    }

    @Test(priority = 3, description = "Verify conversion result for 100 EUR")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validates that the conversion result is correct when converting 100 EUR")
    public void verifyConversionFor100EUR() {
        currenciesSteps.verifyConversion(Constants.EUR, 100, false);
    }

    @Test(priority = 4, description = "Verify value is displayed correctly below calculator for 100 EUR")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensures the value below the calculator is properly indicated for 100 EUR conversion")
    public void verifyDisplayedValueBelowCalculatorFor100EUR() {
        currenciesSteps.checkValueIsCorrectlyIndicatedBelowCalculator(Constants.EUR, false);
    }

    @Test(priority = 5, description = "Enter a different amount (200) into the input field")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Inputs a new amount of 200 EUR into the first input field for updated conversion")
    public void enterDifferentAmountInFirstInput() {
        currenciesSteps.enterDifferentAmountInFirstInput(200);
    }

    @Test(priority = 6, description = "Verify conversion result updates correctly for 200 EUR")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checks that the conversion result updates accurately when the amount is changed to 200 EUR")
    public void verifyUpdatedConversionFor200EUR() {
        currenciesSteps.verifyConversion(Constants.EUR, 200, false);
    }
}