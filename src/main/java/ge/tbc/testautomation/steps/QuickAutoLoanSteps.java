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

    @Step("Validate Contribution Is Correct")
    public void validateContributionIsCorrect(BigDecimal value, BigDecimal value2) {
        Assert.assertEquals(value, value2);
    }

    @Step("Calculate Expected Monthly Contribution from Income: {income}")
    public static BigDecimal ExpectedMonthlyContribution(String income) {
        BigDecimal incomeDecimal = new BigDecimal(income);
        return calculateExpectedMonthlyContribution(incomeDecimal);
    }

    @Step("Check that default income value equals: {income}")
    public void checkDefaultIncomeValues(String income) {
        String defaultvalue = quickLoanPage.defaultIncome.inputValue();
        Assert.assertEquals(defaultvalue, income);
    }

    @Step("Get Monthly Contribution With Loan")
    public BigDecimal getMonthlyContributionWithLoan() {
        String text = quickLoanPage.monthlyContributionWithLoan.innerText();
        return parseDecimalFromText(text);
    }

    @Step("Get Loan Value")
    public BigDecimal getLoanValue() {
        String text = quickLoanPage.loanValue.textContent();
        return parseDecimalFromText(text);
    }

    @Step("Enter Loan Amount: {loanAmount}")
    public void enterLoanAmount(String loanAmount) {
        quickLoanPage.enterIncomeFieldWithLoan.first().fill(loanAmount);
    }

    @Step("Validate Default Loan Value Has Not Changed")
    public QuickAutoLoanSteps validateDefaultLoanIsNotChanged(BigDecimal defaultLoan, BigDecimal loanAfter) {
        Assert.assertEquals(defaultLoan, loanAfter);
        return this;
    }

    @Step("Validate Contributions Have Not Changed")
    public void validateContributionsNotChanged(BigDecimal defaultContribution, BigDecimal contributionAfter) {
        Assert.assertEquals(defaultContribution, contributionAfter);
    }
}
