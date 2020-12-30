package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CarRentalsTest extends BaseTest {

    @Test
    public void searchCarTest() {

        carRentalsPage
                .openPage()
                .enterLocation("Phuket")
                .enterDate("January 2021", "28")
                .clickSearch();
        searchCarRentalsPage
                .isPageOpened()
                .validateRentDetails("Phuket Airport (HKT)");

    }
}
