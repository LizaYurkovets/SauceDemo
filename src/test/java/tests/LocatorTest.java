package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    @Description("Проверка локаторов")
    public void locatorTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name"));
        driver.findElement(By.className("login_logo"));
        driver.findElement(By.name("login-button"));
        driver.findElements(By.tagName("<div>"));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.linkText("About"));
        driver.findElement(By.partialLinkText("Reset"));
        driver.findElement(By.xpath("//button[@id = 'add-to-cart-sauce-labs-backpack']"));
        driver.findElement(By.xpath("//div[text() = 'Sauce Labs Fleece Jacket']"));
        driver.findElements(By.xpath("//div[contains(@class,'inventory-item-desc')]"));
        driver.findElements(By.xpath("//div[contains(text(), 'Get')]"));
        driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Onesie')]" +
                "//ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']"));
        driver.findElements(By.xpath(" //button[@id='id = add-to-cart-sauce-labs-bolt-t-shirt']" +
                "//descendant::div[@class='inventory_item_name']"));
        driver.findElement(By.xpath("//div[contains(text(), 'Test.allTheThings() T-Shirt (Red)')]" +
                "//following::div[@class='inventory_item_desc']"));
        driver.findElements(By.xpath("//div[contains(text(), 'Sauce Labs Backpack')]//parent::div[@class='inventory_item_price']"));
        driver.findElements(By.xpath("//div[@class='inventory_item_price']//preceding::div[@class='inventory_item_desc']"));
        driver.findElements(By.xpath("//div[contains(text(), 'Test.allTheThings()') and contains(text(), 'This classic Sauce Labs')]"));
        driver.findElements(By.className("inventory_item_name.inventory-item-name"));
        driver.findElements(By.cssSelector("button.btn_primary"));
        driver.findElement(By.cssSelector("[id = add-to-cart-sauce-labs-bolt-t-shirt]"));
        driver.findElements(By.cssSelector("[id ~= inventory_item_price]"));
        driver.findElements(By.cssSelector("[class|=inventory_item_name]"));
        driver.findElements(By.cssSelector("[class^=inventory_item_desc]"));
        driver.findElements(By.cssSelector("[id$=link]"));
        driver.findElements(By.cssSelector("[class*=inventory_item_price]"));

    }
}
