package org.ceiling.base;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.ceiling.enums.BrowserType;
import org.ceiling.utils.CrossPlatformFileUploader;
import org.ceiling.utils.PropertiesUtil;
import org.ceiling.utils.WebDriverUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class BaseService {

    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
    private final WebDriver driver;
    private static final BrowserType browserType = BrowserType.valueOf(PropertiesUtil.getProperty("driver.browser.type", "CHROME").toUpperCase());



    // Constructor to initialize WebDriver from WebDriverUtil
    public BaseService() {
        this.driver = WebDriverUtil.getDriver(browserType); // Get driver from WebDriverUtil
    }

    /**
     * click the element  点击元素
     * @param element element 对象
     */
    public void clickElement(WebElement element) {
//        WebElement element = getElement(locator); // Use getElement method for element retrieval
        element.click();
        logger.info("Clicked on the element: {}", element);
    }


    /**
     * Input text into a text field 在文本框中输入内容
     * @param element WebElement representing the text field
     * @param content The text to input
     */
    public void inputText(WebElement element, String content) {
        element.clear();
        element.sendKeys(content);
        logger.info("Input text: {} into element: {}", content, element);
    }

    /**
     * Clear the content of a text field 清除文本框内容
     * @param element WebElement representing the text field
     */
    public void clearText(WebElement element) {
        element.clear();
        logger.info("Cleared content of the element: {}", element);
    }

    /**
     * Get the text of an element 获取元素的文本内容
     *
     * @param element WebElement to retrieve text from
     * @return Text content of the element
     */
    public String getElementText(WebElement element) {
        String text = element.getText();
        logger.info("Retrieved text from element: {}. Text: {}", element, text);
        return text;
    }

    /**
     * Get the value of an attribute of an element 获取元素的属性值
     *
     * @param element WebElement to get attribute from
     * @param attribute The attribute name (e.g., "href", "value")
     * @return The attribute value
     */
    public String getElementAttribute(WebElement element, String attribute) {
        String attributeValue = element.getAttribute(attribute);
        logger.info("Retrieved attribute: {} with value: {} from element: {}", attribute, attributeValue, element);
        return attributeValue;
    }


    /**
     * Switch to a specified iframe 切换到指定的 iframe
     * @param element WebElement representing the iframe
     */
    public void switchToIframe(WebElement element) {
        driver.switchTo().frame(element);
        logger.info("Switched to iframe: {}", element);
    }

    /**
     * Switch back to the main content from an iframe 切换回默认的内容（主文档）
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        logger.info("Switched back to default content");
    }

    /**
     * Execute JavaScript command on the current page 执行 js 代码
     * @param script JavaScript code to execute
     * @param args Arguments that the JavaScript code might require
     * @return Object result of the script execution
     */
    public Object executeJavaScript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Object result = jsExecutor.executeScript(script, args);
        logger.info("Executed JavaScript: {}", script);
        return result;
    }

    /**
     * Switch to a window by its handle 切换指定窗口
     * @param handle Window handle to switch to
     */
    public void switchToWindow(String handle) {
        driver.switchTo().window(handle);
        logger.info("Switched to window with handle: {}", handle);
    }

    /**
     * Switch to the last opened window 切换最新窗口
     */
    public void switchToLastWindow() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        logger.info("Switched to the last opened window");
    }

    /**
     * Scroll to a specific element on the page  滚动页面到指定元素的位置
     * @param element WebElement to scroll to
     */
    public void scrollToElement(WebElement element) {
        // 使用 JavaScript 执行器来运行 JavaScript 代码，作用是让传入的元素滚动到可视区域。
        // arguments[0] 是指 JavaScript 中的第一个参数，这里传入的是 element
        // executeJavaScript("arguments[0].scrollIntoView(true);", element); // 只会处理垂直滚动
        // {block: 'center', inline: 'center'}  表示上下左右都可以滚动到视图可见的区域
        executeJavaScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
        logger.info("Scrolled to element: {}", element);
    }

    /**
     * Get the current window handle  获取当前窗口的句柄
     * @return String current window handle
     */
    public String getCurrentWindowHandle() {
        String handle = driver.getWindowHandle();
        logger.info("Current window handle: {}", handle);
        return handle;
    }

    /**
     * Close the current window and switch back to the main window 关闭当前窗口并切换回主窗口
     */
    public void closeCurrentWindowAndSwitchBack() {
        String mainWindow = driver.getWindowHandle();
        driver.close();
        logger.info("Closed the current window");

        // Switch back to main window
        driver.switchTo().window(mainWindow);
        logger.info("Switched back to the main window");
    }


    /**
     * Hover over a specific element 悬停在某个元素上
     * @param element WebElement to hover over
     */
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        logger.info("Hovered over element: {}", element);
    }

    /**
     * 执行拖拽操作，将元素从源位置拖到目标位置
     * Drag one element and drop it onto another element
     * @param source WebElement to drag
     * @param target WebElement where the source element will be dropped
     */
    public void dragAndDrop(WebElement source, WebElement target) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        logger.info("Dragged element: {} and dropped onto: {}", source, target);
    }


    /**
     * 通过可见文本选择下拉框中的选项
     * Select a value from a dropdown by visible text
     * @param element WebElement representing the dropdown
     * @param visibleText The visible text to select
     */
    public void selectDropdownByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
        logger.info("Selected option with text: {} from dropdown: {}", visibleText, element);
    }

    /**
     * 通过可见文本取消选择下拉框中的选项
     * Deselect a dropdown option by visible text
     *
     * @param element WebElement representing the dropdown
     * @param visibleText Visible text of the option to deselect
     */
    public void deselectDropdownByVisibleText(WebElement element, String visibleText) {
        Select dropdown = new Select(element);
        dropdown.deselectByVisibleText(visibleText);
        logger.info("Deselected option with text: {} from dropdown: {}", visibleText, element);
    }


     /**
      * 添加截图到 allure 报告上 保存到本地
     * Take a screenshot and save it to the specified path
     * @param filePath The path where the screenshot will be saved
     * @param fileName The name where the screenshot will be saved
     * @throws IOException If there's an issue with saving the screenshot
     */
    public void takeScreenshot(String filePath, String fileName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fullPath = filePath + File.separator + fileName + ".png"; // .png 格式
//        FileUtils.copyFile(screenshot, new File(filePath, fileName + ".png"));
        FileUtils.copyFile(screenshot, new File(fullPath));
        logger.info("Screenshot saved to: {}", fullPath);
        // Convert screenshot to byte array
        byte[] screenshotBytes = FileUtils.readFileToByteArray(screenshot);
        // Attach screenshot to Allure report
        Allure.addAttachment(fileName, new ByteArrayInputStream(screenshotBytes));
    }

    
    /**
     * 添加截图到 allure 报告上 不保存到本地
     * Take a screenshot and save it to the specified path
     * @param fileName The name where the screenshot will be saved
     * @throws IOException If there's an issue with saving the screenshot
     */
    public void takeScreenshotNotSaveLocal(String fileName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Convert screenshot to byte array
        byte[] screenshotBytes = FileUtils.readFileToByteArray(screenshot);
        // Attach screenshot to Allure report
        Allure.addAttachment(fileName, new ByteArrayInputStream(screenshotBytes));
    }


    /**
     * 跨平台上传本地文件 非浏览器元素
     * @param filePath upload the local file, such as win/mac/unx platform
     */
    public void CrossPlatformFileUploader(String filePath){
        try {
            CrossPlatformFileUploader.uploadFile(filePath);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前浏览器的 URL
     * Get the current URL of the browser
     *
     * @return The current URL
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.info("Current URL: {}", url);
        return url;
    }

    /**
     * 导航到指定的 URL
     * Navigate to a URL
     *
     * @param url The URL to navigate to
     */
    public void navigateToUrl(String url) {
        driver.get(url);
        logger.info("Navigated to URL: {}", url);
    }

    /**
     * 关闭浏览器驱动
     * quit the web driver
     */
    public void quitDriver(){
        WebDriverUtil.quitDriver();
    }


}
