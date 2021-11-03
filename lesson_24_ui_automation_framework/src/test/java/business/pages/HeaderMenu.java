package business.pages;

import business.conditions.ElementDisplayedConditions;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HeaderMenu extends BasePage {

    @FindBy(id = "search-form__input")
    private WebElement searchField;
    @FindBy(className = "search-form__submit-button")
    private WebElement submitSearchButton;
    @FindBy(className = "geolocation__city-title")
    private WebElement geoLabel;
    @FindBy(className = "geolocation__city")
    private List<WebElement> cityLinks;
    @FindBy(id = "city")
    private WebElement citySearchField;
    @FindBy(css = ".v_custom_select_item")
    private WebElement geoSearchSelectItem;
    @FindBy(xpath = "//div[@class='shopping-cart__icon']//child::*[@class='shopping-cart__count']")
    private WebElement shoppingCartCount;
    @FindBy(className = "shopping-cart__mobile-click")
    private WebElement shoppingCartButton;

    public HeaderMenu(WebDriver driver) {
        super(driver);
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

    public void verifyShoppingCartCount(String count) {
        Assertions.assertEquals(shoppingCartCount.getText(), count);
    }

    public void openCartModal() {
        shoppingCartButton.click();
    }
}
