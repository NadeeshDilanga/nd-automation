package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Retry extends Base implements IRetryAnalyzer {
    private int retryCounter = 0;
    private int retryLimit;
    private List<String> retryExceptions = new ArrayList<String>();
    private Property retryDataProperty = new Property();
    public Retry() throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("Retry constructor called");
        retryDataProperty.initializeProperty(retryDataFilePath);
        retryLimit = Integer.parseInt(retryDataProperty.propertiesFile.getProperty("retryLimit"));
        retryExceptions = Arrays.asList(retryDataProperty.propertiesFile.getProperty("retryExceptions").split(","));
    }
    @Override
    public boolean retry(ITestResult result) {
        String exceptionClassName = result.getThrowable().getClass().getName();
        if (!result.isSuccess() || retryExceptions.contains(exceptionClassName)) {
            if (retryCounter < retryLimit) {
                retryCounter++;
                return true;
            }
            retryCounter = 0;
            return false;
        }
        return false;
    }
}
