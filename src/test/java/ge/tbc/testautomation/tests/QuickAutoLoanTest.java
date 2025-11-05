package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.HomeSteps;
import ge.tbc.testautomation.steps.QuickAutoLoanSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.math.BigDecimal;

import static ge.tbc.testautomation.data.Constants.*;


@Test(description="სესხის თანხა მინიმალურ ზღვარზე ნაკლებია [FIN-T6]")
public class QuickAutoLoanTest extends BaseTest {
    HomeSteps homePageSteps;
    QuickAutoLoanSteps quickAutoLoanSteps;

    @BeforeClass
    public void init() {
        homePageSteps = new HomeSteps(page);
        quickAutoLoanSteps =new QuickAutoLoanSteps(page);
    }


    @Test(priority = 1, description = "ავტო სესხის გვერდზე გადასვლა")
    public void hoverToMegaMenu() {
        homePageSteps
                .hoverToMegaMenu(PERSONAL)
                .navigateToKeySection(AUTOlOAN);
    }



    @Test(priority = 2, description = "სწრაფი ავტოგანვადების გვერდზე გადასვლა")
    public void clickOnQuickLoanCalculator() {
        quickAutoLoanSteps
                .clickOnQuickLoanCalculator();
    }


    @Test(priority = 3, description = "კალკულატორში სესხის თანხის ცვლილება")
    public void checkDefaultIncomeContribution()  {

        BigDecimal defaultContribution = quickAutoLoanSteps.getMonthlyContributionWithLoan();
        BigDecimal defaultLoan = quickAutoLoanSteps.getLoanValue();

        quickAutoLoanSteps.enterLoanAmount(LOAN);

        BigDecimal contributionAfter = quickAutoLoanSteps.getMonthlyContributionWithLoan();
        BigDecimal loanAfter = quickAutoLoanSteps.getLoanValue();

        quickAutoLoanSteps
                .validateDefaultLoanIsNotChanged(defaultContribution, contributionAfter)
                .validateContributionsNotChanged(defaultLoan, loanAfter);

    }





}