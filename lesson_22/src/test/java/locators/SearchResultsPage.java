package locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(css = ".breadcrumbs__link")
    private WebElement breadCrumbs;
    @FindBy(css = ".breadcrumbs__link > b")
    private WebElement searchQuery;
    @FindBy(className = "product-card__title")
    private List<WebElement> searchResultLinks;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

    public boolean isBreadcrumbContainValue(String value) {
        return searchQuery.getText().contains(value);
    }

    public void selectResultsItemByIndex(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultLinks));
        searchResultLinks.get(index).click();
    }
}
