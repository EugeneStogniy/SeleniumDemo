package org.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DesktopsPage extends BasePage {

    @FindBy(xpath = "//select[@id='input-limit']")
    private WebElement inputLimit;
    @FindBy(xpath = "//*[@id='input-limit']/option[@selected]")
    private WebElement inputLimitSelectedOptions;

    @FindBy(xpath = "//select[@id='input-sort']")
    private WebElement inputSort;

    @FindBy(xpath = "//select[@id='input-sort']/option[@selected]")
    private WebElement inputSortSelectedOption;

    @FindBy(xpath = "//*[@id='product-list']/div")
    private List<WebElement> productsDivs;


    public DesktopsPage() {
        PageFactory.initElements(driver, this);
    }

    public String currentValueOfDisplayLimit() {
        return inputLimitSelectedOptions.getText();
    }

    public DesktopsPage setValueOfDisplayLimit(String value) {
        inputLimit.sendKeys(value);
        return this;
    }

    public int amountOfProductsOnPage() {
        return productsDivs.size();
    }

    public String currentValueOfSort() {
        return inputSortSelectedOption.getText();
    }


}
