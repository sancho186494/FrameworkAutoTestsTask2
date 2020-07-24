package pages;

import managers.DriverManager;
import org.openqa.selenium.By;

public class SearchResultPage extends BasePage{

    protected String productXpath;

    public ProductPage selectProductInSearchResult(String name) {
        productXpath = "(//a[contains(text(), '" + name + "')])[1]";
        DriverManager.getDriver().findElement(By.xpath(productXpath)).click();
        return pageManager.getProductPage();
    }
}
