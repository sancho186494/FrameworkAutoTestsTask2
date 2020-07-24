package BaseTests;

import Tests.TestCase;
import managers.InitManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.BasePage;

public class BaseTest {
    @BeforeEach
    void testPrepare() {
        BasePage.setProduct1(TestCase.product1);
        BasePage.setProduct2(TestCase.product2);
        InitManager.initFramework();
    }

    @AfterEach
    void testClose() {
        InitManager.quit();
    }
}
