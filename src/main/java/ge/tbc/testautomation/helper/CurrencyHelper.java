package ge.tbc.testautomation.helper;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CurrencyHelper {
    private final Page page;

    public CurrencyHelper(Page page) {
        this.page = page;
    }

    public Locator buyRate(String currencyCode) {
        return page.locator("tbcx-pw-popular-currency-item")
                .filter(new Locator.FilterOptions().setHasText(currencyCode))
                .locator("div.tbcx-pw-popular-currencies__col")
                .filter(new Locator.FilterOptions().setHasText("ყიდვა"))
                .locator("div.tbcx-pw-popular-currencies__body")
                .first();
    }

    public Locator sellRate(String currencyCode) {
        return page.locator("tbcx-pw-popular-currency-item")
                .filter(new Locator.FilterOptions().setHasText(currencyCode))
                .locator("div.tbcx-pw-popular-currencies__col")
                .filter(new Locator.FilterOptions().setHasText("გაყიდვა"))
                .locator("div.tbcx-pw-popular-currencies__body")
                .first();
    }
}