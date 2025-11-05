package ge.tbc.testautomation.api.base;

import ge.tbc.testautomation.api.model.CommercialRatesRequest;
import io.restassured.response.Response;
import static ge.tbc.testautomation.api.base.BaseApi.REQ_SPEC_FOR_EUR;
import static ge.tbc.testautomation.api.base.BaseApi.REQ_SPEC_FOR_LIST;
import static ge.tbc.testautomation.api.data.ConstantsAPI.COMMERCIAL_LIST_ENDPOINT;
import static ge.tbc.testautomation.api.data.ConstantsAPI.EURO_RATE_ENDPOINT;
import static io.restassured.RestAssured.given;

public class ClientApi {


    public static Response getCommercialRatesList(CommercialRatesRequest requestBody) {
        return given()
                .spec(REQ_SPEC_FOR_LIST)
                .body(requestBody)
                .log().all()
                .post(COMMERCIAL_LIST_ENDPOINT);
    }


    public static Response getEuroRate( ) {
        return given()
                .spec(REQ_SPEC_FOR_EUR)
                .log().all()
                .get(EURO_RATE_ENDPOINT);
    }

}
