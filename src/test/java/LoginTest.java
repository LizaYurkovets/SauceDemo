import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title,
                "Products",
                "Переход на страницу не выполнен");
    }

    @Test
    public void checkEmptyUsernameTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.xpath("//h3[@data-test = 'error']")).getText();
        assertEquals(error, "Epic sadface: Username is required",
                "Неверный текст или ошибка не получена");
    }

    @Test
    public void checkEmptyPasswordTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.xpath("//h3[@data-test = 'error']")).getText();
        assertEquals(error, "Epic sadface: Password is required",
                "Неверный текст или ошибка не получена");
    }

    @Test
    public void checkWrongLoginDataTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("test");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.xpath("//h3[@data-test = 'error']")).getText();
        assertEquals(error, "Epic sadface: Username and password do not match any user in this service",
                "Неверный текст или ошибка не получена");
    }
}
