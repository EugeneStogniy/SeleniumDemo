package org.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
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

    @FindBy(xpath = "//select[@id='input-sort']/option[text() ='Name (A - Z)']")
    private WebElement inputSortNameASCsortOption;

    @FindBy(xpath = "//select[@id='input-sort']/option[text() ='Price (Low > High)']")
    private WebElement inputSortPriceASCsortOption;

    @FindBy(xpath = "//select[@id='input-sort']/option[text() ='Price (High > Low)']")
    private WebElement inputSortPriceDESCsortOption;

    @FindBy(xpath = "//select[@id='input-sort']/option[text() ='Name (Z - A)']")
    private WebElement inputSortNameDESCsortOption;
    @FindBy(xpath = "//select[@id='input-sort']/option[text() ='Default']")
    private WebElement inputSortDefaultOption;


    @FindBy(xpath = "//*[@id='product-list'] //*[@class='description']/h4")
    private List<WebElement> productsNames;

    @FindBy(xpath = "//*[@id='product-list'] //*[@class='description']/div[@class='price']/span[@class='price-new']")
    private List<WebElement> productsPraises;

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

    public DesktopsPage setValueOfSort(SortOptions sortVal) {
        inputSort.click();
        switch (sortVal) {
            case PriceASC:
                inputSortPriceASCsortOption.click();
                break;
            case PriceDESC:
                inputSortPriceDESCsortOption.click();
                break;
            case NameASC:
                inputSortNameASCsortOption.click();
                break;
            case NameDESC:
                inputSortNameDESCsortOption.click();
                break;
            case DEFAULT:
                inputSortDefaultOption.click();
                break;
        }
        return this;
    }

    public List<String> namesOfProducts() {
        List<String> result = new ArrayList<>();
        for (WebElement productName : productsNames) {
            result.add(productName.getText());
        }
        return result;
    }

    public List<Double> productsPraises() {
        List<Double> result = new ArrayList<>();
        for (WebElement productPrice : productsPraises) {
            result.add(Double.valueOf(productPrice.getText().substring(1)));
        }
        return result;
    }


}
