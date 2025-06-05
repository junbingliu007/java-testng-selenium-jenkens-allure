package org.ceiling.base;
import org.ceiling.enums.BrowserType;
import org.ceiling.utils.PropertiesUtil;
import org.ceiling.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    private final WebDriver driver;
    private static final BrowserType browserType = BrowserType.valueOf(PropertiesUtil.getProperty("driver.browser.type", "CHROME").toUpperCase());


    // 构造函数，初始化 WebDriver
    public BasePage() {
        this.driver = WebDriverUtil.getDriver(browserType);
    }

    public WebDriver getDriver() {
        return driver;
    }

    // 显示等待获取单个元素
    public WebElement getElement(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        // ExpectedConditions.visibilityOfAllElementsLocatedBy(locator) 会内部自动调用 findElements 来实现定位和查找元素的功能。
        // ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)，这是一个条件，它会等待直到所有由 locator 指定的元素在页面上可见。
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found: " + locator);
        return element;
    }

    // 默认 10 秒超时的 getElement 方法
    public WebElement getElement(By locator) {
        logger.info("Element found: " + locator);
        return getElement(locator, 10);

    }

    // 显示等待获取多个元素
    public List<WebElement> getElements(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        logger.info("Elements found: " + locator);
        return elements;
    }

    // 默认 10 秒超时的 getElements 方法
    public List<WebElement> getElements(By locator) {
        logger.info("Elements found: " + locator);
        return getElements(locator, 10);
    }

}
