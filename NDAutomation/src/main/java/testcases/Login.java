package testcases;

import io.qameta.allure.*;
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

    @Description("Verify Invalid Login Test")
    @Step("Enter invalid user name, password and click 'Login' button.")
    @Link(name = "sample link name", url = "https://docs.qameta.io/allure/#_testng")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 1)
    public void invalidLoginTest() {
        try {
            driver.navigate().refresh();
            loginPage.navigateToLoginPage();
            loginPage.setUserName("asdasd");
            loginPage.setPassword("123123");
            loginPage.clickLogin();
            String expectedErrorText = "Invalid credentials";
            String actualErrorText = getElementText(loginPage.loginErrorLabel);
            saveScreenshot();
            Assert.assertEquals(actualErrorText, expectedErrorText, "Invalid login test is failed.");
        } catch(Exception ex) {
            saveScreenshot();
            ex.printStackTrace();
            Assert.fail("Invalid login test is failed.\n\n"+ex.getMessage());
        }
    }

    @Description("Verify Login Test")
    @Step("Enter user name, password and click 'Login' button.")
    @Link(name = "sample link name", url = "https://docs.qameta.io/allure/#_testng")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2)
    public void loginTest() {
        try {
            driver.navigate().refresh();
            loginPage.navigateToLoginPage();
            loginPage.setUserName(username);
            loginPage.setPassword(password);
            loginPage.clickLogin();
            String expectedPageTitle = "Dashboard";
            String actualPageTitle = getElementText(loginPage.dashboardPageTitle);
            saveScreenshot();
            Assert.assertEquals(actualPageTitle, expectedPageTitle, "Login test is failed.");
        } catch(Exception ex) {
            saveScreenshot();
            ex.printStackTrace();
            Assert.fail("Login test is failed.\n\n"+ex.getMessage());
        }
    }

    @Test(priority = 3)
    public void sampleRetryTest() {
        int x = 10/0;
        Assert.assertTrue(false);
    }

    @Description("Verify Login Test")
    @Step("Enter user name, password and click 'Login' button.")
    @Link(name = "sample link name", url = "https://docs.qameta.io/allure/#_testng")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 4)
    public void sampleFailTest() {
        try {
            driver.navigate().refresh();
            loginPage.navigateToLoginPage();
            loginPage.setUserName(username);
            loginPage.setPassword(password);
            loginPage.clickLogin();
            String expectedPageTitle = "Dashboard dummy text";
            String actualPageTitle = getElementText(loginPage.dashboardPageTitle);
            saveScreenshot();
            Assert.assertEquals(actualPageTitle, expectedPageTitle, "sampleFailTest is failed.");
        } catch(Exception ex) {
            saveScreenshot();
            ex.printStackTrace();
            Assert.fail("sampleFailTest is failed.\n\n"+ex.getMessage());
        }
    }
}
