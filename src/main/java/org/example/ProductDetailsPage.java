package org.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends BasePage {

    @FindBy(xpath = "//*[@id='content']//span[@class='price-new']")
    private WebElement priceValue;

    public ProductDetailsPage() {
        PageFactory.initElements(driver, this);
    }


    public Double getCurrentPrice() {

        return this.getPriceFromElement(priceValue);

    }

}
