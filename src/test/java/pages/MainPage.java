package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Log4j2
public class MainPage extends BasePage {

    String dateLocator = "//td[contains(@data-date,'%s')]";
    String currency = "//span[contains(text(),'%s')]";

    @FindBy(id = "ss")
    WebElement searchField;

    @FindBy(xpath = "//li[@data-id='currency_selector']//a")
    WebElement currencyButton;

    @FindBy(css = ".sb-searchbox__button ")
    WebElement searchButton;

    @FindBy(xpath = "//div[contains(@data-mode, 'checkout')]")
    WebElement checkoutDate;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        return this;
    }

    public MainPage openPage() {
        log.info("Opening Main page: " + URL);
        driver.get(URL);
        isPageOpened();
        return this;
    }

    public MainPage enterHotel(String hotelName) {
        searchField.sendKeys(hotelName);
        return this;
    }

    public MainPage enterDate(String checkInDate, String checkOutDate) throws InterruptedException {
        checkoutDate.click();
        driver.findElement(By.xpath(String.format(dateLocator, checkInDate))).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath(String.format(dateLocator, checkOutDate))).click();
        return this;
    }

    public SearchHotelsPage clickSearch() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", searchButton);
        return new SearchHotelsPage(driver);
    }

    public MainPage changeCurrency(String requiredCurrency) {
        currencyButton.click();
        driver.findElement(By.xpath(String.format(currency, requiredCurrency))).click();
        return this;
    }

    public MainPage currencyShouldBe(String currency) {

        assertEquals(currencyButton.getText(), currency);
        return this;
    }
}

