package org.ceiling.controller;

import io.qameta.allure.*;
import org.ceiling.service.BaiduService;

import java.io.IOException;
import org.testng.annotations.Test;

/**
 * controller 元素逻辑处理层
 * example 百度搜索逻辑:
 *    1 先在搜索框中输入要搜索的内容信息
 *    2 点击搜索按钮 进行搜索
 */
public class BaiduSearchController {

    private static final BaiduService baiduService = new BaiduService();

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
            // 退出浏览器驱动
            baiduService.quitDriver();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
