package com.automation.practice.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataReader {

    private static Properties configProp;

    private static Properties getConfigPropertiesObject() {
        if (configProp == null) {
            configProp = new Properties();
            try {
                configProp.load(new FileInputStream(new File(System.getProperty("user.dir") + "/Configuration.properties")));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return configProp;
    }

    public static String getConfigData(String key) {
        return getConfigPropertiesObject().get(key).toString().trim();
    }
}
