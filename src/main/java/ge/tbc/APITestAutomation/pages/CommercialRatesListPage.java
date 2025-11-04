package pages;

import io.restassured.common.mapper.TypeRef;
import data.Constants;
import helpers.AuthData;
import models.CommercialRate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static io.restassured.RestAssured.given;

public class CommercialRatesListPage {


    public List<CommercialRate> getCommercialRates(AuthData authData) {
        System.out.println("\nREQUEST: Fetching commercial rates list...");

        String endDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String startDate = LocalDate.now().minusDays(7).format(DateTimeFormatter.ISO_LOCAL_DATE);

        String payload = String.format("""
            {
              "queryModel": {
                "ccyFrom": ["USD"],
                "ccyTo": ["GEL"],
                "startDate": "%s",
                "endDate": "%s"
              }
            }
        """, startDate, endDate);

        // Using RestAssured to make the authenticated POST request and directly deserialize into a List of POJOs.
        return given()
                .baseUri(Constants.BASE_URL)
                .contentType("application/json")
                .header("cookie", authData.getCookies())
                .header("origin", "https://tbcbank.ge")
                .header("referer", "https://tbcbank.ge/")
                .header("x-xsrf-token", authData.getXsrfToken())
                .body(payload)
                .log().headers()
                .when()
                .post(Constants.COMMERCIAL_RATES_ENDPOINT)
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .as(new TypeRef<List<CommercialRate>>() {});
    }
}