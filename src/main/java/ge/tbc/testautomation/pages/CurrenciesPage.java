package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CurrenciesPage {
    private final Page page;

    public final Locator currencyBtn;
    public final Locator popularCurrencyItems;
    public final Locator openSelectCurrency;
    public final Locator selectEur;
    public final Locator currencyInputOne;
    public final Locator currencyInputTwo;
    public final Locator currencySelectFirst;
    public final Locator currencySelectSecond;
    public final Locator actualCurrencyPriceField;
    public final Locator usdBuyActualValue;
    public final Locator eurBuyActualValue;
    public final Locator gbpBuyActualValue;
    public final Locator conditionChangeBtn;

    public CurrenciesPage(Page page) {
        this.page = page;
        this.currencyBtn = page.locator("//tbcx-pw-mega-menu//a[contains(@href, '/treasury-products')]");
        this.popularCurrencyItems = page.locator("tbcx-pw-popular-currency-item");
        this.openSelectCurrency = page.locator("//tbcx-dropdown-selector");
        this.selectEur = page.locator("//tbcx-dropdown-popover-item//div[contains(text(), 'EUR')]");
        this.currencyInputOne = page.locator("//input[@id='tbcx-text-input-1']");
        this.currencyInputTwo = page.locator("//input[@id='tbcx-text-input-2']");
        this.currencySelectFirst = page.locator("//tbcx-input-with-selector[@formcontrolname='iso1']//tbcx-dropdown-selector");
        this.currencySelectSecond = page.locator("//tbcx-input-with-selector[@formcontrolname='iso2']//tbcx-dropdown-selector");
        this.usdBuyActualValue = buyRate("USD");
        this.eurBuyActualValue = buyRate("EUR");
        this.gbpBuyActualValue = buyRate("GBP");
        this.actualCurrencyPriceField = page.locator("//div[@class='tbcx-pw-exchange-rates-calculator__description ng-star-inserted']");
        this.conditionChangeBtn = page.locator("//tbcx-icon[@class='ng-star-inserted' and contains(text(), 'swap-alt-outlined')]");
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
