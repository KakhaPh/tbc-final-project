package ge.tbc.testautomation.api.step;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.api.base.ClientApi;
import ge.tbc.testautomation.api.model.CommercialRatesRequest;
import ge.tbc.testautomation.api.model.CommercialRatesResponse;
import ge.tbc.testautomation.api.model.PublicRateItem;
import ge.tbc.testautomation.api.model.QueryModel;
import io.restassured.common.mapper.TypeRef;
import java.time.LocalDate;
import java.util.List;
import static ge.tbc.testautomation.api.data.ConstantsAPI.DEFAULT_EXCHANGE_PAGE_URL;

public class ExchangeSteps {

    LocalDate today = LocalDate.now();
    LocalDate threeDaysAgo = today.minusDays(3);

    public  void goToTbcPage(Page page){
        page.navigate(DEFAULT_EXCHANGE_PAGE_URL);
        page.waitForTimeout(2000);
    }

    public CommercialRatesRequest createRequestBodyForCommercialRates(String from, String to){
        QueryModel query = new QueryModel();

        query.setCcyFrom(List.of(from));
        query.setCcyTo(List.of(to));
        query.setStartDate(threeDaysAgo.toString());
        query.setEndDate(today.toString());

        CommercialRatesRequest request = new CommercialRatesRequest();
        request.setTop(200);
        request.setSkip(0);
        request.setQueryModel(query);

        return request ;
    }

    public List<CommercialRatesResponse> sendCommercialRatesRequest(CommercialRatesRequest request) {

        return ClientApi.getCommercialRatesList(request)
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<CommercialRatesResponse>>() {});

    }

    public double getLatestResponseBuyRate(List<CommercialRatesResponse> responses){
        return responses.get(0).getBuy();
    }


    public PublicRateItem sendRequest1() {

        return  ClientApi.getEuroRate()
                .then()
                .statusCode(200)
                .extract()
                .as(PublicRateItem.class);
    }



}
