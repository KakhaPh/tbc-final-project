package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.CurrenciesBase;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("ფინანსები")
@Feature("ვალუტის შეცვლა")
@Story("კონვერტაციის შედეგის განახლება ვალუტის და თანხის ოდენობის ცვლილების შემდეგ [FIN-T2]")
@Owner("კახა ფუტკარაძე")
public class UpdateConversionResultTest extends CurrenciesBase {

    @Test(priority = 1, description = "ვალუტის შეცვლა")
    @Severity(SeverityLevel.CRITICAL)
    @Description("სწორად შეიცვალა ლარში დაკოვნერტირებული თანხის ოდენობა ევროს კურსის მიხედვით" +
            "კალკულატორის ქვემოთ სწორად შეიცვალა მითითებული 1 EUR-ს ღირებულება ლარში")
    public void currencyChange() {
        currenciesSteps
                .selectOneCurrency()
                .selectEUR()
                .verifyConversion(Constants.EUR, 100, false)
                .checkValueIsCorrectlyIndicatedBelowCalculator(Constants.EUR, false);
    }

    @Test(priority = 2, description = "ვალუტაში დეფოლტად გაწერილი თანხის ოდენობის ცვლილება")
    @Severity(SeverityLevel.CRITICAL)
    @Description("ვალუტაში გაწერილი თანხის ოდენობა სწორად დააკონვერტირა ლარში კომერციული კურსის მიხედვით")
    public void changeDefaultCommercialExchangeRate() {
        currenciesSteps
                .enterDifferentAmountInFirstInput(200)
                .verifyConversion(Constants.EUR, 200, false);
    }
}