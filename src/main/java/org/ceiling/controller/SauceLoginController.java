package org.ceiling.controller;

import io.qameta.allure.*;
import org.ceiling.service.SauceLoginService;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;

public class SauceLoginController {
    private static final SauceLoginService sauceLoginService = new SauceLoginService();


    @Test
    @Feature("登录")
    @Story("用户登录")
    @Issue("缺陷地址")
    @Description("用户登录")
    @Step("操作步骤")
    @Severity(SeverityLevel.BLOCKER) // 用例等级
    @Link("https://www.saucedemo.com") // 定义链接
    public static void testSauceLogin() throws IOException {

        Allure.step("输入用户名");
        sauceLoginService.inputUserName("standard_user");
        Allure.step("输入密码");
        sauceLoginService.inputPassword("secret_sauce");
        Allure.step("点击登录");
        sauceLoginService.clickLoginButton();

        try {
            Thread.sleep(2000);
            Allure.step("登录截图");
            sauceLoginService.takeScreenshot("screenshot\\sauce", "登录结果截图");
            // 退出浏览器驱动
            sauceLoginService.quitDriver();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
