package org.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {
    @FindBy(xpath = "//div[@class ='col-sm-3'][3]/ul/li[1]/a")
    private WebElement brandsLinkLocator;

    @FindBy(linkText = "Desktops")
    private WebElement desktopsLink;
    @FindBy(linkText = "Show All Desktops")
    private WebElement allDesktopsLink;

    @FindBy(xpath = "//*[@id='content']//a[contains(@href,'product_id=40') and contains(text(),'iPhone')]")
    private WebElement productIphoneLink;

    @FindBy(xpath = "//a[contains(@href,'path=33') and contains(text(),'Cameras')]")
    private WebElement cameraslink;

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    public BrandPage clickBrandsLink() {
        clickOnElementJS(brandsLinkLocator);
        return new BrandPage();
    }

    public MainPage clickOnDesktopsLink() {
        desktopsLink.click();
        return this;
    }

    public ProductsPage clickOnAllDesktopsLink() {
        allDesktopsLink.click();
        return new ProductsPage();
    }

    public MainPage hoverOnDesktopMenu() {
        this.hoverOverElement(desktopsLink);
        return this;
    }


    public ProductDetailsPage clickOnIphone() {

        productIphoneLink.click();
        return new ProductDetailsPage();
    }

    public ProductsPage clickOnCameraslink() {
        cameraslink.click();
        return new ProductsPage();
    }


}
