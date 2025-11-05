package ge.tbc.testautomation.data;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String BASE_URL = "https://www.tbcbank.ge";

    public static final String
            PERSONAL = "ჩემთვის",
            AUTOlOAN="ავტო სესხი",
            DEFAULT_INCOME="1500",
            INCOME = "1300",
            LOAN ="300";

    public static final List<String> POPULAR_CURRENCIES = Arrays.asList("USD", "EUR", "GBP");
    public static final String USD = "USD";
    public static final String EUR = "EUR";
    public static final String GEL = "GEL";

    public static final String EXPECTED_THREE = "Expected 3 popular currency items";
    public static final String NOT_FOUND = "Items Not found";
    public static final String FIRST_DEFAULT_CURRENCY = "Expected first currency USD but found: ";
    public static final String SECOND_DEFAULT_CURRENCY = "Expected second currency GEL but found: ";
    public static final String EXPECTED = "Expected ";
    public static final String BUT_GOT = " but got ";

    public static final int MIN_DAYS_BEFORE_END = 1;
    public static final int MAX_DAYS_BEFORE_END = 30;
    public static final int MIN_RANGE_DAYS = 5;
    public static final int MAX_RANGE_DAYS = 15;
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_RANGE_SEPARATOR = " - ";

    public static final int MODAL_TIMEOUT = 5000;
    public static final int INPUT_TIMEOUT = 3000;
    public static final long DATA_LOAD_WAIT = 2000;

    public static final String ENTER_KEY = "Enter";

    public static final String DATES_NOT_SELECTED_ERROR =
            "Start date or end date is null! Call selectRandomDateRangeBeforeToday() first.";
    public static final String NO_HISTORY_RECORDS_ERROR =
            "No history records found in the table!";
    public static final String ALL_PARSE_ERRORS =
            "Failed to parse any dates! All %d dates had parsing errors. " +
                    "This indicates a problem with the date format or locator.";
    public static final String DATES_OUTSIDE_RANGE_ERROR =
            "Found %d dates outside the selected range [%s to %s]:\n%s";
    public static final String NO_VALID_DATES_ERROR =
            "No valid dates found to verify! Check if data is loading correctly.";
    public static final String UNEXPECTED_DATE_FORMAT =
            "Unexpected date format";
    public static final String UNKNOWN_MONTH_ERROR =
            "Unknown Georgian month: %s";
    public static final String THREAD_INTERRUPTED_ERROR =
            "Thread was interrupted while waiting for data to load";

    public static final String LOG_SELECTED_RANGE = "Selected date range: %s";
    public static final String LOG_START_DATE = "Start date: %s";
    public static final String LOG_END_DATE = "End date: %s";
    public static final String LOG_FOUND_ENTRIES = "Found %d date entries to verify";
    public static final String LOG_EXPECTED_RANGE = "Expected range: %s to %s";
    public static final String LOG_VALID_DATE = "Valid date: %s (from: %s)";
    public static final String LOG_INVALID_DATE = "Date %s is OUTSIDE range [%s to %s]";
    public static final String LOG_PARSE_ERROR = "Failed to parse date: %s - %s";
    public static final String LOG_SUMMARY_HEADER = "\n=== Verification Summary ===";
    public static final String LOG_TOTAL_CHECKED = "Total dates checked: %d";
    public static final String LOG_VALID_COUNT = "Valid dates: %d";
    public static final String LOG_INVALID_COUNT = "Invalid dates: %d";
    public static final String LOG_PARSE_ERRORS = "Parse errors: %d";
    public static final String LOG_SUCCESS = "All %d dates are within the allowed range [%s to %s]";

    private Constants() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }
}