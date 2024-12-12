package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {

    private final By USER_INPUT = By.id("user-name");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.xpath("//h3[@data-test = 'error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы логина")
    public void open(WebDriver driver) {
        log.info("Open LoginPage");
        driver.get(BASE_URL);
    }

    @Step("Вход в систему с логином {user} и паролем {password}")
    public void login(String user, String password) {
        log.info("Log into the system");
        driver.findElement(USER_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Получение сообщения об ошибке")
    public String getError() {
        log.info("Receive error message");
        return driver.findElement(ERROR_MESSAGE).getText();
    }

}
