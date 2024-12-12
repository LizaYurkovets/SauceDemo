package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test/* (dataProvider = "loginData")*/
    @Epic("Модуль логина интернет-магазина")
    @Description("Проверка входа пользователя в систему с использованием корректных данных для входа")
    public void loginTest() {
        loginPage.open(driver);
        loginPage.login(user, password);

        assertEquals(productsPage.getTitle(),
                "Products",
                "Переход на страницу не выполнен");
    }

/*    @DataProvider()
    public Object[][] loginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce", "Products"}
        };
    }*/

/*    @DataProvider()
    public Object[][] WrongLoginData() {
        return new Object[][] {
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }*/

    @Test /*(dataProvider = "WrongLoginData")*/
    @Description("Проверка входа пользователя в систему используя неверные креды")
    public void checkWrongLoginData() {
        loginPage.open(driver);
        loginPage.login(wrongUser, wrongPassword);
        driver.findElement(By.id("login-button")).click();
        assertEquals(loginPage.getError(), "Epic sadface: Username and password do not match any user in this service",
                "Неверный текст или ошибка не получена");
    }
}



    /*@Test
    public void copy() throws IOException, UnsupportedFlavorException {
        String copyIntoBuffer = "TeachMeSkiils";

        StringSelection stringSelection = new StringSelection(copyIntoBuffer);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        System.out.println(Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
    }*/

