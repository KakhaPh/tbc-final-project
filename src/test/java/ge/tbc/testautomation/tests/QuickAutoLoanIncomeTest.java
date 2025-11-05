package ge.tbc.testautomation.tests;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.HomeSteps;
import ge.tbc.testautomation.steps.QuickAutoLoanSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.math.BigDecimal;

import static ge.tbc.testautomation.data.Constants.*;


@Test(description="შემოსავლის მიხედვით ყოველთვიური შენატანის გამოთვლა [FIN-T7]")
public class QuickAutoLoanIncomeTest extends BaseTest {
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


    @Test(priority = 3, description = "შემოსავლით გადათვლის ტაბზე გადასვლა")
    public void checkDefaultIncomeContribution()  {
        quickAutoLoanSteps
                .clickByIncomeButton()
                .checkDefaultIncomeValues(DEFAULT_INCOME);

        BigDecimal actualContribution = quickAutoLoanSteps.getActualMonthlyContribution();
        BigDecimal expectedContribution = quickAutoLoanSteps.ExpectedMonthlyContribution(DEFAULT_INCOME);
        quickAutoLoanSteps.validateContributionIsCorrect(actualContribution,expectedContribution);


    }

    @Test(priority = 4, description = "შემოსავლის თანხის ოდენობის ცვლილება")
    public void validateContributionForIncomeBelow1500() {
        quickAutoLoanSteps.enterIncome(INCOME);

        BigDecimal value1 = quickAutoLoanSteps.getActualMonthlyContribution();
        BigDecimal value2 = quickAutoLoanSteps.ExpectedMonthlyContribution(INCOME);

        quickAutoLoanSteps.validateContributionIsCorrect(value1,value2);
    }




}