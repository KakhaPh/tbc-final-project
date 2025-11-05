
package ge.tbc.testautomation.api;
import ge.tbc.testautomation.api.model.CommercialRatesRequest;
import ge.tbc.testautomation.api.model.CommercialRatesResponse;
import ge.tbc.testautomation.api.model.PublicRateItem;
import ge.tbc.testautomation.api.step.ExchangeSteps;
import ge.tbc.testautomation.runners.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import static ge.tbc.testautomation.data.Constants.EUR;
import static ge.tbc.testautomation.data.Constants.GEL;
import static ge.tbc.testautomation.helper.LoanHelper.getCookieAndToken;

@Epic("ფინანსები")
@Feature("ვალუტის გაცვლა")
@Story("ვალუტის მიმდინარე და ისტორიული კურსის  შემოწმება [FIN-T8]")
@Owner("დავით ეგოიანი")
public class RateValidationTest extends BaseTest {

    ExchangeSteps exchangeSteps = new ExchangeSteps();
    double euroBuyRate;
    double latestEuroBuyRate;
    List<CommercialRatesResponse> responsesHistory;

    @BeforeClass
    public void getCookiesAndToken(){
        exchangeSteps.goToTbcPage(page);
        getCookieAndToken(browserContext);
    }


    @Test(priority = 1,description = " მიმდინარე დღის ევროს ყიდვის კურსისთვის რექუესტის გაგზავნა")
    public void getEuroRate() {
        PublicRateItem response=  exchangeSteps.sendRequest1();
        euroBuyRate = response.getBuyRate();
    }

    @Test(priority = 2,description = " ბოლო სამი დღის ევროს ევროს კურსისთვის რექუესტის გაგზავნა")
    public void getEuroRateFromHistory() {
        CommercialRatesRequest requestBody = exchangeSteps.createRequestBodyForCommercialRates(EUR,GEL);
        responsesHistory = exchangeSteps.sendCommercialRatesRequest(requestBody);
    }

    @Test(priority = 3,description = " მიმდინარე დღის კურსის და ბოლო ცვლილების ჩანაწერის ვალიდაცია")
    public void getLatestAndValidate() {
        latestEuroBuyRate = exchangeSteps.getLatestResponseBuyRate(responsesHistory);
        exchangeSteps.validateCurrency(euroBuyRate, latestEuroBuyRate);
    }


}