package pages;

import managers.PageManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {

    @FindBy(xpath = "//input[@name='q'][@placeholder='Поиск по сайту']")
    protected WebElement searchField;

    public StartPage inputSearch(String searchValue) {
        insertValue(searchField, searchValue);
        return this;
    }

    public SearchResultPage submit() {
        searchField.submit();
        return PageManager.getPageManager().getSearchResultPage();
    }
}
