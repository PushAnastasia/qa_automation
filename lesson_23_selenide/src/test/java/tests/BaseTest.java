package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;
import static pageobject_model.HeaderMenu.logIn;
import static pageobject_model.HeaderMenu.logOut;

public class BaseTest {

    @BeforeSuite
    public void suiteSetUp() {
        System.out.println("Before Suite setUp runs");
        Configuration.startMaximized = true;
    }

    @BeforeTest
    public void testSetUp() {
        System.out.println("Before Test setUp runs");
        Configuration.timeout = 15000;
    }

    @BeforeClass
    public void classSetUp() {
        System.out.println("Before Class setUp runs");
        Configuration.browser = "chrome";
    }

    @BeforeGroups(groups = "signedIn")
    public void groupSetUp() {
        System.out.println("Before Group SetUp runs");
        logIn("depayev278@forfity.com", "plokijuh1");
    }

    @BeforeMethod
    public void setUp() {
        open("https://baellerry.ua/");
    }

    @AfterMethod
    public void cleanUp() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @AfterGroups(groups = "signedIn")
    public void groupCleanUp() {
        System.out.println("After Groups cleanUp runs");
        logOut();
    }

    @AfterClass
    public void classCleanUp() {
        System.out.println("After Class cleanUp runs");
    }

    @AfterTest
    public void testCleanUp() {
        System.out.println("After Test CleanUp runs");
    }

    @AfterSuite
    public void suiteCleanUp() {
        System.out.println("After Suite Clean Up runs");
    }

    @DataProvider(name = "setInvalidLoginPassword")
    public Object[][] setInvalidLoginPassword() {
        return new Object[][]{
                {"test", "test"},
                {"test", ""},
                {"", "test"},
                {"", ""}
        };
    }

    @DataProvider(name = "setCategoryAndProduct")
    public Object[][] setCategoryAndProduct() {
        return new Object[][]{
                {"Кошельки и портмоне женские", 2},
                {"Кошельки и портмоне мужские", 0},
        };
    }
}
