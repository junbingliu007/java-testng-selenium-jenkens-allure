package org.ceiling.pages;

import org.ceiling.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BaiduElement extends BasePage {

    private final By inputContent = By.id("kw");

    private final By searchButton = By.id("su");



    public  WebElement findInputContentElement(){

        return getElement(inputContent);

    }

    public WebElement findSearchButtonElement(){
        return getElement(searchButton);
    }


}
