package org.ceiling.service;

import org.ceiling.base.BaseService;
import org.ceiling.pages.ShoppingCartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ShoppingCartService extends BaseService {
    private static final ShoppingCartPage shoppingCartPage;
    private static final WebDriverWait wait;
    static {
        shoppingCartPage = new ShoppingCartPage();
        wait = new WebDriverWait(shoppingCartPage.getDriver(), Duration.ofSeconds(10));
    }
    public void clickCheckout() {
        clickElement(shoppingCartPage.findCheckoutElement());
    }
    public void inputFirstName(String firstName) {
        inputText(shoppingCartPage.findFirstNameElement(), firstName);
    }
    public void inputLastName(String lastName) {
        inputText(shoppingCartPage.findLastNameElement(), lastName);
    }
    public void inputPostalCode(String postalCode) {
        inputText(shoppingCartPage.findPostalCodeElement(), postalCode);
    }
    public void clickContinueButton() {
        clickElement(shoppingCartPage.findContinueButtonElement());
    }
    public void clickFinishButton() {
        clickElement(shoppingCartPage.findFinishButtonElement());
    }
    public void verifyOrderIfDone(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Thank you for your order!')]")
        ));
    }
}
