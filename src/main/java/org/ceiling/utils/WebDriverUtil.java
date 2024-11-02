package org.ceiling.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.ceiling.api.EnvironmentType;
import org.ceiling.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WebDriverUtil {

    private static WebDriver driver;


    // 私有构造方法，防止外部实例化
    private WebDriverUtil(){}

    // 使用BrowserType枚举来创建WebDriver实例
    public static WebDriver getDriver(BrowserType browserType) {
        if (driver == null) {
            switch (browserType) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("不支持的浏览器类型: " + browserType);
            }

            // 配置通用设置，比如隐式等待时间
            // 表示隐式等待10s 以下两种方式均可
            // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // 获取环境 url 您也可以使用 application.properties 类似的配置文件形式 方式多样化 这里使用枚举类
            // 您也可以通过 EnvironmentType.TEST.getUrl() 的方式来获取其值
            driver.get(EnvironmentType.getUrlByType(EnvironmentType.TEST));
            driver.manage().window().maximize();
        }
        return driver;
    }

    // 关闭并释放资源的方法
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }




}
