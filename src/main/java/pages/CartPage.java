package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static managers.DriverManager.*;

public class CartPage extends BasePage {

    @FindBy(xpath = "(//span[@class='price__current'])[2]")
    protected WebElement productFirstPrice;

    @FindBy(xpath = "(//span[@class='price__current'])[1]")
    protected WebElement productSecondPrice;

    @FindBy(xpath = "(//span[@class='price__current'])[3]")
    protected WebElement cartSumm;

    @FindBy(xpath = "(//span[@class='price__current'])[2]")
    protected WebElement newCartSumm;

    @FindBy(xpath = "//span[@class='restore-last-removed']")
    protected WebElement restore;

    @FindBy(xpath = "//div[@data-commerce-target='basket_additional_warranty_24']//span[@class='base-ui-radio-button__icon base-ui-radio-button__icon_checked']")
    protected WebElement selectedWarranty;

    //params xpaths
    protected String deleteProductXpath = "//a[contains(text(), '$$')]//..//..//..//button[text()='Удалить']";
    protected String deletedProductXpath = "//a[contains(text(), '$$')]";
    protected String productCountXpath = "//a[contains(text(), '$$')]//..//..//..//div[@class='cart-items__product-block-amount']//input";
    protected String plusCountButtonXpath
            = "//a[contains(text(), '$$')]//..//..//..//div[@class='cart-items__product-block-amount']" +
            "//button[@class='count-buttons__button count-buttons__button_plus']";
    protected String product1CountXpath = "//a[contains(text(), '$$')]//..//..//..//div[@class='cart-items__product-block-amount']//input";
    protected String product2CountXpath = "//a[contains(text(), '$$')]//..//..//..//div[@class='cart-items__product-block-amount']//input";


    public CartPage checkWarranty() {
        Assertions.assertDoesNotThrow(() -> selectedWarranty.click(), "Гарантия 2 года не выбрана");
        return this;
    }

    public CartPage checkPrices() {
        Assertions.assertEquals(productCart.get(product2).getPrice(), Integer.parseInt(makePriceNormal(productFirstPrice.getText())),
                "Цена Detroit не совпадает");
        Assertions.assertEquals(productCart.get(product1).getBasePrice(), Integer.parseInt(makePriceNormal(productSecondPrice.getText())),
                "Цена PS4 не совпадает");
        return this;
    }


    public CartPage deleteProductAndCheck(String product) {
        WebElement deleteProductButton = getDriver().findElement(By.xpath(getParamXpath(product, deleteProductXpath)));
        deleteProductButton.click();
        restore.isDisplayed();
        Assertions.assertThrows(NoSuchElementException.class, () -> getDriver().findElement(
                By.xpath(getParamXpath(product, deletedProductXpath))), "Продукт не удален");

        productCart.forEach((k, v) -> {
            if (!product.equals(k)) {
                Assertions.assertEquals(productCart.get(k).getPrice(),
                        Integer.parseInt(makePriceNormal(newCartSumm.getText())),
                        "Сумма корзины не совпадает после удаления товара");
            }
        });
        return this;
    }

    public CartPage plusProduct(String product) {
        WebElement productCount = getDriver().findElement(By.xpath(getParamXpath(product, productCountXpath)));
        int count = Integer.parseInt(productCount.getAttribute("value"));
        WebElement plusCountButton = getDriver().findElement(By.xpath(getParamXpath(product, plusCountButtonXpath)));
        plusCountButton.click();

        int count1 = 0;
        while (true) {
            if (productCount.getAttribute("value").equals(String.valueOf(count + 1)))
                break;
            count1++;
            if (count1 == 300)      //endless loop insurance
                break;
        }

        Assertions.assertEquals(productCart.get(product).getPrice() * (count + 1),
                Integer.parseInt(makePriceNormal(newCartSumm.getText())),
                "Сумма корзины не совпадает после добавления товара");
        return this;
    }


    public CartPage restore() {
        restore.click();
        return this;
    }

    public CartPage checkSummCartFull() {
        WebElement product1Count = getDriver().findElement(By.xpath(getParamXpath(product1, product1CountXpath)));
        int countProduct1 = Integer.parseInt(product1Count.getAttribute("value"));
        WebElement product2Count = getDriver().findElement(By.xpath(getParamXpath(product2, product2CountXpath)));
        int countProduct2 = Integer.parseInt(product2Count.getAttribute("value"));

        Assertions.assertEquals((productCart.get(product1).getPrice() * countProduct1 + productCart.get(product2).getPrice() * countProduct2),
                Integer.parseInt(makePriceNormal(cartSumm.getText())),
                "Сумма корзины не совпадает (финальная)");
        return this;
    }
}
