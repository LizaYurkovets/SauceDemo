package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class ProductTest extends BaseTest {

    @Test
    @Description("Проверка количества пунктов в меню и их названия")
    public void checkMenuItems() {
        loginPage.open(driver);
        loginPage.login("standard_user", "secret_sauce");

        assertEquals(productsPage.getTitle(),
                "Products",
                "Переход на страницу не выполнен");

        productsPage.clickMenu();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productsPage.getSizeMenu(), 4, "Неверное количество пунктов меню");
        softAssert.assertEquals(productsPage.getMenuItemName(0),"All Items", "Название не совпадает");
        softAssert.assertEquals(productsPage.getMenuItemName(1), "About", "Название не совпадает");
        softAssert.assertEquals(productsPage.getMenuItemName(2), "Logout", "Название не совпадает");
        softAssert.assertEquals(productsPage.getMenuItemName(3), "Reset App State", "Название не совпадает");
        //softAssert.assertAll();
    }
}
