package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest{

    @Test
    @Description("Добавление товара в корзину")
    public void checkCart() {
        loginPage.open(driver);
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        String product = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        assertEquals(product, "Sauce Labs Backpack");
    }

    @Test
    @Description("Добавление нескольких товаров в корзину, проверка их наличия в корзине, удаление товаров из корзины и проверка пуста ли корзина")
    public void checkAddAndDeleteFewItems() {
        loginPage.open(driver);
        loginPage.login("standard_user", "secret_sauce");
        //добавляем несколько товаров в корзину
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickAddButton("Sauce Labs Bolt T-Shirt");
        productsPage.clickAddButton("Sauce Labs Fleece Jacket");
        productsPage.clickShoppingCart();
        //проверяем, что это действительно те товары и проверяем по названию и по цене

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getAmountOfItems(), 3, "Количество товара неверное");
        softAssert.assertEquals(cartPage.getItemName(0), "Sauce Labs Backpack", "Товар не найден");
        softAssert.assertEquals(cartPage.getItemName(1), "Sauce Labs Bolt T-Shirt", "Товар не найден");
        softAssert.assertEquals(cartPage.getItemName(2), "Sauce Labs Fleece Jacket", "Товар не найден");
        softAssert.assertEquals(cartPage.getItemPrice(0), "$29.99", "Цена не совпадает");
        softAssert.assertEquals(cartPage.getItemPrice(1), "$15.99", "Цена не совпадает");
        softAssert.assertEquals(cartPage.getItemPrice(2), "$49.99", "Цена не совпадает");
        //удаляем товары
        cartPage.removeItem("Sauce Labs Backpack");
        cartPage.removeItem("Sauce Labs Bolt T-Shirt");
        cartPage.removeItem("Sauce Labs Fleece Jacket");
        softAssert.assertEquals(cartPage.checkIfEmpty(), true, "Корзина не пуста");
        softAssert.assertAll();
    }

    @Test
    @Description("Проверка перехода на страницу Products из корзины")
    public void checkContinueShopping() {
        loginPage.open(driver);
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickShoppingCart();
        cartPage.clickContinueShoppingButton();
        assertEquals(productsPage.getTitle(), "Products", "Страница не найдена");
    }

    @Test
    @Description("Проверка перехода в карточку товара из корзины")
    public void checkTransferToTheItemCardFromTheCart() {
        loginPage.open(driver);
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getItemName(0), "Sauce Labs Backpack", "Товар не найден");
        driver.findElement(By.id("item_4_title_link")).click();
        boolean element = driver.findElement(By.id("back-to-products")).isDisplayed();
        assertTrue(element);
        softAssert.assertAll();
    }

    @Test
    @Description("Проверка перехода на страницу Checkout из корзины")
    public void checkCheckoutFromCart() {
        loginPage.open(driver);
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickShoppingCart();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getItemName(0), "Sauce Labs Backpack", "Товар не найден");
        cartPage.clickCheckoutButton();
        boolean element = driver.findElement(By.id("continue")).isDisplayed();
        assertTrue(element);
    }
}
