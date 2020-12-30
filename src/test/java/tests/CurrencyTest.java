package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CurrencyTest extends BaseTest {

    @Test
    public void changeCurrencyTest() {
        mainPage
                .openPage()
                .changeCurrency("Mexican Peso")
                .currencyShouldBe("MXN");
    }
}
