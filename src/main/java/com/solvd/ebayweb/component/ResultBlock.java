package com.solvd.ebayweb.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ResultBlock extends BaseComponent {

    public ResultBlock(WebElement root) {
        super(root);
    }

    public void clickProduct() {
        root.findElement(By.className("s-item__title")).click();
    }
}
