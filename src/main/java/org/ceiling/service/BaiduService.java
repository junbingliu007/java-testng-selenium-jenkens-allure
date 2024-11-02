package org.ceiling.service;

import org.ceiling.base.BaseHandler;
import org.ceiling.pages.BaiduElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BaiduService extends BaseHandler {

    private static final BaiduElement baiduElement;

    static {
        baiduElement = new BaiduElement();
    }


    public void inputContent(String content){

        inputText(baiduElement.findInputContentElement(),content );

    }

    public void clickSearchButton() {
        clickElement(baiduElement.findSearchButtonElement());
    }
}
