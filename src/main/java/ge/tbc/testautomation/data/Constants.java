package ge.tbc.testautomation.data;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String BASE_URL = "https://www.tbcbank.ge";
    public static final List<String> POPULAR_CURRENCIES = Arrays.asList("USD", "EUR", "GBP");
    public static final String EXPECTED_THREE = "Expected 3 popular currency items";
    public static final String NOT_FOUND = "Items Not found";
    public static final String USD = "USD";
    public static final String EUR = "EUR";
    public static final String GEL = "GEL";

    public static final String FIRST_DEFAULT_CURRENCY = "Expected first currency USD but found: ";
    public static final String SECOND_DEFAULT_CURRENCY = "Expected second currency GEL but found: ";
    public static final String EXPECTED = "Expected ";
    public static final String BUT_GOT = " but got ";
}
