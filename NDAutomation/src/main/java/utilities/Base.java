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
    public static Property testDataProperties;

    @BeforeClass
    public void initializeFrameWork() throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        initializeTestData();
        initializeChromeDriver();
        initializeWait();
    }
    public void initializeTestData() throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        testDataProperties = new Property();
        testDataProperties.initializeProperty(testDataFilePath);
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
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    public byte[] saveScreenshot() throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
