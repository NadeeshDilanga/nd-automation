/*
 *  Copyright 2023 Nadeesh Dilanga.
 *  All rights reserved. Reproduction or transmission in whole or in part,
 *  in any form or by any means, electronic, mechanical or otherwise, is prohibited
 *  without the prior written consent of the copyright owner.
 *  Author: Nadeesh Dilanga
 *  Date: 12/25/2022
 */

package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.Base;

public class Login extends Base {
    LoginPage loginPage;
    By dashboardPageTitleXpath = By.xpath("//div[@id='app']//h6[text()='Dashboard']");

    @AfterClass
    public void exitBrowser() {
        driver.quit();
    }
    @BeforeClass
    public void initializeTestCase() {
        loginPage = new LoginPage();
    }
    @Test(priority = 1)
    public void loginTest() throws Exception {
        loginPage.navigateToLoginPage();
        loginPage.setUserName(testDataProperties.propertiesFile.getProperty("username"));
        loginPage.setPassword(testDataProperties.propertiesFile.getProperty("password"));
        loginPage.clickLogin();
        String expectedPageTitle = "Dashboard";
        waitUntilElementIsPresent(dashboardPageTitleXpath);
        String actualPageTitle = driver.findElement(dashboardPageTitleXpath).getText();
        Assert.assertEquals(actualPageTitle, expectedPageTitle, "Login test failed.");
    }
    @Test(priority = 2)
    public void sampleRetryTest() {
        int x = 10/0;
        Assert.assertTrue(false);
    }
}
