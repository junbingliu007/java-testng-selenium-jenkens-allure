package org.ceiling.pages;

import org.ceiling.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage extends BasePage {


    private final By checkoutButton = By.id("checkout");

    private final By firstName = By.id("first-name");

    private final By lastName = By.id("last-name");

    private final By postalCode = By.id("postal-code");

    private final By continueButton = By.id("continue");

    private final By finishButton = By.id("finish");

    public WebElement findCheckoutElement() {
        return getElement(checkoutButton);
    }

    public WebElement findFirstNameElement() {
        return getElement(firstName);
    }
    public WebElement findLastNameElement() {
        return getElement(lastName);
    }
    public WebElement findPostalCodeElement() {
        return getElement(postalCode);
    }
    public WebElement findContinueButtonElement() {
        return getElement(continueButton);
    }

    public WebElement findFinishButtonElement() {
        return getElement(finishButton);
    }
}
