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
                .waitForAlert().returnError();

        Assert.assertEquals(actualError, "Warning: The E-Mail Address was not found in our records!", "Unexpected E-mail validation error");
    }

    @Test
    public void productsPageTest() {
        SoftAssertions softAssertions = new SoftAssertions();

        MainPage mainPage = new MainPage();
        ProductsPage productsPage = new ProductsPage();
        int amountOfProducts = mainPage.clickOnDesktopsLink()
                .clickOnAllDesktopsLink().amountOfProductsOnPage();

        String displayLimit = productsPage.currentValueOfDisplayLimit();
        String sortValue = productsPage.currentValueOfSort();
        String newValueOfDisplayLimit = "25";
        int changedAmountOfProducts = productsPage.setValueOfDisplayLimit(newValueOfDisplayLimit).amountOfProductsOnPage();

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
        ProductsPage productsPage = new ProductsPage();

        List<String> actualNames = mainPage.hoverOnDesktopMenu().clickOnAllDesktopsLink().setValueOfSort(SortOptions.NameASC).namesOfProducts();
        List<String> expectedNamesOrder = new ArrayList<>();
        expectedNamesOrder.addAll(actualNames);
        Collections.<String>sort(expectedNamesOrder);

        List<Double> actualPrice = productsPage.setValueOfSort(SortOptions.PriceASC).productsPraises();
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


/*    Test #5

    Go to the https://demo.opencart.com/
    Click on the Cameras
    Check that 2 cameras exist on page
    Check that Canon EOS 5D has old price 122.00
    Check that Canon EOS 5D has new price 98.00
    Check that Nikon D300 has ex.rate 80.00*/

    @Test
    public void checkCameraPrices() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage();
        ProductsPage productsPage = new ProductsPage();

        softAssertions.assertThat(mainPage.clickOnCameraslink().amountOfProductsOnPage()).as("Items on Cameras page should be 2").isEqualTo(2);
        softAssertions.assertThat(productsPage.returnOldPrice("Canon EOS 5D")).as("Canon EOS 5D has old price 122.00").isEqualTo(122.00);
        softAssertions.assertThat(productsPage.returnCurrentPrice("Canon EOS 5D")).as("Canon EOS 5D has new price 98.00").isEqualTo(98.00);
        softAssertions.assertThat(productsPage.returnTaxPrice("Nikon D300")).as("Nikon D300 has ex.rate 80.00").isEqualTo(80.00);
        softAssertions.assertAll();
    }


}
