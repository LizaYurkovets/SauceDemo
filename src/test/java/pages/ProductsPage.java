package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Log4j2
public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector(".title");
    private final By CART_LINK = By.cssSelector(".shopping_cart_link");
    private final String ADD_TO_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";
    private final By MENU = By.id("react-burger-menu-btn");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получаем заголовок страницы")
    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара с именем {product} в корзину")
    public void clickAddButton(String product) {
        log.info("Add items to the cart");
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCart).click();
    }

    @Step("Нажимаем на кнопку корзины")
    public void clickShoppingCart() {
        driver.findElement(CART_LINK).click();
    }

    @Step("Нажатие на кнопку Меню")
    public void clickMenu() {
        driver.findElement(MENU).click();
    }

    private List<WebElement> getMenuItems() {
        return driver.findElements(By.className("menu-item"));
    }

    @Step("Получение размера меню")
    public int getSizeMenu() {
        log.info("Receive menu size");
        return getMenuItems().size();
    }

    @Step("Получение названия элементов меню")
    public String getMenuItemName(int counter) {
        log.info("Receive menu items name");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("menu-item")));
        return getMenuItems().get(counter).getText();
    }

}
