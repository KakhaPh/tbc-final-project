package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.BasePage;
import io.qameta.allure.Step;

public class HomeSteps extends BasePage {

    public HomeSteps(Page page) {
        super(page);
    }

    @Step("Hover over mega menu section: {megaMenuSectionName}")
    public HomeSteps hoverToMegaMenu(String megaMenuSectionName) {
        navigationItem(megaMenuSectionName).hover();
        return this;
    }


    @Step("Navigate to key section: {keySectionName}")
    public HomeSteps navigateToKeySection(String keySectionName) {
        megaMenuSubItem(keySectionName).click();
        return this;
    }
}
