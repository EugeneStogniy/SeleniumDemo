package org.example;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage extends BasePage {

    @FindBy(id = "input-email")
    private WebElement eMailInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement continueButton;


    public ForgotPasswordPage() {
        PageFactory.initElements(driver, this);
    }


    public ForgotPasswordPage fillEMail(String email) {

        eMailInput.sendKeys(email);
        return this;

    }

    public AllertWithError submitForm() {

        continueButton.click();
        return new AllertWithError();

    }


}
