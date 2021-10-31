package pageobject_model;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WishListPage {
    static By productItem = By.className("fm-acc-info-block");
    static By deleteItemButton = By.className("fm-acc-info-link-del");
    static By emptyWishListMessage = By.cssSelector("#content > div > div > p");

    public static void verifyWishListProductCount(int count) {
        $$(productItem).shouldHave(CollectionCondition.size(count));
    }

    public static void removeItemFromWishListByIndex(int index) {
        $$(deleteItemButton).get(index).click();
    }

    public static void verifyWishListIsEmpty() {
        verifyWishListProductCount(0);
        $(emptyWishListMessage).should(Condition.visible);
    }
}
