package business.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartModal extends BasePage {
    @FindBy(css = ".v-modal__cmp-body")
    private WebElement cartModal;
    @FindBy(className = "product__item")
    private List<WebElement> cartProductItems;
    @FindBy(xpath = "//div[@class='v-modal__close-btn']")
    private WebElement cartModalCloseButton;
    @FindBy(className = "qty__up")
    private List<WebElement> increaseAmountButtons;
    @FindBy(css = ".qty__count > input")
    private List<WebElement> productQuantityFields;
    @FindBy(css = ".cart-popup__content > div > div > div > ul > li > div > div > div.content > div.title > svg > use")
    private WebElement removeItemButton;
    @FindBy(className = "cart-popup_empty")
    private WebElement emptyCart;
    @FindBy(className = "order-now")
    private WebElement createOrderButton;

    public CartModal(WebDriver driver) {
        super(driver);
    }

    public void verifyCartModal() {
        wait.until(ExpectedConditions.visibilityOf(cartModal));
        Assertions.assertTrue(cartModal.isDisplayed());
        wait.until(ExpectedConditions.visibilityOfAllElements(cartProductItems));
        Assertions.assertTrue(cartProductItems.size() > 0);
    }

    public void closeCartModal() {
        wait.until(ExpectedConditions.elementToBeClickable(cartModalCloseButton));
        cartModalCloseButton.click();
    }

    public void increaseProductsAmountByOne(int productIndex) {
        increaseAmountButtons.get(productIndex).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(cartProductItems));
    }

    public void removeItemFromCartByIndex(int index) {
        removeItemButton.click();
    }

    public void verifyCartIsEmpty() {
        wait.until(ExpectedConditions.visibilityOf(emptyCart));
    }

    public void openOrderPage() {
        createOrderButton.click();
        Assertions.assertEquals(driver.getTitle(), "Оформлення замовлення – інтернет-магазин ALLO.ua!");
    }
}
