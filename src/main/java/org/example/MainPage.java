package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {


    @FindBy(xpath = "//div[@class ='col-sm-3'][3]/ul/li[1]/a")
    private WebElement brandsLinkLocator;

    @FindBy(xpath = "//i[@class='fas fa-user']/..")
    private WebElement accountMenuItem;
    @FindBy(xpath = "//a[contains(@href, 'login')]")
    private WebElement menuLoginitem;

    public MainPage() {
        PageFactory.initElements(driver, this);
    }


    public BrandPage clickBrandsLink() {
        clickOnElementJS(brandsLinkLocator);
        return new BrandPage();
    }

    public MainPage clickAccountMenuitem() {
        clickOnElementJS(accountMenuItem);
        return this;
    }

    public LoginPage clickLoginSubMenuitem() {
        clickOnElementJS(menuLoginitem);
        return new LoginPage();
    }

}