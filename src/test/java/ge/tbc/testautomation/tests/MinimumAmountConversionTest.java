package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.CurrenciesBase;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("ფინანსები")
@Feature("ვალუტის შეცვლა")
@Story("მინიმალური თანხის კონვერტაცია [FIN-T3]")
@Owner("კახა ფუტკარაძე")
public class MinimumAmountConversionTest extends CurrenciesBase {

    @Test(description = "ვალუტაში გაწერილი თანხის ოდენობის შეცვლა")
    @Severity(SeverityLevel.MINOR)
    @Description("სწორად დააკონვერტირა თანხა ლარში და მიღებული შედეგი დაამრგვალა ასეულამდე")
    public void enterMinimumAmountInInputField() {
        currenciesSteps
                .enterDifferentAmountInFirstInput(0.01)
                .verifyConversion(Constants.USD, 0.01, false);
    }
}
