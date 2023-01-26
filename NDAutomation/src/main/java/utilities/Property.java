/*
 *  Copyright 2023 Nadeesh Dilanga.
 *  All rights reserved. Reproduction or transmission in whole or in part,
 *  in any form or by any means, electronic, mechanical or otherwise, is prohibited
 *  without the prior written consent of the copyright owner.
 *  Author: Nadeesh Dilanga
 *  Date: 12/25/2022
 */

package utilities;

import java.io.FileReader;
import java.util.Properties;

public class Property {
    private static FileReader propertiesFileReader;
    public static Properties propertiesFile;

    public void initializeProperty(String propertyFilePath) throws Exception {
        System.out.println("initializeProperty method called");
        propertiesFileReader = new FileReader(propertyFilePath);
        propertiesFile = new Properties();
        propertiesFile.load(propertiesFileReader);
    }
}
