import conditions.ElementDisplayedConditions;
import conditions.ProductCartContentDisplayedCondition;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LocatingElementsTests {

    @Test
    public void findElementByIdTest() {
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://allo.ua/");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebDriverWait waiter = new WebDriverWait(driver, 10);
        WebElement searchField = driver.findElement(By.id("search-form__input"));
        searchField.sendKeys("earphones");
        WebElement searchIcon = driver.findElement(By.className("search-form__submit-button"));
        searchIcon.click();
        WebElement breadCrumbs = waiter.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".breadcrumbs__link > b")));
        assert breadCrumbs.getText().contains("earphones");
        driver.quit();
    }

    @Test
    public void findElementByClassNameTest() {
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://allo.ua/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait waiter = new WebDriverWait(driver, 10);
        waiter.until(new ElementDisplayedConditions());
        WebElement geoLabel = driver.findElement(By.className("geolocation__city-title"));
        geoLabel.click();
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(By.className("geolocation__content")).isDisplayed();
            }
        });
        List<WebElement> cityLinks = driver.findElements(By.className("geolocation__city"));
        System.out.println("Menu contains " + cityLinks.size() + " cities.");
        assert cityLinks.size() == 6;
        driver.quit();
    }

    @Test
    public void findElementByTagTest() {
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://allo.ua/ua/help/credit/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait waiter = new WebDriverWait(driver, 5);
        List<WebElement> tables = waiter.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("table")));
        System.out.println("The page contains " + tables.size() + " table(s).");
        List<WebElement> tableCells = driver.findElements(By.tagName("td"));
        System.out.println("The page contains " + tableCells.size() + " table cells.");
        driver.quit();
    }

    @Test
    public void findElementByNameTest() {
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://allo.ua/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait waiter = new WebDriverWait(driver, 10);
        waiter.until(new ElementDisplayedConditions());
        WebElement emailField = waiter.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.click();
        emailField.sendKeys("test");
        WebElement subscribeButton = driver.findElement(By.className("social-subscription__button"));
        subscribeButton.click();
        WebElement validationMessage = driver.findElement(By.cssSelector(".validation_advice > span > span"));
        assert validationMessage.getText().equals("Будь ласка, вкажіть коректну адресу електронної пошти");
        driver.quit();
    }

    @Test
    public void findElementBytLinkTest() {
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://allo.ua/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait waiter = new WebDriverWait(driver, 4);
        WebElement laptopMenuItem = driver.findElement(By.partialLinkText("Ноутбуки, ПК"));
        laptopMenuItem.click();
        WebElement laptopCategoryLink = driver.findElement(By.linkText("Ноутбуки"));
        laptopCategoryLink.click();
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(By.className("products-layout__container")).isDisplayed();
            }
        });
        WebElement catalogTitle = driver.findElement(By.className("v-catalog__title"));
        assert catalogTitle.getText().equals("Ноутбуки");
        driver.quit();
    }

    @Test
    public void findElementByCssSelectorTest() {
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://allo.ua");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait waiter = new WebDriverWait(driver, 10);
        waiter.until(new ElementDisplayedConditions());
        WebElement geoLabel = driver.findElement(By.cssSelector("body div div div div div div div div div"));
        geoLabel.click();
        WebElement citySearchField = driver.findElement(By.cssSelector("div input"));
        citySearchField.clear();
        citySearchField.sendKeys("Лозова");
        WebElement selectItem = waiter.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v_custom_select_item")));
        selectItem.click();
        waiter.until(new ElementDisplayedConditions());
        geoLabel = driver.findElement(By.cssSelector("body div div div div div div div div div"));
        assert geoLabel.getText().equals("Лозова");
        driver.quit();
    }

    @Test
    public void findElementByCssSelectorIdTest() {
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://allo.ua");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait waiter = new WebDriverWait(driver, 10);
        WebElement loginButton = driver.findElement(By.cssSelector(".authentication__button--login"));
        loginButton.click();
        WebElement loginField = waiter.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#auth")));
        loginField.sendKeys("test");
        WebElement passwordField = driver.findElement(By.cssSelector("#v-login-password"));
        passwordField.sendKeys("test");
        WebElement submitButton = driver.findElement(By.cssSelector(".modal-submit-button"));
        submitButton.click();
        WebElement validationMessage = driver.findElement(By.cssSelector(".validation-advice"));
        validationMessage.getText().equals("Будь ласка, вкажіть коректну адресу електронної пошти");
        driver.quit();
    }

    @Test
    public void findElementByCssSelectorClassNameTest() {
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://allo.ua");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait waiter = new WebDriverWait(driver, 10);
        List<WebElement> buyButtons = driver.findElements(By.cssSelector(".buy-button"));
        buyButtons.get(0).click();
        WebElement cartModal = waiter.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".v-modal__cmp-body")));
        assert cartModal.isDisplayed();
        driver.quit();
    }

    @Test
    public void findElementByXpathTest() {
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://allo.ua");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait waiter = new WebDriverWait(driver, 15);
        WebElement searchField = driver.findElement(By.xpath("//input[@id='search-form__input']"));
        searchField.sendKeys("smart TV");
        searchField.submit();
        List<WebElement> searchResults = waiter.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'TV')]")));
        searchResults.get(0).click();
        WebElement pageHeader = driver.findElement(By.xpath("//*[@id='__layout']/div/div[2]/div[1]/div[1]/h1"));
        assert pageHeader.getText().contains("TV");
        driver.quit();
    }

    @Test
    public void findElementByXpathChildTest() {
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://allo.ua");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebDriverWait waiter = new WebDriverWait(driver, 15);
        List<WebElement> categories = driver.findElements(By.xpath("//p[contains(@class, 'item__title')]"));
        WebElement sportCategory = categories.stream().filter(s -> s.getText().contains("Спорт"))
                .findFirst().orElseThrow(RuntimeException::new);
        sportCategory.click();
        WebElement subCategory = driver.findElement(By.xpath("//img[@alt='Тренажери']"));
        subCategory.click();
        List<WebElement> buyButtons = waiter.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(@class,'buy-button')]")));
        buyButtons.get(1).click();
        waiter.until(new ProductCartContentDisplayedCondition());
        WebElement modalCloseButton = driver.findElement(By.xpath("//div[@class='v-modal__close-btn']"));
        modalCloseButton.click();
        WebElement shoppingCartCount = driver.findElement(By.xpath("//div[@class='shopping-cart__icon']//child::*[@class='shopping-cart__count']"));
        shoppingCartCount.getText().equals("1");
        driver.quit();
    }
}
