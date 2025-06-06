package org.ceiling.controller;

import io.qameta.allure.*;
import org.ceiling.service.ChooseGoodsService;
import org.ceiling.service.SauceLoginService;
import org.ceiling.service.ShoppingCartService;
import org.testng.annotations.*;

import java.io.IOException;

public class SauceDemoController {

    private static SauceLoginService sauceLoginService;
    private static ChooseGoodsService chooseGoodsService;
    private static ShoppingCartService shoppingCartService;


    @BeforeClass
    public void setUp() {
        sauceLoginService = new SauceLoginService();
        chooseGoodsService = new ChooseGoodsService();
        shoppingCartService = new ShoppingCartService();
    }

    @Parameters({
            "productCount",
            "selectionMethod",
            "firstname",
            "lastname",
            "postcode"
    })

    @Test
    @Feature("选择商品并结账")
    @Story("选择商品并结账")
    @Issue("缺陷地址")
    @Description("选择商品并结账")
    @Step("操作步骤")
    @Severity(SeverityLevel.BLOCKER) // 用例等级
    @Link("https://www.saucedemo.com") // 定义链接
    public static void testSauceDemo(
            @Optional("3") int productCount,
            @Optional("sequential") String selectionMethod,
            @Optional("John") String firstname,
            @Optional("Doe") String lastname,
            @Optional("12345") String postcode
    ) throws IOException {

        Allure.step("<输入用户名>");
        sauceLoginService.inputUserName("standard_user");
        Allure.step("<输入密码>");
        sauceLoginService.inputPassword("secret_sauce");
        Allure.step("<点击登录>");
        sauceLoginService.clickLoginButton();

        Allure.step("<选择商品>");
        chooseGoodsService.chooseGoods(selectionMethod,productCount);
        try {
            Thread.sleep(2000);
            Allure.step("选择商品截图");
            chooseGoodsService.takeScreenshot("screenshot\\sauce", "选择商品截图");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        Allure.step("<点击购物车>");
        chooseGoodsService.clickShoppingCartButton();

        Allure.step("<点击结账按钮>");
        shoppingCartService.clickCheckout();
        Allure.step("<输入名>");
        shoppingCartService.inputFirstName(firstname);
        Allure.step("<输入姓>");
        shoppingCartService.inputLastName(lastname);
        Allure.step("<输入邮编>");
        shoppingCartService.inputPostalCode(postcode);
        Allure.step("<点击继续按钮>");
        shoppingCartService.clickContinueButton();
        Allure.step("<点击结束按钮>");
        shoppingCartService.clickFinishButton();

        Allure.step("<校验订单是否完成>");
        shoppingCartService.verifyOrderIfDone();




        try {
            Thread.sleep(2000);
            Allure.step("订单结果截图");
            shoppingCartService.takeScreenshot("screenshot\\sauce", "订单结果截图");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @AfterClass
    public void tearDown() {
        // 退出浏览器驱动
        shoppingCartService.quitDriver();
    }
}
