package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {
    public Page page;
    public final Locator headerPersonal;
    public final Locator burgerMenu;

    public BasePage(Page page) {
        this.page = page;
        this.headerPersonal = page.locator("//div[text()=' ჩემთვის ' or text()=' Personal ']");
        this.burgerMenu = page.locator("//tbcx-icon[text()='burger-menu-alt-outlined']");
    }

    public Locator navigationItem(String itemName) {
        return page.locator("//div[contains(@class, 'tbcx-pw-navigation-item__link') and normalize-space(text()) = '" + itemName + "']");
    }

    public Locator megaMenuSubItem(String itemName) {
        return  page.locator("//tbcx-pw-mega-menu-sub-item//span[text() = '" + itemName + "']");
    }


}

