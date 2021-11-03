package tests;

import business.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.DriverProvider;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;
    MainPage mainPage;
    HeaderMenu headerMenu;
    SearchResultsPage searchResultsPage;
    CataloguePage cataloguePage;
    ProductPage productPage;
    CartModal cartModal;

    @BeforeEach
    public void setUp() {
        DriverProvider provider = new DriverProvider();
        driver = provider.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        headerMenu = new HeaderMenu(driver);
        searchResultsPage = new SearchResultsPage(driver);
        cataloguePage = new CataloguePage(driver);
        productPage = new ProductPage(driver);
        cartModal = new CartModal(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
