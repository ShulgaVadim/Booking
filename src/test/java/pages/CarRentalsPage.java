package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


@Log4j2
public class CarRentalsPage extends BasePage {

    public static final String CARS_URL = URL + "/cars";
    String dateLocator = "//th[contains(text(),'%s')]/ancestor::table//span[contains(text(),'%s')]";

    @FindBy(css = ".sb-searchbox__button ")
    WebElement searchButton;

    @FindBy(id = "ss_origin")
    WebElement locationInput;

    @FindBy(xpath = "//ul[contains(@role,'listbox')]/li[1]")
    WebElement city;

    @FindBy(xpath = "//button[contains(@aria-label,'Open calendar')]")
    WebElement calendar;

    public CarRentalsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CarRentalsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        return this;
    }

    public CarRentalsPage openPage() {
        log.info("Opening Car Rentals page: " + CARS_URL);
        driver.get(CARS_URL);
        isPageOpened();
        return this;
    }

    public CarRentalsPage enterLocation(String location) {
        locationInput.sendKeys(location);
        wait.until(ExpectedConditions.visibilityOf(city)).click();
        return this;
    }

    public CarRentalsPage enterDate(String checkInMonthYear, String checkInDay) {
        calendar.click();
        driver.findElement(By.xpath(String.format(dateLocator, checkInMonthYear, checkInDay))).click();
        return this;
    }

    public SearchCarRentalsPage clickSearch() {
        searchButton.click();
        return new SearchCarRentalsPage(driver);
    }
}
