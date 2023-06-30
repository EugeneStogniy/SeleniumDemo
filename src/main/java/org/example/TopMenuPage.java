package org.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopMenuPage extends BasePage {

    @FindBy(id = "form-currency")
    private WebElement currencyMenu;

    @FindBy(xpath = "//form[@id='form-currency']//a[@href = 'GBP']")
    private WebElement currencyMenuItemGBP;

    @FindBy(xpath = "//form[@id='form-currency']//a[@href = 'EUR']")
    private WebElement currencyMenuItemEUR;

    @FindBy(xpath = "//form[@id='form-currency']//a[@href = 'USD']")
    private WebElement currencyMenuItemUSD;

    @FindBy(xpath = "//*[@id='form-currency']/div/a/strong")
    private WebElement currentCurrency;

    @FindBy(xpath = "//i[@class='fas fa-user']/..")
    private WebElement accountMenuItem;
    @FindBy(xpath = "//a[contains(@href, 'login')]")
    private WebElement menuLoginItem;


    public TopMenuPage() {
        PageFactory.initElements(driver, this);
    }


    public String currentCurrency() {
        return currentCurrency.getText();
    }

    public TopMenuPage setCurrency(CurrencyOption currency) {
        currencyMenu.click();
        switch (currency) {
            case $:
                currencyMenuItemUSD.click();
                break;
            case £:
                currencyMenuItemGBP.click();
                break;
            case €:
                currencyMenuItemEUR.click();
                break;
        }
        // currencyMenu.submit();
        return this;
    }

    public TopMenuPage clickAccountMenuitem() {
        clickOnElementJS(accountMenuItem);
        return this;
    }

    public LoginPage clickLoginSubMenuitem() {
        clickOnElementJS(menuLoginItem);
        return new LoginPage();
    }


}
