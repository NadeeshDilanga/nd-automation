/*
 *  Copyright 2023 Nadeesh Dilanga.
 *  All rights reserved. Reproduction or transmission in whole or in part,
 *  in any form or by any means, electronic, mechanical or otherwise, is prohibited
 *  without the prior written consent of the copyright owner.
 *  Author: Nadeesh Dilanga
 *  Date: 12/25/2022
 */

package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class Base {
    public static WebDriver driver;
    public static WebDriverWait wait;
    private static final int MAX_WAIT = 30;// in seconds
    public static final String testDataFilePath = "src/main/resources/testData.properties";
    public static final String retryDataFilePath = "src/main/resources/retryData.properties";
    public static Property testDataProperties;

    @BeforeClass
    public void initializeFrameWork() throws Exception {
        System.out.println("initializeFrameWork called");
        initializeTestData();
        initializeChromeDriver();
        initializeWait();
    }
    public void initializeTestData() throws Exception {
        System.out.println("initializeTestData called");
        testDataProperties = new Property();
        testDataProperties.initializeProperty(testDataFilePath);
    }
    public void initializeChromeDriver() throws Exception {
        System.out.println("initializeChromeDriver called");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    public void initializeWait() throws Exception {
        System.out.println("initializeWait called");
        wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT));
    }
    public static void waitUntilElementIsPresent(By locator) throws Exception {
        System.out.println("waitUntilElementIsPresent called");
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public static void waitUntilElementIsClickable(By locator) throws Exception {
        System.out.println("waitUntilElementIsClickable called");
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}