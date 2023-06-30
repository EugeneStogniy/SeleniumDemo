import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.example.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test1 extends BaseTest {

    @Test
    public void brandNameVerification() {
        MainPage mainPage = new MainPage();
        List<String> brandsNames;
        brandsNames = mainPage.clickBrandsLink().brandsNames();
        List<String> expectedBrands = Arrays.asList("Apple", "Canon", "Hewlett-Packard", "HTC", "Palm", "Sony");

        Assertions.assertThat(brandsNames).as("wrong name").containsExactlyElementsOf(expectedBrands);
    }

    @Test
    public void forgotPasswordEmailValidation() {
        String actualError;
        // MainPage mainPage = new MainPage();
        TopMenuPage topMenuPage = new TopMenuPage();
        topMenuPage.clickAccountMenuitem();
        actualError = topMenuPage.clickLoginSubMenuitem()
                .clickForgottenPasswordLink()
                .fillEMail("testwqeqdsadsdsa@test.com")
                .submitForm()
                .waitForAllert().returnError();

        Assert.assertEquals(actualError, "Warning: The E-Mail Address was not found in our records!", "Unexpected E-mail validation error");
    }

    @Test
    public void productsPageTest() {
        SoftAssertions softAssertions = new SoftAssertions();

        MainPage mainPage = new MainPage();
        DesktopsPage desktopsPage = new DesktopsPage();
        int amountOfProducts = mainPage.clickOnDesktopsLink()
                .clickOnAllDesktopsLink().amountOfProductsOnPage();

        String displayLimit = desktopsPage.currentValueOfDisplayLimit();
        String sortValue = desktopsPage.currentValueOfSort();
        String newValueOfDisplayLimit = "25";
        int changedAmountOfProducts = desktopsPage.setValueOfDisplayLimit(newValueOfDisplayLimit).amountOfProductsOnPage();

        softAssertions.assertThat(amountOfProducts).as("default Products of the page is equal to 10").isEqualTo(10);
        softAssertions.assertThat(changedAmountOfProducts).as(" Products of the page changed  to 12").isEqualTo(12);
        softAssertions.assertThat(displayLimit).as("default Display limit is equal to 10").isEqualTo("10");
        softAssertions.assertThat(sortValue).as("default Sort is equal to Default").isEqualTo("Default");
        softAssertions.assertAll();

    }

    @Test
    public void productsPageSortTest() {
        SoftAssertions softAssertions = new SoftAssertions();

        MainPage mainPage = new MainPage();
        DesktopsPage desktopsPage = new DesktopsPage();

        List<String> actualNames = mainPage.hoverOnDesktopMenu().clickOnAllDesktopsLink().setValueOfSort(SortOptions.NameASC).namesOfProducts();
        List<String> expectedNamesOrder = new ArrayList<>();
        expectedNamesOrder.addAll(actualNames);
        Collections.<String>sort(expectedNamesOrder);

        List<Double> actualPrice = desktopsPage.setValueOfSort(SortOptions.PriceASC).productsPraises();
        List<Double> expectedPricesOrder = new ArrayList<>();
        expectedPricesOrder.addAll(actualPrice);
        Collections.<Double>sort(expectedPricesOrder);

        softAssertions.assertThat(actualNames).as("Products Names on the Desktop page isn't sorted by ASC").isEqualTo(expectedNamesOrder);
        softAssertions.assertThat(actualPrice).as("Products Prices on the Desktop page isn't sorted by ASC").isEqualTo(expectedPricesOrder);
        softAssertions.assertAll();
    }


    @Test
    public void checkCurrencyOnIphone() {
        SoftAssertions softAssertions = new SoftAssertions();

        MainPage mainPage = new MainPage();
        TopMenuPage topMenuPage = new TopMenuPage();
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();


        if (topMenuPage.currentCurrency().contains("$") == false) topMenuPage.setCurrency(CurrencyOption.$);

        Double priceInUSD = mainPage.clickOnIphone().getCurrentPrice();

        topMenuPage.setCurrency(CurrencyOption.€);
        Double priceInEUR = productDetailsPage.getCurrentPrice();

        topMenuPage.setCurrency(CurrencyOption.£);
        Double priceInGBP = productDetailsPage.getCurrentPrice();

        softAssertions.assertThat(priceInUSD).as("Iphone price in USD expected to be 123.20").isEqualTo(123.20);
        softAssertions.assertThat(priceInEUR).as("Iphone price in USD expected to be 106.04").isEqualTo(106.04);
        softAssertions.assertThat(priceInGBP).as("Iphone price in GBP expected to be 95.32").isEqualTo(95.32);
        softAssertions.assertAll();
    }


}
