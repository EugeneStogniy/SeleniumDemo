package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Forgotten Password")
    private WebElement forgottenPasswordLink;

    public ForgotPasswordPage clickForgottenPasswordLink() {
        clickOnElementJS(forgottenPasswordLink);
        return new ForgotPasswordPage();
    }


}
