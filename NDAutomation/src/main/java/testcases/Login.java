package testcases;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utilities.Base;
import utilities.Log;

@Epic("Epic - Log in to the system.")
@Feature("Feature - User authentication.")
@Story("Story - Log in to the system with user name and password.")
public class Login extends Base {
    LoginPage loginPage;
    public By dashboardPageTitle = By.xpath("//div[@id='app']//h6[text()='Dashboard']");

    @AfterClass
    public void exitBrowser() {
        driver.quit();
    }
    @BeforeClass
    public void initializeTestCase() {
        loginPage = new LoginPage();
    }
    @AfterMethod
    public void printLog() {
        Log.printInfo();
    }

    @Description("Verify Login Test")
    @Step("Enter user name, password and click 'Login' button.")
    @Link(name = "sample link name", url = "https://docs.qameta.io/allure/#_testng")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void loginTest() throws Exception {
        loginPage.navigateToLoginPage();
        loginPage.setUserName(testDataProperties.propertiesFile.getProperty("username"));
        loginPage.setPassword(testDataProperties.propertiesFile.getProperty("password"));
        loginPage.clickLogin();
        String expectedPageTitle = "Dashboard";
        waitUntilElementIsPresent(dashboardPageTitle);
        String actualPageTitle = driver.findElement(dashboardPageTitle).getText();
        saveScreenshot();
        Assert.assertEquals(actualPageTitle, expectedPageTitle, "Login test failed.");
    }

    @Test(priority = 2)
    public void sampleRetryTest() {
        int x = 10/0;
        Assert.assertTrue(false);
    }
}
