package tests;

import org.testng.annotations.*;
import pageobject_model.HeaderMenu;

import static com.codeborne.selenide.Selenide.*;
import static pageobject_model.HeaderMenu.*;

public class LoginSuite extends BaseTest {

    @Test
    public void logInWithValidCredentials() {
        open("https://baellerry.ua/");
        HeaderMenu page = page(HeaderMenu.class);
        logIn("depayev278@forfity.com", "plokijuh1");
        logOut();
    }

    @Test(dataProvider = "setInvalidLoginPassword")
    public void logInWithInvalidCredentials(String email, String password) {
        open("https://baellerry.ua/");
        logIn(email, password);
        validateAlertToast("Неправильно заполнены поля E-Mail и/или пароль!");
    }

    @Test
    public void loginAndValidateProfileMenu() {
        open("https://baellerry.ua/");
        logIn("depayev278@forfity.com", "plokijuh1");
        validateProfileMenu();
    }

    @Test
    public void validateLogOut() {
        open("https://baellerry.ua/");
        logIn("depayev278@forfity.com", "plokijuh1");
        logOut();
    }
}
