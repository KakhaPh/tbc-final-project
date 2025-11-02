package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.CookiesPage;

public class CookiesSteps {
    private final CookiesPage cookiesPage;

    public CookiesSteps(Page page) {
        this.cookiesPage = new CookiesPage(page);
    }

    public void acceptAllCookies() {
        if (cookiesPage.cookieAcceptBtn.isHidden()) {
            cookiesPage.cookieAcceptBtn.click();
        }
    }
}
