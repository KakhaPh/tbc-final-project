package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.helper.DateHelper;
import ge.tbc.testautomation.pages.BasePage;
import ge.tbc.testautomation.pages.CurrenciesPage;
import io.qameta.allure.Step;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import static org.testng.Assert.assertEquals;

public class CurrenciesSteps {
    private final CurrenciesPage currenciesPage;
    private final BasePage basePage;

    private LocalDate startDate;
    private LocalDate endDate;

    public CurrenciesSteps(Page page) {
        this.currenciesPage = new CurrenciesPage(page);
        this.basePage = new BasePage(page);
    }

    @Step("Hover on header Personal menu")
    public CurrenciesSteps hoverOnHeaderPersonal() {
        basePage.headerPersonal.hover();
        return this;
    }

    @Step("Open Currencies page")
    public CurrenciesSteps openCurrenciesPage() {
        currenciesPage.currencyBtn.click();
        return this;
    }

    @Step("Verify popular currencies are displayed")
    public CurrenciesSteps verifyPopularCurrencies() {
        currenciesPage.popularCurrencyItems.first().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));

        int actualCount = currenciesPage.popularCurrencyItems.count();
        assertEquals(actualCount, 3, Constants.EXPECTED_THREE);

        for (String expectedCurrency : Constants.POPULAR_CURRENCIES) {
            boolean found = false;
            for (int i = 0; i < actualCount; i++) {
                String text = currenciesPage.popularCurrencyItems.nth(i).innerText().trim();
                if (text.contains(expectedCurrency)) {
                    currenciesPage.popularCurrencyItems.nth(i)
                            .waitFor(new Locator.WaitForOptions()
                                    .setState(WaitForSelectorState.VISIBLE));
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new AssertionError(Constants.NOT_FOUND + expectedCurrency);
            }
        }
        return this;
    }

    @Step("Verify default currency values (USD and GEL)")
    public CurrenciesSteps verifyDefaultCurrencyValues() {
        String firstCurrency = currenciesPage.currencySelectFirst.innerText().trim();
        String secondCurrency = currenciesPage.currencySelectSecond.innerText().trim();

        if (!firstCurrency.equals(Constants.USD)) {
            throw new AssertionError(Constants.FIRST_DEFAULT_CURRENCY + firstCurrency);
        }
        if (!secondCurrency.equals(Constants.GEL)) {
            throw new AssertionError(Constants.SECOND_DEFAULT_CURRENCY + secondCurrency);
        }
        return this;
    }

    @Step("Verify conversion for {currencyCode} with amount {inputAmount}, reversed: {isReversed}")
    public CurrenciesSteps verifyConversion(String currencyCode, double inputAmount, boolean isReversed) {
        double rate = Double.parseDouble(currenciesPage.buyRate(currencyCode).innerText().trim());

        double expected;
        if (isReversed) {
            expected = inputAmount / (1 / rate);
        } else {
            expected = inputAmount * rate;
        }

        double roundedExpected = Math.round(expected * 100.0) / 100.0;

        double result = 0;
        int tries = 0;

        while (tries < 30) {
            String valueText = currenciesPage.currencyInputTwo.inputValue().trim();
            if (!valueText.isEmpty()) {
                result = Double.parseDouble(valueText);
            }

            if (Math.abs(result - roundedExpected) < 0.001) {
                break;
            }

            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            tries++;
        }

        if (Math.abs(result - roundedExpected) > 0.001) {
            throw new AssertionError(Constants.EXPECTED + roundedExpected + Constants.BUT_GOT + result);
        }

        return this;
    }

    @Step("Check value is correctly indicated below calculator for {currencyCode}, reversed: {isReversed}")
    public void checkValueIsCorrectlyIndicatedBelowCalculator(String currencyCode, boolean isReversed) {
        Locator rateLocator = currenciesPage.buyRate(currencyCode);
        Locator sellRateLocator = currenciesPage.sellRate(currencyCode);

        rateLocator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(3000));

        sellRateLocator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(3000));

        double rate = Double.parseDouble(rateLocator.innerText().trim());
        double sRate = Double.parseDouble(sellRateLocator.innerText().trim());
        double expectedValue = isReversed ? 1 / sRate : rate;

        double actualValue = 0;
        int tries = 0;

        while (tries < 30) {
            String calculatorText = currenciesPage.actualCurrencyPriceField.innerText().trim();
            String[] parts = calculatorText.split(" ");
            if (parts.length >= 4) {
                try {
                    actualValue = Double.parseDouble(parts[3]);
                } catch (NumberFormatException ignored) {}
            }

            if (Math.abs(actualValue - expectedValue) < 0.001) {
                break;
            }

            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            tries++;
        }

        if (Math.abs(actualValue - expectedValue) > 0.001) {
            throw new AssertionError(Constants.EXPECTED + expectedValue + Constants.BUT_GOT + actualValue);
        }

    }

    @Step("Select  currency dropdown")
    public CurrenciesSteps selectOneCurrency() {
        currenciesPage.currencySelectFirst.click();
        return this;
    }

    @Step("Select EUR currency")
    public CurrenciesSteps selectEUR() {
        currenciesPage.selectEur.click();
        return this;
    }

    @Step("Enter amount {amount} ")
    public CurrenciesSteps enterDifferentAmountInFirstInput(double amount) {
        currenciesPage.currencyInputOne.fill(String.valueOf(amount));
        return this;
    }

    @Step("Change currency conversion direction")
    public CurrenciesSteps changeCurrencyConversionCondition() {
        currenciesPage.conditionChangeBtn.click();
        return this;
    }

    @Step("Open USD history ")
    public CurrenciesSteps openUSDHistoryModal() {
        currenciesPage.usdHistoryModal.click();
        return this;
    }

    @Step("Wait for history modal to be visible")
    public void waitForHistoryModal() {
        currenciesPage.usdHistoryModal.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(Constants.MODAL_TIMEOUT));
    }

    @Step("Select random date range before today")
    public CurrenciesSteps selectRandomDateRangeBeforeToday() {
        waitForDateInput();

        LocalDate today = LocalDate.now();
        this.endDate = today.minusDays(generateRandomDays(Constants.MIN_DAYS_BEFORE_END, Constants.MAX_DAYS_BEFORE_END));
        this.startDate = endDate.minusDays(generateRandomDays(Constants.MIN_RANGE_DAYS, Constants.MAX_RANGE_DAYS));

        String dateRange = formatDateRange(startDate, endDate);
        fillDateRange(dateRange);

        logDateSelection(dateRange);

        return this;
    }

    @Step("Verify all dates are within selected range")
    public CurrenciesSteps verifyDatesInRange() {
        validateDateSelection();
        waitForDataLoad();

        List<String> historyDates = fetchHistoryDates();
        validateHistoryData(historyDates);

        DateValidationResult result = validateDates(historyDates);
        logValidationSummary(result);
        handleValidationResult(result);

        return this;
    }
    @Step("Wait for date input field to be visible")
    private void waitForDateInput() {
        currenciesPage.selectHistoryDatesInput.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(Constants.INPUT_TIMEOUT));
    }

    @Step("Generate random days between {min} and {max}")
    private int generateRandomDays(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    @Step("Format date range: {start} - {end}")
    private String formatDateRange(LocalDate start, LocalDate end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        return start.format(formatter) + Constants.DATE_RANGE_SEPARATOR + end.format(formatter);
    }

    @Step("Fill date range input with: {dateRange}")
    private void fillDateRange(String dateRange) {
        currenciesPage.selectHistoryDatesInput.fill(dateRange);
        currenciesPage.selectHistoryDatesInput.press(Constants.ENTER_KEY);
    }

    @Step("Log selected date range: {dateRange}")
    private void logDateSelection(String dateRange) {
        System.out.println(String.format(Constants.LOG_SELECTED_RANGE, dateRange));
        System.out.println(String.format(Constants.LOG_START_DATE, startDate));
        System.out.println(String.format(Constants.LOG_END_DATE, endDate));
    }

    @Step("Validate that dates have been selected")
    private void validateDateSelection() {
        if (startDate == null || endDate == null) {
            throw new IllegalStateException(Constants.DATES_NOT_SELECTED_ERROR);
        }
    }

    @Step("Wait for data to load")
    private void waitForDataLoad() {
        try {
            Thread.sleep(Constants.DATA_LOAD_WAIT);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(Constants.THREAD_INTERRUPTED_ERROR, e);
        }
    }

    @Step("Fetch history dates from table")
    private List<String> fetchHistoryDates() {
        return currenciesPage.historyDateCells.allInnerTexts();
    }

    @Step("Validate history data is not empty")
    private void validateHistoryData(List<String> historyDates) {
        if (historyDates.isEmpty()) {
            throw new AssertionError(Constants.NO_HISTORY_RECORDS_ERROR);
        }
        System.out.println(String.format(Constants.LOG_FOUND_ENTRIES, historyDates.size()));
        System.out.println(String.format(Constants.LOG_EXPECTED_RANGE, startDate, endDate));
    }

    @Step("Validate all dates from history")
    private DateValidationResult validateDates(List<String> historyDates) {
        DateValidationResult result = new DateValidationResult();

        for (String dateText : historyDates) {
            if (dateText == null || dateText.trim().isEmpty()) {
                continue;
            }

            processDateEntry(dateText.trim(), result);
        }

        return result;
    }

    @Step("Process date entry: {dateText}")
    private void processDateEntry(String dateText, DateValidationResult result) {
        try {
            LocalDate parsedDate = parseDateFromText(dateText);

            if (isDateInRange(parsedDate)) {
                result.addValidDate(parsedDate, dateText);
                System.out.println(String.format(Constants.LOG_VALID_DATE, parsedDate, dateText));
            } else {
                result.addInvalidDate(parsedDate, dateText);
                System.err.println(String.format(Constants.LOG_INVALID_DATE, parsedDate, startDate, endDate));
            }
        } catch (Exception e) {
            result.incrementParseErrors();
            System.err.println(String.format(Constants.LOG_PARSE_ERROR, dateText, e.getMessage()));
        }
    }

    @Step("Parse date from text: {dateText}")
    private LocalDate parseDateFromText(String dateText) {
        String datePart = extractDatePart(dateText);
        String[] parts = datePart.split("\\s+");

        if (parts.length < 2) {
            throw new IllegalArgumentException(Constants.UNEXPECTED_DATE_FORMAT);
        }

        int day = Integer.parseInt(parts[0]);
        String monthName = parts[1];

        Integer month = DateHelper.getGeorgianMonth(monthName);
        if (month == null) {
            throw new IllegalArgumentException(String.format(Constants.UNKNOWN_MONTH_ERROR, monthName));
        }

        int year = determineYear(month);
        return LocalDate.of(year, month, day);
    }

    @Step("Extract date part from: {dateText}")
    private String extractDatePart(String dateText) {
        return dateText.split(",")[0].trim();
    }

    @Step("Determine year for month: {month}")
    private int determineYear(int month) {
        if (startDate.getYear() == endDate.getYear()) {
            return startDate.getYear();
        }

        return (month >= startDate.getMonthValue()) ? startDate.getYear() : endDate.getYear();
    }

    @Step("Check if date {date} is in range")
    private boolean isDateInRange(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    @Step("Log validation summary")
    private void logValidationSummary(DateValidationResult result) {
        System.out.println(Constants.LOG_SUMMARY_HEADER);
        System.out.println(String.format(Constants.LOG_TOTAL_CHECKED, result.getTotalChecked()));
        System.out.println(String.format(Constants.LOG_VALID_COUNT, result.getValidCount()));
        System.out.println(String.format(Constants.LOG_INVALID_COUNT, result.getInvalidCount()));
        System.out.println(String.format(Constants.LOG_PARSE_ERRORS, result.getParseErrors()));
    }

    @Step("Handle validation result")
    private void handleValidationResult(DateValidationResult result) {
        if (result.hasParseErrorsOnly()) {
            throw new AssertionError(String.format(
                    Constants.ALL_PARSE_ERRORS,
                    result.getParseErrors()
            ));
        }

        if (result.hasInvalidDates()) {
            throw new AssertionError(String.format(
                    Constants.DATES_OUTSIDE_RANGE_ERROR,
                    result.getInvalidCount(),
                    startDate,
                    endDate,
                    result.getInvalidDatesReport()
            ));
        }

        if (result.hasNoValidDates()) {
            throw new AssertionError(Constants.NO_VALID_DATES_ERROR);
        }

        System.out.println(String.format(
                Constants.LOG_SUCCESS,
                result.getValidCount(),
                startDate,
                endDate
        ));
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    private static class DateValidationResult {
        private final List<String> validDates = new ArrayList<>();
        private final List<String> invalidDates = new ArrayList<>();
        private int parseErrors = 0;
        private int totalChecked = 0;

        public void addValidDate(LocalDate date, String originalText) {
            validDates.add(date.toString());
            totalChecked++;
        }

        public void addInvalidDate(LocalDate date, String originalText) {
            invalidDates.add(date + " (from: " + originalText + ")");
            totalChecked++;
        }

        public void incrementParseErrors() {
            parseErrors++;
            totalChecked++;
        }

        public int getTotalChecked() {
            return totalChecked;
        }

        public int getValidCount() {
            return validDates.size();
        }

        public int getInvalidCount() {
            return invalidDates.size();
        }

        public int getParseErrors() {
            return parseErrors;
        }

        public boolean hasParseErrorsOnly() {
            return parseErrors > 0 && validDates.isEmpty();
        }

        public boolean hasInvalidDates() {
            return !invalidDates.isEmpty();
        }

        public boolean hasNoValidDates() {
            return validDates.isEmpty();
        }

        public String getInvalidDatesReport() {
            return String.join("\n", invalidDates);
        }
    }
}