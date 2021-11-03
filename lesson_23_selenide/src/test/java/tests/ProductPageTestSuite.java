package tests;

import org.testng.annotations.*;

import static pageobject_model.CategoryPage.selectProductByIndex;
import static pageobject_model.HeaderMenu.*;
import static pageobject_model.MainPage.selectCategoryByTitle;
import static pageobject_model.ProductPage.*;
import static pageobject_model.WishListPage.*;

public class ProductPageTestSuite extends BaseTest {

    @Test(dataProvider = "setCategoryAndProduct")
    public void navigateToProductViaCategoryAndPutToCart(String category, int index) {
        selectCategoryByTitle(category);
        selectProductByIndex(index);
        putProductInCart();
        verifyHeaderCartCount("1");
    }

    @Test
    public void increaseProductCountInSideMenuCart() {
        selectCategoryByTitle("Ремни");
        selectProductByIndex(0);
        putProductInCart();
        openSideMenuCart();
        increaseProductCountInSideMenu();
        closeSideMenuCart();
        verifyHeaderCartCount("2");
    }

    @Test
    public void decreaseProductCountInSideMenuCart() {
        selectCategoryByTitle("Сумки женские");
        selectProductByIndex(3);
        putProductInCart();
        openSideMenuCart();
        increaseProductCountInSideMenu();
        decreaseProductCountInSideMenu();
        closeSideMenuCart();
        verifyHeaderCartCount("1");
    }

    @Test
    public void changeProductCountManuallyInSideMenuCart() {
        selectCategoryByTitle("Кошельки и портмоне женские");
        selectProductByIndex(0);
        putProductInCart();
        openSideMenuCart();
        changeProductCountManuallyInSideMenu("3");
        closeSideMenuCart();
        verifyHeaderCartCount("3");
    }

    @Test
    public void removeProductFromSideMenuCart() {
        selectCategoryByTitle("Сумки мужские");
        selectProductByIndex(2);
        putProductInCart();
        openSideMenuCart();
        removeProductFromSideMenu();
        closeSideMenuCart();
        verifyHeaderCartCount("0");
    }

    @Test
    public void putProductIntoCartAndGoToOrderPage() {
        selectCategoryByTitle("Кошельки и портмоне мужские");
        selectProductByIndex(4);
        putProductInCart();
        openSideMenuCart();
        goToCreateOrderFromSideMenuCart();
    }

    @Test(groups = "signedIn")
    public void putProductToWishList() {
        selectCategoryByTitle("Браслеты мужские");
        selectProductByIndex(1);
        putToWishList();
        verifyHeaderWishListCount("1");
    }

    @Test(groups = "signedIn")
    public void navigateToWishListPage() {
        selectCategoryByTitle("Ремни");
        selectProductByIndex(2);
        putToWishList();
        verifyHeaderWishListCount("1");
        openWishListPage();
        verifyWishListProductCount(1);
    }

    @Test
    public void removeSingleItemFromWishList() {
        selectCategoryByTitle("Ремни");
        selectProductByIndex(1);
        putToWishList();
        verifyHeaderWishListCount("1");
        openWishListPage();
        verifyWishListProductCount(1);
        removeItemFromWishListByIndex(0);
        verifyWishListIsEmpty();
    }

    @Test
    public void increaseProductAmountAndPutIntoCart() {
        selectCategoryByTitle("Кошельки и портмоне мужские");
        selectProductByIndex(2);
        increaseProductAmount();
        putProductInCart();
        verifyHeaderCartCount("2");
    }

    @Test
    public void changeProductAmountAndPutIntoCart() throws InterruptedException {
        selectCategoryByTitle("Кошельки и портмоне женские");
        selectProductByIndex(0);
        changeProductAmountManually("3");
        putProductInCart();
        verifyHeaderCartCount("3");
        Thread.sleep(10000);
    }
}
