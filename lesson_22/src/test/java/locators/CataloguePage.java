package locators;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CataloguePage {

    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(className = "v-catalog__title")
    private WebElement catalogueTitle;

    public CataloguePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void verifyCatalogueTitle(String title) {
        Assertions.assertTrue(catalogueTitle.getText().equals(title));
    }
}
