package business.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(css = ".breadcrumbs__link")
    private WebElement breadCrumbs;
    @FindBy(css = ".breadcrumbs__link > b")
    private WebElement searchQuery;
    @FindBy(className = "product-card__title")
    private List<WebElement> searchResultLinks;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBreadcrumbContainValue(String value) {
        return searchQuery.getText().contains(value);
    }

    public void selectResultsItemByIndex(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultLinks));
        searchResultLinks.get(index).click();
    }
}
