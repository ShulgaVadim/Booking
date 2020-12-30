package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class SortHotelsTest extends BaseTest {

    @Test
    public void sortHotelsTest() throws InterruptedException {

        mainPage
                .openPage()
                .enterHotel("Milan")
                .enterDate("2021-01-20", "2021-01-21")
                .clickSearch();
        searchHotelsPage
                .isPageOpened()
                .clickSort()
                .validateSortingPrice();
    }
}
