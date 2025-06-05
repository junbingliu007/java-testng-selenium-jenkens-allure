package org.ceiling.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.ceiling.api.EnvironmentType;
import org.ceiling.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WebDriverUtil {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverUtil.class);

    // 配置无头模式 浏览器大小常量，避免重复
    private static final String HEADLESS_OPTION = "--headless";
    private static final String WINDOW_SIZE_OPTION = "--window-size=1920x1080";

    // 隐式等待时间，读取配置文件
    private static final int implicitWait;
    private static final boolean headless;

    // 使用 ThreadLocal 管理 WebDriver，保证线程安全
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    static {
        int tempWait;
        try {
            tempWait = Integer.parseInt(PropertiesUtil.getProperty("driver.implicit.wait", "10"));
        } catch (NumberFormatException e) {
            tempWait = 10; // 配置值无效时，使用默认值
            logger.info("The driver implicity wait value in the configuration file is invalid. The default value is used: {}", tempWait);
        }
        implicitWait = tempWait;

        // 读取 headless 配置项  若没有该项，则默认值为 false
        String headlessProperty = PropertiesUtil.getProperty("driver.headless", "false").trim();
        // 如果配置为空或为 "false"（不区分大小写），则设置为 false；如果为 "true"，则设置为 true
        headless = headlessProperty.equalsIgnoreCase("true");
        logger.info("无头模式配置: {}", headless ? "启用" : "禁用");
    }

    // 初始化 WebDriver
    public static WebDriver getDriver(BrowserType browserType) {
        if (threadLocalDriver.get() == null) {
            WebDriver driver = createDriver(browserType);
            configureDriver(driver);
            threadLocalDriver.set(driver);
        }
        return threadLocalDriver.get();
    }

    // 关闭 WebDriver 并清理线程资源
    public static void quitDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver != null) {
            driver.quit();
            threadLocalDriver.remove();
        }
    }

    // 创建 WebDriver 实例
    private static WebDriver createDriver(BrowserType browserType) {
        WebDriver driver;
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments(HEADLESS_OPTION);  // 启用无头模式
                    chromeOptions.addArguments(WINDOW_SIZE_OPTION);  // 设置窗口大小
                }
                System.setProperty("webdriver.chrome.driver", "C:/Users/ljl/.cache/selenium/chromedriver/win64/137.0.7151.68/chromedriver.exe");
                driver = new ChromeDriver(chromeOptions);
                logger.info("CHROME browser started successfully, opening the website...");
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments(HEADLESS_OPTION);  // 启用无头模式
                    firefoxOptions.addArguments(WINDOW_SIZE_OPTION);  // 设置窗口大小
                }
                driver = new FirefoxDriver(firefoxOptions);
                logger.info("FIREFOX browser started successfully, opening the website...");
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments(HEADLESS_OPTION);  // 引用常量
                    edgeOptions.addArguments(WINDOW_SIZE_OPTION);  // 引用常量
                }
                driver = new EdgeDriver(edgeOptions);
                logger.info("EDGE browser started successfully, opening the website...");
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser types: " + browserType);
        }
        return driver;
    }

    // 配置 WebDriver 通用设置
    private static void configureDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();
        String url = EnvironmentType.getUrlByType(EnvironmentType.TEST); // 读取环境 URL
        driver.get(url);
        logger.info("Open URL: " + url);
    }
}
