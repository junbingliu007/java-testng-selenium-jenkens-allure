package org.ceiling.service;

import org.ceiling.base.BaseService;
import org.ceiling.pages.ChooseGoodsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ChooseGoodsService extends BaseService {
    private static final ChooseGoodsPage chooseGoodsPage;

    private static final WebDriverWait wait;

    static {
        chooseGoodsPage = new ChooseGoodsPage();
        wait = new WebDriverWait(chooseGoodsPage.getDriver(), Duration.ofSeconds(10));
    }

    public void chooseGoods(String selectionMethod,int productCount) {
        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(chooseGoodsPage.inventoryItem)
        );

        // 根据参数选择商品
        if ("random".equalsIgnoreCase(selectionMethod)) {
            selectRandomProducts(products, productCount);
        } else {
            selectSequentialProducts(products, productCount);
        }
    }

    public void clickShoppingCartButton() {
        clickElement(chooseGoodsPage.findShoppingCartLink());
    }

    private void selectRandomProducts(List<WebElement> products, int count) {
        Random rand = new Random();
        for (int i = 0; i < Math.min(count, products.size()); i++) {
            int randomIndex = rand.nextInt(products.size());
            products.get(randomIndex).findElement(By.tagName("button")).click();
            products.remove(randomIndex); // 避免重复选择
        }
    }

    private void selectSequentialProducts(List<WebElement> products, int count) {
        for (int i = 0; i < Math.min(count, products.size()); i++) {
            products.get(i).findElement(By.tagName("button")).click();
        }
    }
}
