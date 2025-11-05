package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.runners.CurrenciesBase;
import ge.tbc.testautomation.utils.RetryAnalyzer;
import ge.tbc.testautomation.utils.RetryCount;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("ფინანსები")
@Feature("ვალუტის გაცვლა")
@Story("მითითებული პერიოდის კომერციული გაცვლითი კურსების ისტორია [FIN-T5]")
@Owner("კახა ფუტკარაძე")
public class CommercialExchangeRateHistoryTest extends CurrenciesBase {

    @Test(priority = 1, description = "კომერციული გაცვლითი კურსების ისტორიის გვერდზე გადასვლა")
    @Severity(SeverityLevel.CRITICAL)
    @Description("იხსნება არჩეული ვალუტის კომერციული გაცვლითი კურსების ისტორიის გვერდი")
    public void openUSDHistoryModalWindow() {
        currenciesSteps
                .openUSDHistoryModal()
                .waitForHistoryModal();
    }

    @Test(priority = 2, description = "პერიოდის მითითება", retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @RetryCount(count = 3)
    @Description("ისტორიაში ჩანს მხოლოდ მითითებული პერიოდის ჩანაწერები + საწყისი თარიღის წინა დღის ერთი ჩანაწერი")
    public void selectRandomValidateRangeBeforeTodayAndVerifyDateRange() {
        currenciesSteps
                .selectRandomDateRangeBeforeToday()
                .verifyDatesInRange();
    }
}