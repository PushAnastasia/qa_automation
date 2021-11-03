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

public class MainPage extends BasePage {

    @FindBy(id = "catalog-social-block-email")
    private WebElement emailField;
    @FindBy(className = "social-subscription__button")
    private WebElement subscribeButton;
    @FindBy(css = ".validation_advice > span > span")
    private WebElement emailFieldValidationMessage;
    @FindBy(xpath = "//p[contains(@class, 'item__title')]")
    private List<WebElement> sideBarMenuCategories;
    @FindBy(className = "portal-group__title-link")
    private List<WebElement> subCategoryLinks;
    @FindBy(css = ".buy-button")
    private List<WebElement> buyButtons;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openMainPage() {
        driver.get("https://allo.ua/");
        String pageTitle = driver.getTitle();
        Assertions.assertTrue(pageTitle
                .startsWith("Інтернет магазин мобільних телефонів, ноутбуків, телевізорів"));
    }

    public void waitAllElementsLoad() {
        wait.until(new ElementDisplayedConditions());
    }

    public void submitEmailFieldValue(String value) {
        emailField.click();
        emailField.sendKeys(value);
        emailField.click();
        subscribeButton.click();
    }

    public void verifyEmailFieldValidationMessage(String expectedMessage) {
        wait.until(ExpectedConditions.visibilityOf(emailFieldValidationMessage));
        Assertions.assertTrue(emailFieldValidationMessage.getText().equals(expectedMessage));
    }

    public void selectSideBarMenuCategory(String category) {
        WebElement selectedCategory = sideBarMenuCategories.stream().filter(s -> s.getText().contains(category))
                .findFirst().orElseThrow(RuntimeException::new);
        selectedCategory.click();
        wait.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(By.className("portal-group")).isDisplayed();
            }
        });
    }

    public void selectSubCategory(String subCategory) {
        wait.until(ExpectedConditions.visibilityOfAllElements(subCategoryLinks));
        WebElement selectedSubCategory = subCategoryLinks.stream().filter(s -> s.getText().contains(subCategory))
                .findFirst().orElseThrow(RuntimeException::new);
        selectedSubCategory.click();
        wait.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(By.className("products-layout__container")).isDisplayed();
            }
        });
    }

    public void clickBuyButtonByIndex(int index) {
        buyButtons.get(index).click();
    }
}
