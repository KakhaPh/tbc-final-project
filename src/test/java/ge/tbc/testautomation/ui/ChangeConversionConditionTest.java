package ge.tbc.testautomation.ui;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.Currencies;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("ფინანსები")
@Feature("ვალუტის გაცვლა")
@Story("კონვერტაციის პირობის შეცვლა [FIN-T4]")
@Owner("კახა ფუტკარაძე")
public class ChangeConversionConditionTest extends Currencies {

    @Test(priority = 1, description = "კონვერტაციის პირობის შეცვლა")
    @Severity(SeverityLevel.CRITICAL)
    @Description("კალკულატორის ზედა და ქვედა ველებში შეიცვალა ვალუტის და ლარის ადგილები" +
            "არსებული კომერციული კურსის მიხედვით სწორად გადაითვალა თანხა" +
            "კალკულატორის ქვემოთ სწორად არის მითითებული 1 GEL-ს ღირებულება ვალუტაში")
    public void changeConversionDirection() {
        currenciesSteps
                .changeCurrencyConversionCondition()
                .verifyConversion(Constants.USD, 100, true)
                .checkValueIsCorrectlyIndicatedBelowCalculator(Constants.USD, true);
    }
}