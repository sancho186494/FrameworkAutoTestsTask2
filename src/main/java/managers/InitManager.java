package managers;

import java.util.concurrent.TimeUnit;

import static utils.PropertyConstants.*;
import static managers.DriverManager.*;

public class InitManager {
    private static PropertyManager properties;

    public static void initFramework() {
        properties = PropertyManager.initProperties();

        getDriver().manage().timeouts().pageLoadTimeout(
                Long.parseLong(properties.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(
                Long.parseLong(properties.getProperty(IMPLICITY_WAIT)), TimeUnit.SECONDS);

        getDriver().manage().window().maximize();
        getDriver().get(properties.getProperty(TARGET_URL));
    }

    public static void quit() {
        quitDriver();
    }
}
