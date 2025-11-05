package ge.tbc.testautomation.ui;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.Currencies;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("ფინანსები")
@Feature("ვალუტის შეცვლა")
@Story("მინიმალური თანხის კონვერტაცია [FIN-T3]")
@Owner("კახა ფუტკარაძე")
public class MinimumAmountConversionTest extends Currencies {

    @Test(description = "ვალუტაში გაწერილი თანხის ოდენობის შეცვლა")
    @Severity(SeverityLevel.MINOR)
    @Description("სწორად დააკონვერტირა თანხა ლარში და მიღებული შედეგი დაამრგვალა ასეულამდე")
    public void enterMinimumAmountInInputField() {
        currenciesSteps
                .enterDifferentAmountInFirstInput(0.01)
                .verifyConversion(Constants.USD, 0.01, false);
    }
}
