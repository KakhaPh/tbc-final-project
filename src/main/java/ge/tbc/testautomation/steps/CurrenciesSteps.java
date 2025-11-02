package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.BasePage;
import ge.tbc.testautomation.pages.CurrenciesPage;
import static org.testng.Assert.assertEquals;

public class CurrenciesSteps {
    private final CurrenciesPage currenciesPage;
    private final BasePage basePage;

    public CurrenciesSteps(Page page) {
        this.currenciesPage = new CurrenciesPage(page);
        this.basePage = new BasePage(page);
    }

    public CurrenciesSteps hoverOnHeaderPersonal() {
        basePage.headerPersonal.hover();
        return this;
    }

    public void openCurrenciesPage() {
        currenciesPage.currencyBtn.click();
    }

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

    public CurrenciesSteps checkValueIsCorrectlyIndicatedBelowCalculator(String currencyCode, boolean isReversed) {
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

        return this;
    }


    public CurrenciesSteps selectOneCurrency() {
        currenciesPage.currencySelectFirst.click();
        return this;
    }

    public CurrenciesSteps selectEUR() {
        currenciesPage.selectEur.click();
        return this;
    }

    public CurrenciesSteps enterDifferentAmountInFirstInput(double amount) {
        currenciesPage.currencyInputOne.fill(String.valueOf(amount));
        return this;
    }

    public CurrenciesSteps changeCurrencyConversionCondition() {
        currenciesPage.conditionChangeBtn.click();
        return this;
    }

    //    public void checkIfGelValueIsCorrectlyWrittenBelowCalculator() {
    //
    //    }

    //    public CurrenciesSteps verifyConversion(String currencyCode, double inputAmount) {
    //        double rate = Double.parseDouble(currenciesPage.buyRate(currencyCode).innerText().trim());
    //        double result = Double.parseDouble(currenciesPage.currencyInputTwo.inputValue().trim());
    //
    //        double expected = inputAmount * rate;
    //
    //        if (Math.abs(result - expected) > 0.001) {
    //            throw new AssertionError(Constants.EXPECTED + expected + Constants.BUT_GOT + result);
    //        }
    //        return this;
    //    }

    //    public CurrenciesSteps verifyConversion(String currencyCode, double inputAmount) {
    //        double rate = Double.parseDouble(currenciesPage.buyRate(currencyCode).innerText().trim());
    //        double expected = inputAmount * rate;
    //
    //        double roundedExpected = Math.round(expected * 100.0) / 100.0;
    //
    //        double result = 0;
    //        int tries = 0;
    //
    //        while (tries < 30) {
    //            String valueText = currenciesPage.currencyInputTwo.inputValue().trim();
    //            if (!valueText.isEmpty()) {
    //                result = Double.parseDouble(valueText);
    //            }
    //
    //            if (Math.abs(result - roundedExpected) < 0.001) {
    //                break;
    //            }
    //
    //            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
    //            tries++;
    //        }
    //
    //        if (Math.abs(result - roundedExpected) > 0.001) {
    //            throw new AssertionError(Constants.EXPECTED + roundedExpected + Constants.BUT_GOT + result);
    //        }
    //
    //        return this;
    //    }

    //    public CurrenciesSteps checkValueIsCorrectlyIndicatedBelowCalculator(String currencyCode) {
    //        Locator rateLocator = currenciesPage.buyRate(currencyCode);
    //        rateLocator.waitFor(new Locator.WaitForOptions()
    //                .setState(WaitForSelectorState.VISIBLE)
    //                .setTimeout(3000));
    //
    //        double expectedValue = Double.parseDouble(rateLocator.innerText().trim());
    //
    //        double actualValue = 0;
    //        int tries = 0;
    //        while (tries < 30) {
    //            String calculatorText = currenciesPage.actualCurrencyPriceField.innerText().trim();
    //            String[] parts = calculatorText.split(" ");
    //            actualValue = Double.parseDouble(parts[3]);
    //
    //            if (Math.abs(actualValue - expectedValue) < 0.001) break;
    //
    //            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
    //            tries++;
    //        }
    //
    //        if (Math.abs(actualValue - expectedValue) > 0.001) {
    //            System.out.println(Constants.EXPECTED + expectedValue + Constants.BUT_GOT + actualValue);
    //        }
    //
    //        return this;
    //    }
}
