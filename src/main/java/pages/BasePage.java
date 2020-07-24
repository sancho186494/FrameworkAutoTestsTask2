package pages;

import managers.PageManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Product;

import java.util.HashMap;
import java.util.Map;

import static managers.DriverManager.*;

public class BasePage {

    protected static String product1;
    protected static String product2;

    protected PageManager pageManager = PageManager.getPageManager();

    protected static Map<String, Product> productCart = new HashMap<>();

    public static void setProduct1(String product1) {
        BasePage.product1 = product1;
    }

    public static void setProduct2(String product2) {
        BasePage.product2 = product2;
    }


    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    static void insertValue(WebElement element, String value) {
        element.click();
        for (int j = 0; j < 5; j++) {
            element.clear();
            for (int i = 0; i < value.length(); i++) {
                element.sendKeys(String.valueOf(value.charAt(i)));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (element.getAttribute("value").equals(value))
                break;
        }
    }
    //delete space-char 99 999 => 99999
    static String makePriceNormal(String value) {
        String normal = "";
        String[] temp = value.split(" ");
        for (String s : temp) {
            normal += s;
        }
        return normal;
    }

    static String getParamXpath(String param, String tempXpath) {
        String xpath = "";
        String[] temp = tempXpath.split("\\$\\$");
        xpath = xpath + temp[0] + param + temp[1];

        return xpath;
    }

}
