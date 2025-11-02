package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CookiesPage {
    public final Locator cookieAcceptBtn;

    public CookiesPage(Page page) {
        this.cookieAcceptBtn = page.locator("//tbcx-pw-cookie-consent//button[contains(@class, 'primary')]");
    }
}
