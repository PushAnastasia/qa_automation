package business.steps;

import business.pages.CataloguePage;
import business.pages.HeaderMenu;
import business.pages.MainPage;
import business.pages.SearchResultsPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class OrdersSteps {
    private static String productPageTitleEnd = "⁕ ALLO.UA ⁕ ціна, відгуки";

    public static void navigateToProductViaSearch(WebDriver driver, String query, int index) {
        HeaderMenu headerMenu = new HeaderMenu(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        headerMenu.submitSearchQuery(query);
        searchResultsPage.selectResultsItemByIndex(index);
        Assertions.assertTrue(driver.getTitle().endsWith(productPageTitleEnd));
    }

    public static void navigateToProductViaCategoryMenu(WebDriver driver, String category, String subCategory, int index) {
        MainPage mainPage = new MainPage(driver);
        CataloguePage cataloguePage = new CataloguePage(driver);
        mainPage.selectSideBarMenuCategory(category);
        mainPage.selectSubCategory(subCategory);
        cataloguePage.selectProductByIndex(index);
        Assertions.assertTrue(driver.getTitle().endsWith(productPageTitleEnd));
    }
}
