package locators;

import conditions.ElementDisplayedConditions;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HeaderMenu {

    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "search-form__input")
    private WebElement searchField;
    @FindBy(className = "search-form__submit-button")
    private WebElement submitSearchButton;
    @FindBy(className = "geolocation__city-title")
    private WebElement geoLabel;
    @FindBy(className = "geolocation__city")
    private List<WebElement> cityLinks;
    @FindBy(id = "city" )
    private WebElement citySearchField;
    @FindBy(css = ".v_custom_select_item")
    private WebElement geoSearchSelectItem;
    @FindBy(css = ".authentication__button--login")
    private WebElement loginButton;
    @FindBy(id = "auth")
    private WebElement loginField;
    @FindBy(id = "v-login-password")
    private WebElement passwordField;
    @FindBy(css = ".modal-submit-button")
    private WebElement submitLoginButton;
    @FindBy(css = ".validation-advice")
    private WebElement loginValidationMessage;
    @FindBy(xpath = "//div[@class='shopping-cart__icon']//child::*[@class='shopping-cart__count']")
    private WebElement shoppingCartCount;

    public HeaderMenu(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 15);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void submitSearchQuery(String query) {
        searchField.sendKeys(query);
        submitSearchButton.click();
        WebElement breadCrumbs = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".breadcrumbs__link > b")));
        String pageTitle = driver.getTitle();
        Assertions.assertTrue(pageTitle.startsWith("Результати пошуку для"));
    }

    public void verifyGeolocationMenuItems() {
        geoLabel.click();
        wait.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(By.className("geolocation__content")).isDisplayed();
            }
        });
        System.out.println("Menu contains " + cityLinks.size() + " cities.");
        Assertions.assertTrue(cityLinks.size() == 6);
    }

    public void changeGeolocation(String city) {
        geoLabel.click();
        citySearchField.clear();
        citySearchField.sendKeys(city);
        wait.until(ExpectedConditions.visibilityOf(geoSearchSelectItem));
        geoSearchSelectItem.click();
        wait.until(new ElementDisplayedConditions());
        geoLabel = driver.findElement(By.className("geolocation__city-title"));
        Assertions.assertTrue(geoLabel.getText().startsWith(city));
    }

    public void submitLoginPassword(String login, String password) {
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(loginField));
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        submitLoginButton.click();
    }

    public void verifyLoginValidationMessage(String message) {
        Assertions.assertTrue(loginValidationMessage.getText().equals(message));
    }

    public void verifyShoppingCartCount(String count) {
        Assertions.assertEquals(shoppingCartCount.getText(), count);

    }
}
