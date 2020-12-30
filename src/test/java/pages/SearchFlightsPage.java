package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class SearchFlightsPage extends BasePage {

    @FindBy(css = ".resultInner")
    List<WebElement> results;

    @FindBy(xpath = "//div[@class='section-content '][1]//div[@class='content-card']/descendant::div[4]")
    List<WebElement> departDates;

    @FindBy(xpath = "//div[@class='section-content '][2]//div[@class='content-card']/descendant::div[4]")
    List<WebElement> returnDates;

    @FindBy(xpath = "//div[@class='section-content '][1]//span[@class='origin-destination']")
    List<WebElement> departFlights;

    @FindBy(xpath = "//div[@class='section-content '][2]//span[@class='origin-destination']")
    List<WebElement> returnFlights;

    public SearchFlightsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchFlightsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElements(results));
        return this;
    }

    public SearchFlightsPage validateFlightsResults(String departDate, String returnDate, String departFlight, String returnFlight) throws InterruptedException {
        Thread.sleep(10000);

        for (int i = 0; i < results.size(); i++) {

            assertEquals(departDates.get(i).getAttribute("innerText").substring(1, 12), departDate);
            assertEquals(returnDates.get(i).getAttribute("innerText").substring(1, 11), returnDate);
            assertEquals(departFlights.get(i).getAttribute("innerText").substring(1, 10), departFlight);
            assertEquals(returnFlights.get(i).getAttribute("innerText").substring(1, 10), returnFlight);
        }
        return this;
    }
}

