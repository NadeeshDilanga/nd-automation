package utilities;

import io.qameta.allure.Step;
import org.testng.log4testng.Logger;

import java.util.ArrayList;

public class Log {
    public static Logger logger = Logger.getLogger(Base.class);
    public static ArrayList<String> logArrayList = new ArrayList<>();

    public static void logInfo(String message) throws Exception {
        logArrayList.add(message);
    }
    public static void printInfo() {
        for (String message: logArrayList) {
            printInfo(message);
        }
        logArrayList.clear();
    }
    @Step("{0}")
    public static void printInfo(String message) {
        logger.info(message);
    }
}
