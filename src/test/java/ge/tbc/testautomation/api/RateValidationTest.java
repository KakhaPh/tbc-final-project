
package ge.tbc.testautomation.api;
import ge.tbc.testautomation.api.model.CommercialRatesRequest;
import ge.tbc.testautomation.api.model.CommercialRatesResponse;
import ge.tbc.testautomation.api.model.PublicRateItem;
import ge.tbc.testautomation.api.step.ExchangeSteps;
import ge.tbc.testautomation.runners.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import static ge.tbc.testautomation.data.Constants.EUR;
import static ge.tbc.testautomation.data.Constants.GEL;
import static ge.tbc.testautomation.helper.LoanHelper.getCookieAndToken;

public class RateValidationTest extends BaseTest {

    ExchangeSteps steps = new ExchangeSteps();
    double publicEurBuy;
    double euroBuyRate;

    @BeforeClass
    public void getCookiesAndToken(){
        steps.goToTbcPage(page);
        getCookieAndToken(browserContext);
    }


    @Test(priority = 1)
    public void getEuroRate() {
        PublicRateItem response=  steps.sendRequest1();
        publicEurBuy = response.getBuyRate();

    }

    @Test(priority = 2)
    public void getLatestEuroRateFromHistoryAndValidate() {
        CommercialRatesRequest requestBody =steps.createRequestBodyForCommercialRates(EUR,GEL);
        List<CommercialRatesResponse> responses = steps.sendCommercialRatesRequest(requestBody);
        euroBuyRate = steps.getLatestResponseBuyRate(responses);
        Assert.assertEquals(euroBuyRate,publicEurBuy);

    }


}