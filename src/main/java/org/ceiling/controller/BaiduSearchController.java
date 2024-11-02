package org.ceiling.controller;

import io.qameta.allure.*;
import org.ceiling.service.BaiduService;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaiduSearchController {


    private static BaiduService baiduService = new BaiduService();

    @BeforeTest
    public void setUp(){
        /*
         * 在每个测试类之前执行一次
         * */
        baiduService = new BaiduService();
    }

    @Test
    @Feature("模块名称")
    @Story("用例名称")
    @Issue("缺陷地址")
    @Description("用例描述")
    @Step("操作步骤")
    @Severity(SeverityLevel.BLOCKER) // 用例等级
    @Link("https://www.baidu.com") // 定义链接
    public static void testBaiduSearchContent(){

        Allure.step("在百度输入框中输入内容");
        baiduService.inputContent("123");
        Allure.step("点击百度搜索按钮");
        baiduService.clickSearchButton();

        try {
            Thread.sleep(2000);
            Allure.step("将百度搜索内容进行截图");
            baiduService.takeScreenshot("screenshot\\baidu", "百度搜索结果截图");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }




}
