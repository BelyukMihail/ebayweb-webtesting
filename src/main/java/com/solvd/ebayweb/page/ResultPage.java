package com.solvd.ebayweb.page;

import com.solvd.ebayweb.component.ResultBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultPage extends BasePage {

    @FindBy(xpath = "//*[ contains(@class,'srp-format-tabs-h2') and text()='Buy It Now']")
    WebElement buyItNowFilter;

    @FindBy(xpath = "//*[@id='srp-river-results']//*[contains(@class,'s-item__info')]")
    private List<WebElement> resultBlocks;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage chooseProduct() {
        ResultBlock resultBlock = getResultBlocks().get(1);
        resultBlock.clickProduct();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new ProductPage(driver);

    }

    public List<ResultBlock> getResultBlocks() {
        return resultBlocks.stream().map(ResultBlock::new).collect(Collectors.toList());
    }

    public void setBuyItNowFilter() {
        buyItNowFilter.click();
    }
}
