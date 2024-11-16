package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By CHECKOUT_ID = By.id("checkout");
    private final By CONTINUE_SHOPPING = By.id("continue-shopping");


    public CartPage(WebDriver driver) {
        super(driver);
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
    //получаем количество товаров в корзине
    public int getAmountOfItems() {
        return getItems().size();
    }
    //нам нужно получить товары по именам, у каждого товара свой номер
    public String getItemName(int counter) {
        return getItems().get(counter).findElement(By.className("inventory_item_name")).getText();
    }
    //по стоимости находим
    public String getItemPrice(int counter) {
        return getItems().get(counter).findElement(By.className("inventory_item_price")).getText();
    }
    //удаление товара из корзины
    public void removeItem(String name) {
        for (WebElement item : getItems()) {
            var itemName = item.findElement(By.className("inventory_item_name")).getText();
            if (itemName.equals(name)) {
                item.findElement(By.className("cart_button")).click();
                break;
            }
        }
    }
    //проверка пустая корзина или нет
    public boolean checkIfEmpty() {
        return getAmountOfItems() == 0;
    }
}
