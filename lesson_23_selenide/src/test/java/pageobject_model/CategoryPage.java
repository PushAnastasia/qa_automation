package pageobject_model;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CategoryPage {
    static By productItems = By.className("fm-module-item");

    public static void selectProductByIndex(int index) {
        $$(productItems).get(index).click();
        $(By.cssSelector(".fm-product-slide-box")).should(Condition.visible);
    }
}
