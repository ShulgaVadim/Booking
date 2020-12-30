package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class SearchHotelsTest extends BaseTest {

    @Test
    public void searchHotel() throws InterruptedException {

        mainPage
                .openPage()
                .enterHotel("Minsk Marriott Hotel")
                .enterDate("2021-01-25", "2021-01-26")
                .clickSearch();
        searchHotelsPage
                .isPageOpened()
                .searchPageShouldContain("Minsk Marriott Hotel");
    }
}


////
////        searchHotelsPage
////                .openPage()
////                .searchHotel("Marriott Minsk")
////                .searchPageShouldContain("Minsk Marriott Hotel");
////    }

