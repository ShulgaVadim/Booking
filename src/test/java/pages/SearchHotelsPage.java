package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchHotelsPage extends BasePage {

    String checkboxBudget = "//span[contains(text(),'BYN %s – BYN %s')]/ancestor::label";
    String checkboxRating = "//a[@data-id=\"class-%s\"]";

    @FindBy(xpath = "//a[contains(text(),'Price (lowest first)')]")
    WebElement sortPriceButton;

    @FindBy(css = ".sr-hotel__name")
    List<WebElement> hotelsNames;

    @FindBy(css = ".bui-price-display__value")
    List<WebElement> prices;

    @FindBy(css = ".bui-rating")
    List<WebElement> ratingValues;

    public SearchHotelsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchHotelsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(sortPriceButton));
        return this;
    }

    public boolean searchPageShouldContain(String hotelName) {
        boolean shouldContain = false;

        for (int i = 0; i < hotelsNames.size(); i++) {
            if (hotelsNames.get(i).getText().equals(hotelName)) {
                shouldContain = true;
            }
        }
        return shouldContain;
    }

    public SearchHotelsPage clickSort() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", sortPriceButton);
        Thread.sleep(5000);
        return this;
    }

    public SearchHotelsPage validateSortingPrice() {

        ArrayList<String> priceValues = new ArrayList<String>();
        for (int i = 0; i < prices.size(); i++) {
            String str = prices.get(i).getText();
            priceValues.add(str);
        }

        ArrayList<String> referenceValues = new ArrayList<String>();
        for (int i = 0; i < priceValues.size(); i++) {
            referenceValues.add(priceValues.get(i));
        }
        Collections.sort(referenceValues);
        assert referenceValues.equals(priceValues);
        return this;
    }

    public SearchHotelsPage chooseYourBudget(int lowPrice, int highPrice) throws InterruptedException {
        driver.findElement(By.xpath(String.format(checkboxBudget, lowPrice, highPrice))).click();
        Thread.sleep(5000);
        return this;
    }

    public SearchHotelsPage validatePriceFiltering(int lowPrice, int highPrice) {
        boolean a = false;
        for (int i = 0; i < prices.size(); i++) {
            int rating = Integer.parseInt(prices.get(i).getText().substring(4));

            if (rating >= lowPrice && rating <= highPrice) {
                a = true;
            }
            assertTrue(a);
        }
        return this;
    }

    public SearchHotelsPage chooseRequiredRating(int rating) throws InterruptedException {
        driver.findElement(By.xpath(String.format(checkboxRating, rating))).click();
        Thread.sleep(5000);
        return this;
    }

    public SearchHotelsPage validateRatingFiltering(int numberOfStars) {

        for (int i = 0; i < ratingValues.size(); i++) {
            String value = ratingValues.get(i).getAttribute("aria-label");
            int rating = Integer.parseInt(value.substring(0, 1));
            assertEquals(rating, numberOfStars);
        }
        return this;
    }
}
