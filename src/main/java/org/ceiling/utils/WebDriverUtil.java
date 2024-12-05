package org.ceiling.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.ceiling.api.EnvironmentType;
import org.ceiling.base.BasePage;
import org.ceiling.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverUtil {

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

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
                    logger.info("CHROME 浏览器启动成功，正在打开网站...");
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    logger.info("FIREFOX 浏览器启动成功，正在打开网站...");
                    break;
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    logger.info("EDGE 浏览器启动成功，正在打开网站...");
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
