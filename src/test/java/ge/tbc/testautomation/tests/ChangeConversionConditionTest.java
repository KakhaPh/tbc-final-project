package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Finance")
@Feature("Currency Exchange")
@Story("FIN-T4: Switch conversion direction (Sell/Buy)")
@Owner("Kakha Phutkaradze")
public class ChangeConversionConditionTest extends BaseTest {
    @Test(priority = 1, description = "Change conversion direction from Buy to Sell (or vice versa)")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Changes the conversion direction — for example, switches from buying to selling — to validate the conversion toggle functionality.")
    public void changeConversionDirection() {
        currenciesSteps.changeCurrencyConversionCondition();
    }

    @Test(priority = 2, description = "Verify conversion result after switching direction (100 USD)")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that after switching conversion direction, the result is correctly recalculated for 100 USD.")
    public void verifyConversionResultAfterDirectionChange() {
        currenciesSteps.verifyConversion(Constants.USD, 100, true);
    }

    @Test(priority = 3, description = "Verify displayed value below calculator reflects new conversion direction")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensures that the value displayed below the calculator updates correctly after changing conversion direction to reflect the correct rate type.")
    public void verifyDisplayedValueBelowCalculatorAfterDirectionChange() {
        currenciesSteps.checkValueIsCorrectlyIndicatedBelowCalculator(Constants.USD, true);
    }
}