package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector(".title");
    private final By CART_LINK = By.cssSelector(".shopping_cart_link");
    private final String ADD_TO_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";
    private final By MENU = By.id("react-burger-menu-btn");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();
    }

    public void clickAddButton(String product) {
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCart).click();
    }

    public void clickShoppingCart() {
        driver.findElement(CART_LINK).click();
    }

    public void clickMenu() {
        driver.findElement(MENU).click();
    }

    private List<WebElement> getMenuItems() {
        return driver.findElements(By.className("menu-item"));
    }

    public int getSizeMenu() {
        return getMenuItems().size();
    }

    public String getMenuItemName(int counter) {
        return getMenuItems().get(counter).getText();
    }

}
