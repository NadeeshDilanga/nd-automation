/*
 *  Copyright 2023 Nadeesh Dilanga.
 *  All rights reserved. Reproduction or transmission in whole or in part,
 *  in any form or by any means, electronic, mechanical or otherwise, is prohibited
 *  without the prior written consent of the copyright owner.
 *  Author: Nadeesh Dilanga
 *  Date: 12/30/2022
 */

package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Retry extends Base implements IRetryAnalyzer {
    private static int retryCounter = 0;
    private static int retryLimit;
    private static List<String> retryExceptions = new ArrayList<String>();
    private static Property retryDataProperty = new Property();
    public Retry() throws Exception {
        System.out.println("Retry constructor called");
        retryDataProperty.initializeProperty(retryDataFilePath);
        retryLimit = Integer.parseInt(retryDataProperty.propertiesFile.getProperty("retryLimit"));
        retryExceptions = Arrays.asList(retryDataProperty.propertiesFile.getProperty("retryExceptions").split(","));
    }
    @Override
    public boolean retry(ITestResult result) {
        System.out.println("retry method called");
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
