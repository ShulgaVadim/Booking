package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


@Log4j2
public class FlightsPage extends BasePage {

    public static final String FLIGHTS_URL = "https://booking.kayak.com/";
    JavascriptExecutor executor = (JavascriptExecutor) driver;

    @FindBy(xpath = "//div[@class='search-form-inner']//div[@data-placeholder=\"From?\"]")
    WebElement fromInput;

    @FindBy(xpath = "//input[@name='origin']")
    WebElement from;

    @FindBy(xpath = "//div[@class='search-form-inner']//div[@data-placeholder=\"To?\"]")
    WebElement toInput;

    @FindBy(xpath = "//input[@name='destination']")
    WebElement to;

    @FindBy(xpath = "//div[contains(@class,'startDate')]")
    WebElement dates;

    @FindBy(xpath = "//div[contains(@id,'depart-input')]")
    WebElement departDate;

    @FindBy(xpath = "//div[contains(@id,'return-input')]")
    WebElement returnDate;

    @FindBy(xpath = "//div[@class='search-form-inner']//button/span")
    WebElement searchButton;

    @FindBy(xpath = "//div[contains(@id,'origin-airport')]//ul[2]/li[1]")
    WebElement airportsFrom;

    @FindBy(xpath = "//div[contains(@id,'destination-airport')]//ul[2]/li[1]")
    WebElement airportsTo;

    public FlightsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FlightsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        return this;
    }

    public FlightsPage openPage() {
        log.info("Opening Flights page: " + FLIGHTS_URL);
        driver.get(FLIGHTS_URL);
        isPageOpened();
        return this;
    }

    public FlightsPage enterAirports(String fromAirport, String toAirport) {
        fromInput.click();
        from.clear();
        from.sendKeys(fromAirport);
        wait.until(ExpectedConditions.visibilityOf(airportsFrom)).click();

        executor.executeScript("arguments[0].click();", toInput);
        to.clear();
        to.sendKeys(toAirport);
        wait.until(ExpectedConditions.visibilityOf(airportsTo)).click();
        return this;
    }

    public FlightsPage enterDate(String depDate, String retDate) {

        executor.executeScript("arguments[0].click();", dates);
        departDate.clear();
        departDate.sendKeys(depDate);
        returnDate.clear();
        returnDate.sendKeys(retDate);
        return this;
    }

    public SearchFlightsPage clickSearch() {
        searchButton.click();
        return new SearchFlightsPage(driver);
    }
}
