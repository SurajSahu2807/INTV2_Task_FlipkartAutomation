package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public void Wrapper_sendkeys(WebElement e, String ss){
        e.click();
        e.clear();
        e.sendKeys(ss);
    }

    public void Wrapper_GoToUrl(WebDriver driver, String url){
        driver.get(url);
    }

    public WebElement waitForElement(WebDriver driver, By locator , int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
            return  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch(TimeoutException e){
            System.out.println("Element with locator " + locator.toString() + " not found within " + timeout + " seconds");
            return null;
        }
    }

    public void waitForUrl(WebDriver driver,String ss,int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.urlContains(ss));
        }catch(org.openqa.selenium.NoSuchElementException e){
            System.out.println("Url doesn't contains :"  + ss);
        }
    }

    public WebElement waitForElementToBeClickable(WebDriver driver, By locator , int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            System.out.println("Element with locator " + locator.toString() + " not clickable within " + timeout + " seconds");
            return null;
        }
    }

    public void WaitUrlMatchesToUrl(WebDriver driver,String s, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.urlMatches(s));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public WebElement waitForElementToBeVisible(WebDriver driver, By locator , int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.out.println("Element with locator " + locator.toString() + " not visible within " + timeout + " seconds");
            return null;
        }
    }

    public List<WebElement> VisiBlityOfAllElements(WebDriver driver, List<WebElement> n , int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.visibilityOfAllElements(n));
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }
}
