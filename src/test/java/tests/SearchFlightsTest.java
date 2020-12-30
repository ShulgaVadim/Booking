package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class SearchFlightsTest extends BaseTest {

    @Test
    public void searchFlightTest() throws InterruptedException {

        flightsPage
                .openPage()
                .enterAirports("London (LHR)", "New York (JFK)")
                .enterDate("01/21/2021", "02/05/2021")
                .clickSearch();
        searchFlightsPage
                .validateFlightsResults("Thu, Jan 21", "Fri, Feb 5", "LHR - JFK", "JFK - LHR");
    }
}

