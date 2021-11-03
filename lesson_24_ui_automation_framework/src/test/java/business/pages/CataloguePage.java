package business.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CataloguePage extends BasePage {

    @FindBy(className = "v-catalog__title")
    private WebElement catalogueTitle;
    @FindBy(className = "products-layout__item")
    private List<WebElement> productItems;

    public CataloguePage(WebDriver driver) {
        super(driver);
    }

    public void verifyCatalogueTitle(String title) {
        Assertions.assertTrue(catalogueTitle.getText().equals(title));
    }

    public void selectProductByIndex(int index) {
        productItems.get(index).click();
    }
}
