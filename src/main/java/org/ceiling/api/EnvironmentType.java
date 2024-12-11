package org.ceiling.api;

import org.ceiling.utils.PropertiesUtil;

public enum EnvironmentType {

    PROD(PropertiesUtil.getProperty("PROD_URL")),
    TEST(PropertiesUtil.getProperty("TEST_URL")),
    DEV(PropertiesUtil.getProperty("DEV_URL"));

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
