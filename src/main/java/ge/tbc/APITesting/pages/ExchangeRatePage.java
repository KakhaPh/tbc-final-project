package pages;

import data.Constants;
import io.restassured.RestAssured;
import models.ExchangeRate;
import static io.restassured.RestAssured.given;

public class ExchangeRatePage {
    public ExchangeRate getExchangeRate() {
        System.out.println("\nREQUEST: Fetching single exchange rate...");
        return given()
                .baseUri(Constants.BASE_URL)
                .when()
                .get(Constants.EXCHANGE_RATE_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .as(ExchangeRate.class);
    }
}