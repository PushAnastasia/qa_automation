package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static business.steps.OrdersSteps.navigateToProductViaSearch;

public class MainPageSuite extends BaseTest {

    @BeforeEach
    public void navigateToPage() {
        mainPage.openMainPage();
    }

    @Test
    public void submitSearchQuery() {
        headerMenu.submitSearchQuery("earphones");
        Assertions.assertTrue(searchResultsPage.isBreadcrumbContainValue("earphones"));
    }

    @Test
    public void openGeolocationMenuAndVerifyElements() {
        mainPage.waitAllElementsLoad();
        headerMenu.verifyGeolocationMenuItems();
    }

    @Test
    public void emailFieldValidationForInvalidInput() {
        mainPage.waitAllElementsLoad();
        mainPage.submitEmailFieldValue("test");
        mainPage.verifyEmailFieldValidationMessage("Будь ласка, вкажіть коректну адресу електронної пошти");
    }

    @Test
    public void emailFieldValidationForEmptyInput() {
        mainPage.waitAllElementsLoad();
        mainPage.submitEmailFieldValue("");
        mainPage.verifyEmailFieldValidationMessage("Це поле є обов'язковим для заповнення.");
    }

    @Test
    public void navigateToCatalogPageViaCategoryLink() {
        String query = "Ноутбуки";
        mainPage.selectSideBarMenuCategory(query);
        mainPage.selectSubCategory(query);
        cataloguePage.verifyCatalogueTitle(query);
    }

    @Test
    public void verifyChangingGeolocation() {
        mainPage.waitAllElementsLoad();
        headerMenu.changeGeolocation("Лозова");
    }

    @Test
    public void cartModalContainsItemAfterClickBuyButton() {
        mainPage.clickBuyButtonByIndex(0);
        cartModal.verifyCartModal();
    }

    @Test
    public void navigateToProductPageViaSearch() {
        navigateToProductViaSearch(driver, "smart TV", 0);
        productPage.verifyPageHeader("TV");
    }

    @Test
    public void putProductIntoCartVerifyCartCount() {
        String query = "Навушники";
        mainPage.selectSideBarMenuCategory(query);
        mainPage.selectSubCategory(query);
        mainPage.clickBuyButtonByIndex(1);
        cartModal.verifyCartModal();
        cartModal.closeCartModal();
        headerMenu.verifyShoppingCartCount("1");
    }
}
