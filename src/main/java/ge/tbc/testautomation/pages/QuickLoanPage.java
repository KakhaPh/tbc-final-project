package ge.tbc.testautomation.pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class QuickLoanPage {
    public Page page;

    public  Locator
            quickLoanCalculator,
            byIncomeButton,
            enterincomeField,
            monthlyContribution,
            defaultIncome,
            monthlyContributionWithLoan,
            loanValue,
            enterIncomeFieldWithLoan;


    public QuickLoanPage(Page page) {
        this.page = page;
        quickLoanCalculator=page.locator("//a[@href='/ka/loans/auto-loan/quick-auto-installment']//button[text()='პირობები']");
        byIncomeButton =page.locator("//button[span[contains(., 'შემოსავლით')]]");
        enterincomeField=page.locator("xpath=//input[@id='tbcx-text-input-3' and @type='number']");
        defaultIncome =  page.locator("#tbcx-text-input-3");
        monthlyContribution = page.locator("//div[text()=' ყოველთვიური შენატანი  ']/following-sibling::div[contains(@class,'tbcx-pw-calculated-info__rows-item-info')]");
        monthlyContributionWithLoan = page.locator("div.tbcx-pw-calculated-info__number--old");
        loanValue=page.locator("//div[contains(@class,'tbcx-pw-calculated-info') and contains(text(),' სესხის თანხა ')]/following-sibling::div[1]");
        enterIncomeFieldWithLoan =page.locator("input[type='number'][min='500'][max='150000']");
    }


}
