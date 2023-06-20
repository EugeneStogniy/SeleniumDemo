package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class BrandPage extends BasePage {

    @FindBy(xpath = "//div[@id='product-manufacturer']//div[@class='col-sm-3']/a")
    List<WebElement> brands;


    public BrandPage() {
        PageFactory.initElements(driver, this);
    }

    public List<String> brandsNames() {
        List<String> result = new ArrayList<>();
        for (WebElement brand : brands) {
            result.add(brand.getText());
        }
        return result;
    }


}
