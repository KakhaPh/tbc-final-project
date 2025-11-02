package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {
    public final Locator headerPersonal;
    public final Locator burgerMenu;

    public BasePage(Page page) {
        this.headerPersonal = page.locator("//div[text()=' ჩემთვის ' or text()=' Personal ']");
        this.burgerMenu = page.locator("//tbcx-icon[text()='burger-menu-alt-outlined']");
    }
}

