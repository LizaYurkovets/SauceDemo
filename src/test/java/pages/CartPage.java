package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Log4j2
public class CartPage extends BasePage {

    private final By CHECKOUT_ID = By.id("checkout");
    private final By CONTINUE_SHOPPING = By.id("continue-shopping");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "cart.html");
    }

    private List<WebElement> getItems() {
        return driver.findElements(By.className("cart_item"));
    }

    public void clickCheckoutButton(){
        driver.findElement(CHECKOUT_ID).click();
    }

    public void clickContinueShoppingButton() {
        driver.findElement(CONTINUE_SHOPPING).click();
    }

    @Step("Получение количества товаров в корзине")
    public int getAmountOfItems() {
        log.info("Receive amount of items in the cart");
        return getItems().size();
    }

    @Step("Получение товаров по имени")
    public String getItemName(int counter) {
        log.info("Receive item name");
        return getItems().get(counter).findElement(By.className("inventory_item_name")).getText();
    }

    @Step("Получение товаров по стоимости")
    public String getItemPrice(int counter) {
        log.info("Receive item price");
        return getItems().get(counter).findElement(By.className("inventory_item_price")).getText();
    }

    @Step("Удаление товара из корзины")
    public void removeItem(String name) {
        log.info("Delete items from the cart");
        for (WebElement item : getItems()) {
            var itemName = item.findElement(By.className("inventory_item_name")).getText();
            if (itemName.equals(name)) {
                item.findElement(By.className("cart_button")).click();
                break;
            }
        }
    }

    @Step("Проверка пустая корзина или нет")
    public boolean checkIfEmpty() {
        log.info("Check if the cart empty");
        return getAmountOfItems() == 0;
    }
}
