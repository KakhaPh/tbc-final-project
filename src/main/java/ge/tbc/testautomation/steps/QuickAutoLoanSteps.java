package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.QuickLoanPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.math.BigDecimal;

import static ge.tbc.testautomation.helper.LoanHelper.calculateExpectedMonthlyContribution;
import static ge.tbc.testautomation.helper.LoanHelper.parseDecimalFromText;


public class QuickAutoLoanSteps {

    Page page;
    static QuickLoanPage quickLoanPage;

    public QuickAutoLoanSteps(Page page) {
        this.page = page;
        this.quickLoanPage = new QuickLoanPage(page);
    }


    @Step("Choose Quick Loan Calculator")
    public QuickAutoLoanSteps clickOnQuickLoanCalculator() {
        quickLoanPage.quickLoanCalculator.click();
        return this;
    }


    @Step("Click By Income Button")
    public QuickAutoLoanSteps clickByIncomeButton() {
        quickLoanPage.byIncomeButton.click();
        return this;
    }

    @Step("Enter Income: {income}")
    public void enterIncome(String income) {
        quickLoanPage.enterincomeField.click();
        quickLoanPage.enterincomeField.fill(income);
    }

    @Step("Get Actual Monthly Contribution")
    public static BigDecimal getActualMonthlyContribution() {
        String text = quickLoanPage.monthlyContribution.textContent().trim();
        return parseDecimalFromText(text);
    }

    @Step("Validate Contribution is correct")
    public void validateContributionIsCorrect(BigDecimal value, BigDecimal value2) {
        Assert.assertEquals(value, value2);
    }

    @Step("Calculate Expected Monthly Contribution")
    public static BigDecimal ExpectedMonthlyContribution(String income) {
        BigDecimal incomeDecimal = new BigDecimal(income);
        return calculateExpectedMonthlyContribution(incomeDecimal);
    }


    public void checkDefaultIncomeValues(String income) {
        String defaultvalue = quickLoanPage.defaultIncome.inputValue();
        Assert.assertEquals(defaultvalue, income);
    }


    public BigDecimal getMonthlyContributionWithLoan() {

        String text = quickLoanPage.monthlyContributionWithLoan.innerText();
        return parseDecimalFromText(text);
    }


    public BigDecimal getLoanValue() {
        String text = quickLoanPage.loanValue.textContent();
        return parseDecimalFromText(text);
    }

    public void enterLoanAmount(String loanAmount) {
        quickLoanPage.enterIncomeFieldWithLoan.first().fill(loanAmount);
    }

    public QuickAutoLoanSteps validateDefaultLoanIsNotChanged(BigDecimal defaultLoan, BigDecimal loanAfter) {
        Assert.assertEquals(defaultLoan, loanAfter);
        return this;
    }

    public void validateContributionsNotChanged(BigDecimal defaultContribution, BigDecimal contributionAfter) {
        Assert.assertEquals(defaultContribution, contributionAfter);
    }


}




