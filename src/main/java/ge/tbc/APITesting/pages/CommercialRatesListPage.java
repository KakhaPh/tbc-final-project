package pages;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.Constants;
import helpers.AuthData;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import models.CommercialRate;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
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

        // Use RestAssured to make the authenticated POST request
        List<Map<String, Object>> responseData = given()
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
                .as(new TypeRef<List<Map<String, Object>>>() {});

        Gson gson = new Gson();
        String json = gson.toJson(responseData);
        Type listType = new TypeToken<List<CommercialRate>>(){}.getType(); // This line was causing the error
        return gson.fromJson(json, listType);
    }
}