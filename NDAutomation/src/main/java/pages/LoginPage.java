package pages;

import org.openqa.selenium.By;
import utilities.Base;
import utilities.Log;

public class LoginPage extends Base {
    private By userNameTextBox = By.xpath("//div[@class='orangehrm-login-form']//input[@name='username']");
    private By passwordTextBox = By.xpath("//div[@class='orangehrm-login-form']//input[@name='password']");
    private By loginButton = By.xpath("//div[@class='orangehrm-login-form']//button[contains(@class, 'orangehrm-login-button')]");

    public void navigateToLoginPage() throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        driver.navigate().to(testDataProperties.propertiesFile.getProperty("url"));
    }
    public void setUserName(String username) throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        waitUntilElementIsPresent(userNameTextBox);
        driver.findElement(userNameTextBox).sendKeys(username);
    }
    public void setPassword(String password) throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        waitUntilElementIsPresent(passwordTextBox);
        driver.findElement(passwordTextBox).sendKeys(password);
    }
    public void clickLogin() throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        waitUntilElementIsPresent(loginButton);
        waitUntilElementIsClickable(loginButton);
        driver.findElement(loginButton).click();
    }
}
