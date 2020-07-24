package pages;

import managers.PageManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.Product;

public class ProductPage extends BasePage {

    protected Select select;

    @FindBy (xpath = "//span[@class='current-price-value']")
    protected WebElement productPrice;

    @FindBy(xpath = "(//select)[2]")
    protected WebElement warrantySelect;

    @FindBy(xpath = "//button[text()='Купить']")
    protected WebElement buyButton;

    @FindBy(xpath = "//span[@class='cart-link__icon']")
    protected WebElement cart;

    @FindBy(xpath = "//input[@name='q'][@placeholder='Поиск по сайту']")
    protected WebElement nextSearchField;

    @FindBy(xpath = "//span[@class='cart-link__price']")
    protected WebElement cartSum;

    @FindBy(xpath = "//button[text()='В корзине']")
    protected WebElement inCartButton;

    public ProductPage inputSearch(String searchValue) {
        insertValue(nextSearchField, searchValue);
        return this;
    }

    public ProductPage submit() {
        nextSearchField.submit();
        return PageManager.getPageManager().getProductPage();
    }

    public ProductPage addProduct(String name) {
        productCart.put(name, new Product());
        return this;
    }

    public ProductPage selectWarranty(String name, String years) {
        select = new Select(warrantySelect);
        warrantySelect.click();
        select.selectByVisibleText(years);
        productCart.get(name).setWarranty(years);
        return this;
    }

    public ProductPage getBasePrice(String name) {
        productCart.get(name).setBasePrice(Integer.parseInt(makePriceNormal(productPrice.getText())));
        return this;
    }

    public ProductPage getPrice(String name) {
        productCart.get(name).setPrice(Integer.parseInt(makePriceNormal(productPrice.getText())));
        System.out.println(name + productCart.get(name));
        return this;
    }

    public ProductPage addToCart() {
        buyButton.click();
        return this;
    }

    public ProductPage checkSum() {
        inCartButton.isDisplayed();
        Assertions.assertEquals((productCart.get(product1).getPrice() + productCart.get(product2).getPrice()),
                Integer.parseInt(makePriceNormal(cartSum.getText())),
                "Сумма разная (самый первый раз)");
        return this;
    }

    public CartPage goToCart() {
        cart.click();
        return PageManager.getPageManager().getCartPage();
    }
}
