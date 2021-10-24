package locators;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//*[@id='__layout']/div/div[2]/div[1]/div[1]/h1")
    private WebElement pageHeader;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 15);
        PageFactory.initElements(driver, this);
    }

    public void verifyPageHeader(String value) {
        Assertions.assertTrue(pageHeader.getText().contains(value));
    }
}
