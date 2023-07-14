package com.solvd.ebayweb.page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    private int enteredQuantity;

    @FindBy(css = "[selectboxlabel='Color']")
    WebElement colorSelectBox;

    @FindBy(css = "[selectboxlabel='Model']")
    WebElement modelSelectBox;

    @FindBy(css = "[selectboxlabel='Carrier']")
    WebElement carrierSelectBox;

    @FindBy(css = "[selectboxlabel='Cosmetic']")
    WebElement cosmeticSelectBox;

    @FindBy(css = "[selectboxlabel='Storage Capacity']")
    WebElement storageSelectBox;

    @FindBy(css = "[selectboxlabel='Network']")
    WebElement networkSelectBox;

    @FindBy(css = "#qtyTextBox")
    WebElement quantityBox;

    @FindBy(xpath = "//*[@class='ux-call-to-action__text' and contains(text(),'Add to cart')]")
    WebElement addToCartButton;

    @FindBy(xpath = "//*[@class='clzBtnSection']//button")
    WebElement addedToCartConfirmPopupCloseBtn;

    @FindBy(css = "#gh-cart-n")
    WebElement itemsInCartCount;

    @FindBy(xpath = "//span[contains(text(),'Last One')]")
    WebElement lastOneMsg;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addToCartAndCloseConfirmPopup(int quantity) {
        selectColor();
        selectStorageCapacity();
        selectModel();
        selectCarrier();
        selectCosmetic();
        selectNetwork();
        enterQuantityAndAddToCart(quantity);
        closeAddedToCartConfirmPopup();
    }

    public int getItemsInCartCount() {
        WebElement itemsInCart = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(itemsInCartCount));
        System.out.println("Items in cart : " + itemsInCart.getText());
        return Integer.parseInt(itemsInCart.getText());
    }

    public void closeAddedToCartConfirmPopup() {
        WebElement closeBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(addedToCartConfirmPopupCloseBtn));
        closeBtn.click();
    }

    public void enterQuantityAndAddToCart(int quantity) {
        if (checkIfLastOneMessageElementExist() && checkIfQuantityBoxElementExist()) {
            enteredQuantity = Integer.parseInt(quantityBox.getText());
        }
        if (!checkIfLastOneMessageElementExist() && checkIfQuantityBoxElementExist()) {
            quantityBox.clear();
            enteredQuantity = quantity;
            quantityBox.sendKeys(String.valueOf(quantity));
        }
        clickAddToCartBtn();
    }

    public boolean checkIfQuantityBoxElementExist() {
        try {
            quantityBox.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Quantity box does not exist on the page");
            return false;
        }
        return true;
    }

    public boolean checkIfLastOneMessageElementExist() {
        try {
            lastOneMsg.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Last one message does not exist on the page");
            return false;
        }
        return true;
    }

    public int getProductEnteredQuantity() {
        return this.enteredQuantity;
    }

    public void clickAddToCartBtn() {
        addToCartButton.click();
    }

    public void selectNetwork() {
        try {
            Select networkOptions = new Select(networkSelectBox);
            List<WebElement> networks = networkOptions.getOptions();
            networks = networks.stream()
                    .filter(network -> !network.getText().equals("- Select -"))
                    .collect(Collectors.toList());
            networks.get((int) (Math.random() * networks.size())).click();
        } catch (NoSuchElementException e) {
            System.out.println("There is no cosmetic select");
        }
    }

    public void selectCosmetic() {
        try {
            Select cosmeticOptions = new Select(cosmeticSelectBox);
            List<WebElement> cosmetics = cosmeticOptions.getOptions();
            cosmetics = cosmetics.stream()
                    .filter(cosmetic -> !cosmetic.getText().equals("- Select -"))
                    .collect(Collectors.toList());
            cosmetics.get((int) (Math.random() * cosmetics.size())).click();
        } catch (NoSuchElementException e) {
            System.out.println("There is no cosmetic select");
        }
    }

    public void selectCarrier() {
        try {
            Select carriersOptions = new Select(carrierSelectBox);
            List<WebElement> carriers = carriersOptions.getOptions();
            carriers = carriers.stream()
                    .filter(carrier -> !carrier.getText().equals("- Select -"))
                    .collect(Collectors.toList());
            carriers.get((int) (Math.random() * carriers.size())).click();
        } catch (NoSuchElementException e) {
            System.out.println("There is no carrier select");
        }
    }

    public void selectModel() {
        try {
            Select modelOptions = new Select(modelSelectBox);
            List<WebElement> models = modelOptions.getOptions();
            models = models.stream()
                    .filter(model -> !model.getText().equals("- Select -"))
                    .collect(Collectors.toList());
            models.get((int) (Math.random() * models.size())).click();
        } catch (NoSuchElementException e) {
            System.out.println("There is no model select");
        }
    }

    public void selectStorageCapacity() {
        try {
            Select storageOptions = new Select(storageSelectBox);
            List<WebElement> storageCapacities = storageOptions.getOptions();
            storageCapacities = storageCapacities.stream()
                    .filter(storageCapacity -> storageCapacity.getAttribute("disabled") == null && !storageCapacity.getText().equals("- Select -"))
                    .collect(Collectors.toList());
            storageCapacities.get((int) (Math.random() * storageCapacities.size())).click();
        } catch (NoSuchElementException e) {
            System.out.println("There is no storage select");
        }
    }

    public void selectColor() {
        try {
            Select colorOptions = new Select(colorSelectBox);
            List<WebElement> colorList = colorOptions.getOptions();
            colorList = colorList.stream()
                    .filter(color -> color.getAttribute("disabled") == null && !color.getText().equals("- Select -"))
                    .collect(Collectors.toList());
            colorList.get((int) (Math.random() * colorList.size())).click();
        } catch (NoSuchElementException e) {
            System.out.println("There is no color select");
        }
    }
}
