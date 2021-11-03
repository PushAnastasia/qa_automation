package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static business.steps.OrdersSteps.navigateToProductViaCategoryMenu;
import static business.steps.OrdersSteps.navigateToProductViaSearch;

public class ProductPageSuite extends BaseTest {

    @BeforeEach
    public void navigateToPage() {
        mainPage.openMainPage();
    }

    @Test
    public void navigateViaCatalogAndPutProductIntoCart() {
        navigateToProductViaCategoryMenu(driver, "Навушники і акустика", "Акустика", 2);
        productPage.clickBuyButton();
        cartModal.verifyCartModal();
        cartModal.closeCartModal();
        headerMenu.verifyShoppingCartCount("1");
    }

    @Test
    public void navigateViaSearchAndPutProductIntoCart() {
        navigateToProductViaSearch(driver, "мультиварка", 2);
        productPage.clickBuyButton();
        cartModal.verifyCartModal();
        cartModal.closeCartModal();
        headerMenu.verifyShoppingCartCount("1");
    }

    @Test
    public void putProductIntoCartAndIncreaseAmount() {
        navigateToProductViaSearch(driver, "електросамокат", 0);
        productPage.clickBuyButton();
        cartModal.verifyCartModal();
        cartModal.increaseProductsAmountByOne(0);
        cartModal.verifyCartModal();
        cartModal.closeCartModal();
        headerMenu.verifyShoppingCartCount("2");
    }

    @Test
    public void putProductIntoCartAndThanRemove() {
        navigateToProductViaSearch(driver, "Кавомашина", 5);
        productPage.clickBuyButton();
        cartModal.verifyCartModal();
        cartModal.removeItemFromCartByIndex(0);
        cartModal.verifyCartIsEmpty();
    }

    @Test
    public void putProductIntoCartAndGoToOrderPage() {
        navigateToProductViaSearch(driver, "Планшет", 3);
        productPage.clickBuyButton();
        cartModal.verifyCartModal();
        cartModal.openOrderPage();
    }
}
