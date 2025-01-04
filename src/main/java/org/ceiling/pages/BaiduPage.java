package org.ceiling.pages;

import org.ceiling.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * pages 查找元素层
 */
public class BaiduPage extends BasePage {

    // 百度输入框元素
    private final By inputContent = By.id("kw");
    // 点击搜索按钮元素
    private final By searchButton = By.id("su");

    /**
     *
     * @return 返回百度输入框元素 供 service(元素操作层) 调用
     */
    public  WebElement findInputContentElement(){
        return getElement(inputContent);
    }

    /**
     *
     * @return 返回百度搜索按钮元素 供 service(元素操作层) 调用
     */
    public WebElement findSearchButtonElement(){
        return getElement(searchButton);
    }


}
