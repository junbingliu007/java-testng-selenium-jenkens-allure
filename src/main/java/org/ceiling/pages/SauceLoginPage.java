package org.ceiling.pages;

import org.ceiling.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * pages 查找元素层
 */
public class SauceLoginPage extends BasePage {

    private final By inputUserName = By.id("user-name");

    private final By inputPassword = By.id("password");

    private final By buttonLogin = By.id("login-button");


    public WebElement findInputUserNameElement() {
        return getElement(inputUserName);
    }


    public WebElement findInputPasswordElement() {
        return getElement(inputPassword);
    }

    public WebElement findButtonLoginElement() {
        return getElement(buttonLogin);
    }

}
