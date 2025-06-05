package org.ceiling.pages;

import org.ceiling.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChooseGoodsPage extends BasePage {

    public final By inventoryItem = By.className("inventory_item");

    public final By shoppingCartLink = By.className("shopping_cart_link");

    public WebElement findInventoryItem() {
        return getElement(inventoryItem);
    }

    public WebElement findShoppingCartLink() {
        return getElement(shoppingCartLink);
    }

}
