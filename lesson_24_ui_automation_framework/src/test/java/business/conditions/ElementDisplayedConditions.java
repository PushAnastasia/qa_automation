package business.conditions;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ElementDisplayedConditions implements ExpectedCondition<Boolean> {
    @NullableDecl
    @Override
    public Boolean apply(@NullableDecl WebDriver driver) {
        return driver.findElement(By.className("contact-us-button-wrapper")).isDisplayed();
    }
}
