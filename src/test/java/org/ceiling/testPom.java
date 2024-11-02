package org.ceiling;

import org.ceiling.utils.WebDriverUtil;
import org.testng.annotations.Test;

import java.io.IOException;

public class testPom {


    private static final eleHandler eleHandler;

    static {

        eleHandler = new eleHandler();
    }

    @Test
    public void testPom(){

        eleHandler.input_content("123");
        eleHandler.input_content("test");
        eleHandler.button_click();
        try {
            Thread.sleep(3000);
            eleHandler.takeScreenshot("screenshot/baidu", "search"); // .png 格式
            Thread.sleep(3000);
            WebDriverUtil.quitDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
