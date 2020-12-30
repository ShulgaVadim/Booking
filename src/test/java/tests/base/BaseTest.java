package tests.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;


import java.util.concurrent.TimeUnit;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    WebDriver driver;
    public MainPage mainPage;
    public SearchHotelsPage searchHotelsPage;
    public FlightsPage flightsPage;
    public SearchFlightsPage searchFlightsPage;
    public CarRentalsPage carRentalsPage;
    public SearchCarRentalsPage searchCarRentalsPage;


    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest(ITestContext context) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        context.setAttribute("driver", driver);
        mainPage = new MainPage(driver);
        searchHotelsPage = new SearchHotelsPage(driver);
        flightsPage = new FlightsPage(driver);
        searchFlightsPage = new SearchFlightsPage(driver);
        carRentalsPage = new CarRentalsPage(driver);
        searchCarRentalsPage = new SearchCarRentalsPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
