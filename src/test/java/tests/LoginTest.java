package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        loginPage.open(driver);
        loginPage.login("standard_user", "secret_sauce");

        assertEquals(productsPage.getTitle(),
                "Products",
                "Переход на страницу не выполнен");
    }

    @Test
    public void checkEmptyUsernameTest() {
        loginPage.open(driver);
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getError(), "Epic sadface: Username is required",
                "Неверный текст или ошибка не получена");
    }

    @Test
    public void checkEmptyPasswordTest() {
        loginPage.open(driver);
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getError(), "Epic sadface: Password is required",
                "Неверный текст или ошибка не получена");
    }

    @Test
    public void checkWrongLoginDataTest() {
        loginPage.open(driver);
        loginPage.login("standard_user", "test");
        driver.findElement(By.id("login-button")).click();
        assertEquals(loginPage.getError(), "Epic sadface: Username and password do not match any user in this service",
                "Неверный текст или ошибка не получена");
    }
    /*@Test
    public void copy() throws IOException, UnsupportedFlavorException {
        String copyIntoBuffer = "TeachMeSkiils";

        StringSelection stringSelection = new StringSelection(copyIntoBuffer);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        System.out.println(Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
    }*/
}
