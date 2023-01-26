/*
 *  Copyright 2023 Nadeesh Dilanga.
 *  All rights reserved. Reproduction or transmission in whole or in part,
 *  in any form or by any means, electronic, mechanical or otherwise, is prohibited
 *  without the prior written consent of the copyright owner.
 *  Author: Nadeesh Dilanga
 *  Date: 12/25/2022
 */

package pages;

import org.openqa.selenium.By;
import utilities.Base;

public class LoginPage extends Base {
    private By userNameTextBox = By.xpath("//div[@class='orangehrm-login-form']//input[@name='username']");
    private By passwordTextBox = By.xpath("//div[@class='orangehrm-login-form']//input[@name='password']");
    private By loginButton = By.xpath("//div[@class='orangehrm-login-form']//button[contains(@class, 'orangehrm-login-button')]");

    public void navigateToLoginPage() throws Exception {
        driver.navigate().to(testDataProperties.propertiesFile.getProperty("url"));
    }
    public void setUserName(String username) throws Exception {
        waitUntilElementIsPresent(userNameTextBox);
        driver.findElement(userNameTextBox).sendKeys(username);
    }
    public void setPassword(String password) throws Exception {
        waitUntilElementIsPresent(passwordTextBox);
        driver.findElement(passwordTextBox).sendKeys(password);
    }
    public void clickLogin() throws Exception {
        waitUntilElementIsPresent(loginButton);
        waitUntilElementIsClickable(loginButton);
        driver.findElement(loginButton).click();
    }
}
