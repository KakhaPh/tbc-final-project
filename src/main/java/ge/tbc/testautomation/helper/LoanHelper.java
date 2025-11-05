package ge.tbc.testautomation.helper;

import com.microsoft.playwright.Page;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LoanHelper {
    protected final Page page;
    public static String xsrfToken;
    public static String cookies;

    public LoanHelper(Page playwrightPage) {
        this.page = playwrightPage;
    }

    public static BigDecimal parseDecimalFromText(String text) {
        String parsed = text.replaceAll("[^0-9.,]", "")
                .replaceAll(",", "");
        if (parsed.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(parsed);
    }

    public static BigDecimal calculateExpectedMonthlyContribution(BigDecimal incomeDecimal) {
        BigDecimal percent = incomeDecimal.compareTo(new BigDecimal("1500")) < 0
                ? new BigDecimal("0.25")   // 25%
                : new BigDecimal("0.50");  // 50%
        BigDecimal result = incomeDecimal.multiply(percent);

        if (result.stripTrailingZeros().scale() <= 0) {
            return result.setScale(0); // 374.00 → 374
        } else {
            return result.setScale(2, RoundingMode.DOWN);

        }
    }

    public static void getCookieAndToken (com.microsoft.playwright.BrowserContext context){
        var cookieList = context.cookies();
        StringBuilder cookieBuilder = new StringBuilder();

        for (var cookie : cookieList) {
            if (cookie.name.equals("XSRF-TOKEN")) xsrfToken = cookie.value;
            cookieBuilder.append(cookie.name).append("=").append(cookie.value).append("; ");
        }

        cookies = cookieBuilder.toString();
        if (xsrfToken == null || xsrfToken.isEmpty()) {
            throw new RuntimeException("XSRF token not found!");
        }
    }
}
