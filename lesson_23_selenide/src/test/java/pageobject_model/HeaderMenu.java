package pageobject_model;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class HeaderMenu {
    static By profileButton = By.id("fm-account-dropdown");
    static By loginModal = By.className("modal-content");
    static By emailField = By.id("emailLoginInput");
    static By passwordField = By.id("passwordLoginInput");
    static By loginButton = By.id("popup-login-button");
    static By alertToast = By.className("alert-block");
    static By alertToastText = By.className("fm-alert-text");
    static By profileMenu = By.cssSelector("#fm-account-dropdown > ul");
    static By logOutButton = By.linkText("Выход");
    static By headerCartCount = By.cssSelector("#cart > span.fm-menu-buttons-index.fm-menu-cart-index");
    static By cartButton = By.id("cart");
    static By sideMenuCart = By.id("fm_sidebar");
    static By sideMenuCartIncreaseButton = By.xpath("html/body/div[2]/div[2]/div[2]/div/div[2]/div/button[2]");
    static By sideMenuCartDecreaseButton = By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div/button[1]");
    static By sideMenuCartCount = By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div/input[4]");
    static By sideMenuCloseButton = By.className("fm_sidebar-title-close");
    static By sideMenuRemoveButton = By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/button");
    static By sideMenuEmptyImage = By.className("fm_mobile_menu_cart-empty");
    static By sideMenuCreateOrderButton = By.linkText("Оформить заказ");
    static By headerWishListCount = By.cssSelector("body > div.menu-row.sticky-top > div > div > div.col-lg-9.fm-menu-buttons-list-col.d-flex.justify-content-between > div.fm-menu-buttons-list.d-flex > button.fm-menu-buttons-item.fm-menu-buttons-wishlist > span > span");
    static By headerWishListButton = By.cssSelector("body > div.menu-row.sticky-top > div > div > div.col-lg-9.fm-menu-buttons-list-col.d-flex.justify-content-between > div.fm-menu-buttons-list.d-flex > button.fm-menu-buttons-item.fm-menu-buttons-wishlist");
    static By logoButton = By.id("logo");

    public static void logIn(String email, String password) {
        Configuration.timeout = 10000;
        $(profileButton).click();
        $(loginModal).should(Condition.visible);
        $(emailField).sendKeys(email);
        $(passwordField).sendKeys(password);
        $(loginButton).click();
    }

    public static void validateAlertToast(String message) {
        $(alertToast).should(Condition.appear);
        $(alertToastText).should(Condition.ownText(message));
    }

    public static void validateProfileMenu() {
        $(profileButton).should(Condition.visible);
        $(profileButton).click();
        $(profileMenu).should(Condition.visible);
        $$(By.className("dropdown-item")).shouldHave(size(5));
    }

    public static void logOut() {
        $(profileButton).click();
        $(profileMenu).should(Condition.visible);
        $(logOutButton).click();
        $("title").shouldHave(attribute("text", "Выход"));
    }

    public static void verifyHeaderCartCount(String count) {
        $(headerCartCount).shouldHave(Condition.ownText(count));
    }

    public static void openSideMenuCart() {
        $(cartButton).click();
        $(sideMenuCart).should(Condition.visible);
    }

    public static void closeSideMenuCart() {
        $(sideMenuCloseButton).click();
        $(sideMenuCart).should(Condition.not(Condition.visible));
    }

    public static void changeProductCount(Boolean increase) {
        int count = Integer.valueOf($(sideMenuCartCount).getValue());
        if (increase) {
            $(sideMenuCartIncreaseButton).click();
            $(sideMenuCartCount).shouldHave(Condition.value(String.valueOf(count + 1)));
        } else {
            $(sideMenuCartDecreaseButton).click();
            $(sideMenuCartCount).shouldHave(Condition.value(String.valueOf(count - 1)));
        }
    }

    public static void increaseProductCountInSideMenu() {
        changeProductCount(true);
    }

    public static void decreaseProductCountInSideMenu() {
        changeProductCount(false);
    }

    public static void changeProductCountManuallyInSideMenu(String newCount) {
        $(sideMenuCartCount).clear();
        $(sideMenuCartCount).sendKeys(newCount);
        $(sideMenuCartCount).click();
        $(sideMenuCartCount).shouldHave(Condition.value(newCount));
    }

    public static void removeProductFromSideMenu() {
        $(sideMenuRemoveButton).click();
        $(sideMenuEmptyImage).should(Condition.visible);
    }

    public static void goToCreateOrderFromSideMenuCart() {
        $(sideMenuCreateOrderButton).click();
        $("title").shouldHave(attribute("text", "Оформление заказа"));
    }

    public static void verifyHeaderWishListCount(String count) {
        $(headerWishListCount).shouldHave(Condition.ownText(count));
    }

    public static void openWishListPage() {
        $(headerWishListButton).click();
    }

    public static void goToMainPageViaLogo() {
        $(logoButton).click();
        $("title").shouldHave(attribute("text", "Кожаные аксессуары Baellerry - официальный магазин в Украине"));
    }
}
