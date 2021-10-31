package pageobject_model;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    static By categoryItems = By.className(".fm-category-wall-item");

    public static void selectCategoryByTitle(String title) {
        SelenideElement selectedCategoryLink = $$(By.cssSelector(".fm-category-wall-item :last-child")).stream()
                .filter(s -> s.getText().contains(title)).findFirst().orElseThrow(RuntimeException::new);
        selectedCategoryLink.click();
    }
}
