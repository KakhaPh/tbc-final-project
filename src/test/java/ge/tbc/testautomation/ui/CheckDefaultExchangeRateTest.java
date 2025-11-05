package ge.tbc.testautomation.ui;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.CurrenciesSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("ფინანსები")
@Feature("ვალუტის გაცვლა")
@Story("ნაგულისხმევი ვალუტის კურსის შემოწმება [FIN-T1]")
@Owner("კახა ფუტკარაძე")
public class CheckDefaultExchangeRateTest extends BaseTest {
    @BeforeClass
    public void init() {
        currenciesSteps = new CurrenciesSteps(page);
    }

    @Test(priority = 1, description = "ვებ გვერდზე შესვლა")
    @Severity(SeverityLevel.CRITICAL)
    @Description("იხსნება TBC მთავარ გვერდზე")
    public void openTbcMainPage() {
        page.navigate(Constants.BASE_URL);
    }

    @Test(priority = 2, description = "ჩემი შემოთავაზებების მენიუ გახსნა")
    @Severity(SeverityLevel.CRITICAL)
    @Description("იხსნება ჩემი შემოთავაზებების მენიუ")
    public void openPersonalMenu() {
        currenciesSteps.hoverOnHeaderPersonal();
    }

    @Test(priority = 3, description = "ვალუტის კურსების გვერდზე გადასვლა")
    @Severity(SeverityLevel.CRITICAL)
    @Description("იხსნება კომერციული ვალლუტის გვერდი, 3 პოპულარული ვალუტის კურსით, default-ად USD/GEL, სწორად არის " +
            "დაკონვერტირებული და კალკულატორის ქვევით სწორად არის მითითებული 1 USD-ს ღირებულება ლარში")
    public void openAndValidateCommercialCurrencyRatePage() {
        currenciesSteps
                .openCurrenciesPage()
                .verifyPopularCurrencies()
                .verifyDefaultCurrencyValues()
                .verifyConversion(Constants.USD, 100, false)
                .checkValueIsCorrectlyIndicatedBelowCalculator(Constants.USD, false);
    }
}