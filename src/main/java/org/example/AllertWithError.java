package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class AllertWithError extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'alert')]")
    private WebElement allertError;

    @FindBy(id = "alert")
    private WebElement allertDiv;

    public AllertWithError() {
        PageFactory.initElements(driver, this);
    }

    public String returnError() {
        return allertError.getText();
    }

    public AllertWithError waitForAllert() {
        super.waitUntilPresent(new By.ByXPath("//div[contains(@class, 'alert')]"), 1);
        return this;
    }

}
