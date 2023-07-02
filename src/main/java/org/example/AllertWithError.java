package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllertWithError extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'alert')]")
    private WebElement alertError;

    @FindBy(id = "alert")
    private WebElement alertDiv;

    public AllertWithError() {
        PageFactory.initElements(driver, this);
    }

    public String returnError() {
        return alertError.getText();
    }

    public AllertWithError waitForAlert() {
        super.waitUntilPresent(new By.ByXPath("//div[contains(@class, 'alert')]"), 1);
        return this;
    }

}
