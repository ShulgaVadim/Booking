package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class FilterTest extends BaseTest {

    @Test
    public void priceFilterTest() throws InterruptedException {
        mainPage
                .openPage()
                .enterHotel("Paris")
                .enterDate("2021-01-20", "2021-01-21")
                .clickSearch();
        searchHotelsPage
                .isPageOpened()
                .chooseYourBudget(156, 313)
                .validatePriceFiltering(156, 313);
    }

    @Test
    public void ratingFilterTest() throws InterruptedException {
        mainPage
                .openPage()
                .enterHotel("London")
                .enterDate("2021-01-20", "2021-01-21")
                .clickSearch();
        searchHotelsPage
                .isPageOpened()
                .chooseRequiredRating(5)
                .validateRatingFiltering(5);
    }
}
