package ge.tbc.testautomation.runners;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.CookiesSteps;
import ge.tbc.testautomation.steps.CurrenciesSteps;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;

@Listeners({AllureTestNg.class})
public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    public CookiesSteps cookiesSteps;
    public CurrenciesSteps currenciesSteps;

    @Parameters({"browser", "headless", "mobile"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chromium") String browserName,
                      @Optional("false") String headlessParam,
                      @Optional("false") String mobileParam) {
        playwright = Playwright.create();
        boolean headless = Boolean.parseBoolean(headlessParam);
        boolean mobile = Boolean.parseBoolean(mobileParam);

        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            default:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        }

        if (mobile) {
            Browser.NewContextOptions opts = new Browser.NewContextOptions()
                    .setViewportSize(375, 812)
                    .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 16_0 like Mac OS X)...")
                    .setIsMobile(true)
                    .setHasTouch(true);
            browserContext = browser.newContext(opts);
        } else {
            browserContext = browser.newContext();
        }

        page = browserContext.newPage();

        page.navigate(Constants.BASE_URL);
        cookiesSteps = new CookiesSteps(page);
        cookiesSteps.acceptAllCookies();
    }

    @BeforeMethod
    public void setContext() {
        page.navigate(Constants.BASE_URL);

        currenciesSteps = new CurrenciesSteps(page);
        currenciesSteps
                .hoverOnHeaderPersonal()
                .openCurrenciesPage();
    }

    @AfterClass
    public void tearDown() {
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}