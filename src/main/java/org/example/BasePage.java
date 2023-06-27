package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


public class BasePage {
    protected static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }


    public static WebElement find(By locator) {

        return getDriver().findElement(locator);
    }


    public static void clickOnElementJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        executor.executeScript("arguments[0].click()", element);
    }

    protected WebElement waitUntilClickable(By locator, int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds)).until(
                ExpectedConditions.elementToBeClickable(locator));

    }

    protected WebElement waitUntilVisible(By locator, int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds)).until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitUntilEnabled(By locator, By childLocator, int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(
                ExpectedConditions.presenceOfNestedElementLocatedBy(locator, childLocator));
    }

    protected WebElement waitUntilPresent(By locator, int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds)).until(
                ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitUntilPageIsLoaded() {
        ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
                .equals("complete");
    }

}
