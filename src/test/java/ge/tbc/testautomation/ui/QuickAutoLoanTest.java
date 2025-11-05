package ge.tbc.testautomation.ui;

import ge.tbc.testautomation.runners.QuickLoan;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import java.math.BigDecimal;

import static ge.tbc.testautomation.data.Constants.*;

@Epic("ფინანსები")
@Feature("სწარფი ავტო სესხი")
@Story("სესხის თანხა მინიმალურ ზღვარზე ნაკლებია [FIN-T6]")
@Owner("მარიამ უბირია")
public class QuickAutoLoanTest extends QuickLoan {

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