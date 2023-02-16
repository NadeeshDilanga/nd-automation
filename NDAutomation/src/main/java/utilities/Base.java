package utilities;

import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class Base {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static final int MAX_WAIT = 30;// in seconds
    public static final String testDataFilePath = "src/main/resources/testData.properties";
    public static final String retryDataFilePath = "src/main/resources/retryData.properties";
    public static final Property testDataProperties = new Property();
    public static String baseUrl;
    public static String username;
    public static String password;

    @BeforeClass
    public void initializeFrameWork() throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        initializeTestData();
        initializeChromeDriver();
        initializeWait();
    }
    public void initializeTestData() throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        testDataProperties.initializeProperty(testDataFilePath);
        baseUrl = testDataProperties.propertiesFile.getProperty("url");
        username = testDataProperties.propertiesFile.getProperty("username");
        password = testDataProperties.propertiesFile.getProperty("password");
    }
    public void initializeChromeDriver() throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    public void initializeWait() throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT));
    }
    public void waitUntilElementIsPresent(By locator) throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void waitUntilElementIsClickable(By locator) throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * try catch is added to avoid any exceptions in saveScreenshot method.
     * Then the test can run without any interruption.
     */
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    public byte[] saveScreenshot() {
        try {
            Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName() + " > " + Thread.currentThread().getStackTrace()[1].getMethodName());
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public String getElementText(By locator) throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        waitUntilElementIsPresent(locator);
        return driver.findElement(locator).getText().trim();
    }
}
