package org.ceiling.api;

public enum EnvironmentType {

    PROD("https://www.baidu.com"),
    TEST("https://www.baidu.com"),
    DEV("https://www.baidu.com");

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
