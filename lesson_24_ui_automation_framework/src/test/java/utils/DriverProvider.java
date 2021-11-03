package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;

public class DriverProvider {

    private WebDriver driver;
    private String browser;

    public WebDriver getDriver() {

        Map<String, String> map = PropertyResolver.getResource("browsers.properties");
        browser = map.get("browser");

        switch (browser) {
            case "chrome": {
                if (driver == null) {
                    System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
                    driver = new ChromeDriver();
                }
            }
            break;
            case "firefox": {
                if (driver == null) {
                    System.setProperty("webdriver.gecko.driver", "..\\geckodriver.exe");
                    driver = new FirefoxDriver();
                }
            }
            break;
            case "edge": {
                if (driver == null) {
                    System.setProperty("webdriver.edge.driver", "..\\msedgedriver.exe");
                    driver = new EdgeDriver();
                }
            }
        }
        return driver;
    }
}
