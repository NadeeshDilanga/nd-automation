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
