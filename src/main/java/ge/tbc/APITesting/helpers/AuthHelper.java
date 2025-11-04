package helpers;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class AuthHelper {

    public static AuthData getAuthTokens() {
        System.out.println("--- Starting Auth Token Fetch ---");
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://tbcbank.ge/ka/treasury-products?amount=100&ccyFrom=USD&ccyTo=GEL");
            page.waitForLoadState();
            page.waitForTimeout(3000);

            String xsrfToken = "";
            StringBuilder cookieBuilder = new StringBuilder();

            for (var cookie : context.cookies()) {
                if (cookie.name.equals("XSRF-TOKEN")) {
                    xsrfToken = cookie.value;
                }
                cookieBuilder.append(cookie.name).append("=").append(cookie.value).append("; ");
            }

            browser.close();

            if (xsrfToken.isEmpty()) {
                throw new RuntimeException("CRITICAL: Failed to retrieve XSRF token from the browser session.");
            }

            System.out.println("XSRF Token and Cookies fetched successfully. ✓");
            System.out.println("--- Auth Token Fetch Complete ---");
            return new AuthData(cookieBuilder.toString(), xsrfToken);
        }
    }
}