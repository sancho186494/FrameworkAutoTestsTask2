package Tests;

import BaseTests.BaseTest;
import org.junit.jupiter.api.*;

import static managers.PageManager.*;

public class TestCase extends BaseTest {

    public static String product1 = "PlayStation 4 Slim Black";
    public static String product2 = "Detroit";

    @Test
    void runTest() {
        getPageManager().
                getStartPage().
                inputSearch("playstation").
                submit().
                selectProductInSearchResult(product1).
                addProduct(product1).
                getBasePrice(product1).
                selectWarranty(product1, "2 года").
                getPrice(product1).
                addToCart().
                inputSearch("detroit").
                submit().
                addProduct(product2).
                getPrice(product2).
                addToCart().
                checkSum().
                goToCart().
                checkWarranty().
                checkPrices().
                checkSummCartFull().
                deleteProductAndCheck(product2).
                plusProduct(product1).
                plusProduct(product1).
                restore().
                checkSummCartFull();
    }
}
