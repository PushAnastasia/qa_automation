package business.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//*[@id='__layout']/div/div[2]/div[1]/div[1]/h1")
    private WebElement pageHeader;
    @FindBy(id = "product-buy-button")
    private WebElement buyButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void verifyPageHeader(String value) {
        Assertions.assertTrue(pageHeader.getText().contains(value));
    }

    public void clickBuyButton() {
        buyButton.click();
    }
}
