package org.ceiling.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static final Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load the configuration file!");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // 获取指定 key 的值，支持默认值
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}

