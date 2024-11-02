package org.ceiling;

import org.ceiling.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

@Test
public class TestBaidu extends BasePage {

    private By input = By.id("kw");
    private By clickEle = By.id("su");

    public TestBaidu(){

        super();

    }

    public void testBaidu(){

        WebElement inputEle = getElement(input);
        WebElement elementClick = getElement(clickEle);



    }

}
