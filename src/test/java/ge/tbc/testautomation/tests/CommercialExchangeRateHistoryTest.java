package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.utils.RetryAnalyzer;
import ge.tbc.testautomation.utils.RetryCount;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Finance")
@Feature("Currency Exchange")
@Story("Check history of commercial exchange rates for a specified period")
@Owner("Kakha Phutkaradze")
public class CommercialExchangeRateHistoryTest extends BaseTest {
    @Severity(SeverityLevel.NORMAL)
    @RetryCount(count = 3)
    @Test(description = "Check commercial exchange rate history for a specific date range [FIN-T5]", retryAnalyzer = RetryAnalyzer.class)
    @Description("Verify that the commercial exchange rate history is displayed correctly for the given date range.")
    public void changeConversionCondition() {
        currenciesSteps
                .openUSDHistoryModal()
                .waitForHistoryModal()
                .selectRandomDateRangeBeforeToday()
                .verifyDatesInRange();
    }
}