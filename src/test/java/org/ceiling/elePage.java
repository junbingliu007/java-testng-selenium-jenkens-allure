package org.ceiling;

import org.ceiling.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class elePage extends BasePage {

    private final By input = By.id("kw");
    private final By clickEle = By.id("su");


    public WebElement input_content(){

        return getElement(input);

    }


    public WebElement clickEle(){

        return getElement(clickEle);
    }




}
