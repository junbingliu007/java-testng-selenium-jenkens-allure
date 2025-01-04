package org.ceiling.api;

import org.ceiling.utils.PropertiesUtil;

public enum EnvironmentType {

    TEST(PropertiesUtil.getProperty("driver.url"));

    private final String url;

    EnvironmentType(String apiBaseUrl) {
        this.url = apiBaseUrl;
    }

    public String getUrl() {
        return url;
    }

    // 静态方法，直接通过枚举名获取 URL
    public static String getUrlByType(EnvironmentType environmentType) {
        return environmentType.getUrl();
    }



}
