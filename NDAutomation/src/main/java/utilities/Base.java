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
        System.out.println("initializeFrameWork method called");
        initializeTestData();
        initializeChromeDriver();
        initializeWait();
    }
    public void initializeTestData() throws Exception {
        System.out.println("initializeTestData method called");
        testDataProperties = new Property();
        testDataProperties.initializeProperty(testDataFilePath);
    }
    public void initializeChromeDriver() throws Exception {
        System.out.println("initializeChromeDriver method called");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    public void initializeWait() throws Exception {
        System.out.println("initializeWait method called");
        wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT));
    }
    public void waitUntilElementIsPresent(By locator) throws Exception {
        System.out.println("waitUntilElementIsPresent method called");
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void waitUntilElementIsClickable(By locator) throws Exception {
        System.out.println("waitUntilElementIsClickable method called");
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    public byte[] saveScreenshot() throws Exception {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
