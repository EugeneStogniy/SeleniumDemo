
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.example.DesktopsPage;
import org.example.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class Test1 extends BaseTest{

    @Test
    public void brandNameVerification(){
        MainPage mainPage = new MainPage();
        List<String> brandsNames ;
        brandsNames = mainPage.clickBrandsLink().brandsNames();
        List<String> expectedBrands =  Arrays.asList("Apple","Canon","Hewlett-Packard","HTC","Palm","Sony");

        Assertions.assertThat(brandsNames).as("wrong name").containsExactlyElementsOf(expectedBrands);
    }

    @Test
    public void forgotPasswordEmailValidation() {
        String actualError;
        MainPage mainPage = new MainPage();
        mainPage.clickAccountMenuitem();
        actualError = mainPage.clickLoginSubMenuitem()
                .clickForgottenPasswordLink()
                .fillEMail("testwqeqdsadsdsa@test.com")
                .submitForm()
                .waitForAllert().returnError();

        Assert.assertEquals(actualError, "Warning: The E-Mail Address was not found in our records!", "Unexpected error");
    }


    /*        Go to the https://demo.opencart.com/
        Hower over Desktops from top menu
        Click on Show All Desktops
        Check that value in Show dropdown is 10
        Check that value in Sort By is Default
        Check that 10 products display on page
        Select 25 from Show dropdown
        Check that 12 products now displayed
        Check that text 'Showing 1 to 12 of 12 (1 Pages)' displays on the bottom of the page*/
    @Test
    public void productsPageTest(){
        SoftAssertions softAssertions = new SoftAssertions();

        MainPage mainPage = new MainPage();
        DesktopsPage desktopsPage = new DesktopsPage();
        int amountOfProducts = mainPage.clickOnDesktopsLink()
                .clickOnAllDesktopsLink().amountOfProductsOnPage();

        String displayLimit = desktopsPage.currentValueOfDisplayLimit();
        String sortValue = desktopsPage.currentValueOfSort();
        String newValueOfDisplayLimit = "25";
        int changedAmountOfProducts = desktopsPage.setValueOfDisplayLimit(newValueOfDisplayLimit).amountOfProductsOnPage();

        Assertions.assertThat(amountOfProducts).as("default Products of the page is equal to 10").isEqualTo(10);
        Assertions.assertThat(displayLimit).as("default Display limit is equal to 10").isEqualTo("10");
        Assertions.assertThat(changedAmountOfProducts).as(" Products of the page changed  to 12").isEqualTo(12);
        Assertions.assertThat(sortValue).as("default Sort is equal to Default").isEqualTo("Default");

    }

}
