package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.utils.RetryAnalyzer;
import ge.tbc.testautomation.utils.RetryCount;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Finance")
@Feature("Currency Exchange")
@Story("FIN-T5: Check history of commercial exchange rates for a specified period")
@Owner("Kakha Phutkaradze")
public class CommercialExchangeRateHistoryTest extends BaseTest {

    @Test(priority = 1, description = "Open USD history modal window")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Opens the USD exchange rate history modal for further validation.")
    public void openUSDHistoryModalWindow() {
        currenciesSteps.openUSDHistoryModal();
    }

    @Test(priority = 2, description = "Wait for the history modal to become visible")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Waits until the USD exchange rate history modal is fully loaded and visible.")
    public void waitForHistoryModalToLoad() {
        currenciesSteps.waitForHistoryModal();
    }

    @Test(priority = 3, description = "Select a random valid date range before today")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Selects a random valid date range prior to the current date for verifying exchange rate history data.")
    public void selectRandomValidDateRangeBeforeToday() {
        currenciesSteps.selectRandomDateRangeBeforeToday();
    }

    @Test(priority = 4, description = "Verify that displayed dates are within the selected range", retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @RetryCount(count = 3)
    @Description("Verifies that all displayed commercial exchange rate history entries correspond to the chosen date range.")
    public void verifyDisplayedDatesAreWithinSelectedRange() {
        currenciesSteps.verifyDatesInRange();
    }
}