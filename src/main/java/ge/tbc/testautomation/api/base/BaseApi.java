package ge.tbc.testautomation.api.base;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static ge.tbc.testautomation.api.data.ConstantsAPI.*;
import static ge.tbc.testautomation.helper.LoanHelper.cookies;
import static ge.tbc.testautomation.helper.LoanHelper.xsrfToken;

public class BaseApi {

    public static final RequestSpecification REQ_SPEC_FOR_LIST = new RequestSpecBuilder()
            .setBaseUri(BASE_URL_EXCHANGE)
            .setContentType("application/json")
            .addHeader("cookie", cookies)
            .addHeader("origin", ORIGIN_URL)
            .addHeader("x-xsrf-token", xsrfToken)
            .build();


    public static final RequestSpecification REQ_SPEC_FOR_EUR = new RequestSpecBuilder()
            .setBaseUri(BASE_URL_EXCHANGE)
            .setBasePath(BASE_PATH_EXCHANGE_RATE)
            .setContentType("application/json")
            .build();

}

