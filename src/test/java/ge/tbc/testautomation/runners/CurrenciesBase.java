package ge.tbc.testautomation.runners;

import ge.tbc.testautomation.steps.CurrenciesSteps;
import org.testng.annotations.BeforeClass;

public class CurrenciesBase extends BaseTest {

    protected CurrenciesSteps currenciesSteps;

    @BeforeClass(alwaysRun = true)
    public void openCurrenciesPage() {
        currenciesSteps = new CurrenciesSteps(page);
        currenciesSteps
                .hoverOnHeaderPersonal()
                .openCurrenciesPage();
    }
}
