package utilities;

import java.io.FileReader;
import java.util.Properties;

public class Property {
    private FileReader propertiesFileReader;
    public static Properties propertiesFile;

    public void initializeProperty(String propertyFilePath) throws Exception {
        Log.logInfo(Thread.currentThread().getStackTrace()[1].getClassName()+" > "+Thread.currentThread().getStackTrace()[1].getMethodName());
        propertiesFileReader = new FileReader(propertyFilePath);
        propertiesFile = new Properties();
        propertiesFile.load(propertiesFileReader);
    }
}
