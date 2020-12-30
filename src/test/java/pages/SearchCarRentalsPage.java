package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SearchCarRentalsPage extends BasePage {

    @FindBy(xpath = "//tbody//div[@class='ab-SearchSummary_PickUp_City']")
    WebElement location;

    @FindBy(xpath = "//div[contains(@class, 'carResultDiv')]")
    List<WebElement> results;


    public SearchCarRentalsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchCarRentalsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElements(results));
        return this;
    }

    public SearchCarRentalsPage validateRentDetails(String expLocation) {
        assertEquals(location.getText(), expLocation);
        return this;
    }
}
