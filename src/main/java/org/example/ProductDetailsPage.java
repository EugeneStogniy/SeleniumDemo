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

/*    public String getCurrentPrice(){
        return priceValue.getText();
    }*/

    public Double getCurrentPrice() {
        String price;
        TopMenuPage topMenuPage = new TopMenuPage();
        topMenuPage.currentCurrency();
        price = priceValue.getText().replace(topMenuPage.currentCurrency(), "");
        return Double.valueOf(price);
    }

}
