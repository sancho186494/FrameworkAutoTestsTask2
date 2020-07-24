package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.PropertyConstants;

public class DriverManager {
    private static WebDriver driver;

    private static PropertyManager properties;

    private DriverManager() {}

    public static WebDriver getDriver() {
        properties = PropertyManager.initProperties();
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty(PropertyConstants.PATH_CHROME_DRIVER));
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
        driver = null;
    }
}
