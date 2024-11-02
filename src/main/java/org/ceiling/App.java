package org.ceiling;

import org.apache.commons.io.FileUtils;
import org.ceiling.enums.BrowserType;
import org.ceiling.utils.WebDriverUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WebDriver driver = WebDriverUtil.getDriver(BrowserType.CHROME);
//        driver.get("https://www.baidu.com");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // selenium 自带的截图工具

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        String time = format.format(Calendar.getInstance().getTime());
        // 保存截图文件到我们的项目中
        try {
            // 使用 commons-io 封装好的工具类 parent 文件夹名称 child 截图文件名称
            FileUtils.copyFile(screenshot, new File("screenshot", time + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        WebDriverUtil.quitDriver();



    }
}
