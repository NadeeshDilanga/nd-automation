package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.nio.file.NoSuchFileException;
import java.time.Duration;
import java.util.function.Function;

public class Waits {

    /**
     * To:Do >> Recheck
     * waits till matches with the expected condition and with a timeout
     * Ex:
     * webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/span")));
     */
    public static void explicitWait(WebDriver driver, ExpectedCondition condition, int timeoutInSeconds) throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        webDriverWait.until(condition);
    }
    /**
     * To:Do >> Recheck
     * waits only with a timeout
     * Ex:
     * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
     * driver.findElement(By.id("userId"));
     */
    public static void implicitWait(WebDriver driver, By locator, int timeoutInSeconds) throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSeconds));
        driver.findElement(locator);
    }

    /**
     * To:Do >> Recheck
     * waits with a rechecking time and with a timeout by ignoring exceptions
     * Ex: rechecks once every 10 seconds
     * Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver)
     *      .withTimeout(Duration.ofSeconds(30))
     *      .pollingEvery(Duration.ofSeconds(10))
     *      .ignoring(NoSuchElementException.class);
     *
     *  WebElement foo = wait2.until(driver -> {
     *      return driver.findElement(By.id("foo"));
     *  });
     */
    public static void fluentWait(WebDriver driver, int timeoutInSeconds, int pollingTimeInSeconds, By locator) throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(pollingTimeInSeconds))
                .ignoring(
                        NoSuchElementException.class,
                        NoSuchFileException.class
                );
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }
}
