
import org.assertj.core.api.Assertions;
import org.example.BasePage;
import org.example.BrandPage;
import org.example.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.*;

public class Test1 extends BaseTest{

    @Test
    public void FirstTest(){
        MainPage mainPage = new MainPage();
        List<String> brandsNames ;
        brandsNames = mainPage.clickBrandsLink().brandsNames();
        List<String> expectedBrands =  Arrays.asList("Apple","Canon","Hewlett-Packard","HTC","Palm","Sony");

        Assertions.assertThat(brandsNames).as("wrong name").containsExactlyElementsOf(expectedBrands);
    }

    @Test
    public void forgotPassword() throws InterruptedException {
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

}
