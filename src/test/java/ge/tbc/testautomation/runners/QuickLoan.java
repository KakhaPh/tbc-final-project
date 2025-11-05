package ge.tbc.testautomation.runners;

import ge.tbc.testautomation.steps.HomeSteps;
import ge.tbc.testautomation.steps.QuickAutoLoanSteps;
import org.testng.annotations.BeforeClass;

public class QuickLoan extends BaseTest {

    public HomeSteps homePageSteps;
    public QuickAutoLoanSteps quickAutoLoanSteps;

    @BeforeClass
    public void init() {
        homePageSteps = new HomeSteps(page);
        quickAutoLoanSteps =new QuickAutoLoanSteps(page);
    }
}
