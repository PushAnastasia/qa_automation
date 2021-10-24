package tests;

import locators.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainPageTestSuite {

    WebDriver driver;
    MainPage mainPage;
    HeaderMenu headerMenu;
    SearchResultsPage searchResultsPage;
    CataloguePage cataloguePage;
    ProductPage productPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        headerMenu = new HeaderMenu(driver);
        searchResultsPage = new SearchResultsPage(driver);
        cataloguePage = new CataloguePage(driver);
        productPage = new ProductPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void submitSearchQuery() {
        mainPage.openMainPage();
        headerMenu.submitSearchQuery("earphones");
        Assertions.assertTrue(searchResultsPage.isBreadcrumbContainValue("earphones"));
    }

    @Test
    public void openGeolocationMenuAndVerifyElements() {
        mainPage.openMainPage();
        mainPage.waitAllElementsLoad();
        headerMenu.verifyGeolocationMenuItems();
    }

    @Test
    public void emailFieldValidationForInvalidInput() {
        mainPage.openMainPage();
        mainPage.waitAllElementsLoad();
        mainPage.submitEmailFieldValue("test");
        mainPage.verifyEmailFieldValidationMessage("Будь ласка, вкажіть коректну адресу електронної пошти");
    }

    @Test
    public void emailFieldValidationForEmptyInput() {
        mainPage.openMainPage();
        mainPage.waitAllElementsLoad();
        mainPage.submitEmailFieldValue("");
        mainPage.verifyEmailFieldValidationMessage("Це поле є обов'язковим для заповнення.");
    }

    @Test
    public void navigateToCatalogPageViaCategoryLink() {
        String query = "Ноутбуки";
        mainPage.openMainPage();
        mainPage.selectSideBarMenuCategory(query);
        mainPage.selectSubCategory(query);
        cataloguePage.verifyCatalogueTitle(query);
    }

    @Test
    public void verifyChangingGeolocation() {
        mainPage.openMainPage();
        mainPage.waitAllElementsLoad();
        headerMenu.changeGeolocation("Лозова");
    }

    @Test
    public void verifyLoginValidationMessage() {
        mainPage.openMainPage();
        headerMenu.submitLoginPassword("test", "test");
        headerMenu.verifyLoginValidationMessage("Будь ласка, вкажіть коректну адресу електронної пошти");
    }

    @Test
    public void cartModalContainsItemAfterClickBuyButton() {
        mainPage.openMainPage();
        mainPage.clickBuyButtonByIndex(0);
        mainPage.verifyCartModal();
    }

    @Test
    public void navigateToProductPageViaSearch() {
        mainPage.openMainPage();
        headerMenu.submitSearchQuery("smart TV");
        searchResultsPage.selectResultsItemByIndex(0);
        productPage.verifyPageHeader("TV");
    }

    @Test
    public void putProductIntoCartVerifyCartCount() {
        String query = "Навушники";
        mainPage.openMainPage();
        mainPage.selectSideBarMenuCategory(query);
        mainPage.selectSubCategory(query);
        mainPage.clickBuyButtonByIndex(1);
        mainPage.verifyCartModal();
        mainPage.closeCartModal();
        headerMenu.verifyShoppingCartCount("1");
    }
}
