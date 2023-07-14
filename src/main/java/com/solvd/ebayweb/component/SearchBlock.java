package com.solvd.ebayweb.component;

import com.solvd.ebayweb.exception.NotClickedException;
import com.solvd.ebayweb.page.BasePage;
import com.solvd.ebayweb.page.ResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchBlock extends BasePage {
    @FindBy(xpath = "//*[contains(@class,'ui-autocomplete-input')]")
    private WebElement searchField;

    @FindBy(css = "#gh-btn")
    private WebElement searchButton;

    public SearchBlock(WebDriver driver) {
        super(driver);
    }

    public void typeSearchText(String text) {
        typeText(searchField, text, 5);
    }

    public ResultPage clickSearchButton() throws NotClickedException {
        elementClick(searchButton, 5);
        return new ResultPage(driver);
    }
}
