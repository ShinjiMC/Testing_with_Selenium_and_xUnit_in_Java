package com.testweb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AppWebdriver {
    public static String calculatePercentage(WebDriver driver, String firstValue, String secondValue) {
        driver.get("https://www.calculator.net/percent-calculator.html");
        WebElement firstNumber = driver.findElement(By.id("cpar1"));
        firstNumber.sendKeys(firstValue);
        WebElement secondNumber = driver.findElement(By.id("cpar2"));
        secondNumber.sendKeys(secondValue);
        WebElement calculateButton = driver.findElement(By.xpath("//input[@value='Calculate']"));
        calculateButton.click();
        WebElement resultElement = driver.findElement(By.cssSelector("font[color='green']"));
        return resultElement.getText();
    }
}
