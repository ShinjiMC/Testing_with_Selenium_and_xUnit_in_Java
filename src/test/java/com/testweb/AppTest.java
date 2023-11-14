package com.testweb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import junit.framework.TestCase;

public class AppTest extends TestCase {
    private WebDriver driver;

    @Override
    protected void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/testweb/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void tests(){
        String[][] testData = {
            {"15.5", "7.2", "1.116"},
            {"-15.5", "7.2", "-1.116"},
            {"0", "7.2", "0"},
            {"0.01", "7.2", "0.00072"},
            {"100", "7.2", "7.2"},
        };
        for (String[] data : testData){
            String result = AppWebdriver.calculatePercentage(driver, data[0], data[1]);
            System.out.println("Para " + data[0] + " y " + data[1] + " el resultado es: " + result);
            assertEquals(data[2], result.trim());
        }
    }

    @Override
    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}