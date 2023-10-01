package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class LogInPage {

    private WebDriver driver;
    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }


    private static final By ENTER_BUTTON =
            By.xpath(".//button[text()='Войти']");
    private static final By EMAIL_INPUT_FIELD =
            By.xpath(".//label[contains(text(), 'Email')]/" +
                    "following-sibling::input[@class='text input__textfield text_type_main-default']");
    private static final By PASSWORD_INPUT_FIELD =
            By.xpath(".//label[contains(text(), 'Пароль')]/" +
                    "following-sibling::input[@class='text input__textfield text_type_main-default']");
    private static final By REGISTRATION_BUTTON =
            By.xpath(".//*[@class='Auth_link__1fOlj' and text()='Зарегистрироваться']");
    private static final By RECOVER_PASSWORD_BUTTON =
            By.xpath(".//*[text()='Восстановить пароль']");


    //методы:
    public void clickButton(By button) {
        driver.findElement(button).click();
    }

    public boolean isVisible(By element) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }
    public void scroll(By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
    }


    //дождаться видимости кнопки войти и получить ее текст
    public String  waitVisibleEnterButtonAndGetText () {
        isVisible(ENTER_BUTTON);
        String realResult = driver.findElement(ENTER_BUTTON).getText();
        return realResult;
    }


    //шаг доскроллить до кнопки регистрация, проверить видимость кнопки и нажать на нее
    public void scrollAndClickRegistrationButton(){
        scroll(REGISTRATION_BUTTON);
        isVisible(REGISTRATION_BUTTON);
        clickButton(REGISTRATION_BUTTON);
    }

    //шаг нажать на поле емаил и заполнить его, нажать на поле пароль и заполнить его
    public void fillEmailAndPasswordFields(String email, String password) {
        clickButton(EMAIL_INPUT_FIELD);
        driver.findElement(EMAIL_INPUT_FIELD).sendKeys(email);
        clickButton(PASSWORD_INPUT_FIELD);
        driver.findElement(PASSWORD_INPUT_FIELD).sendKeys(password);

    }

    public void clickEnterButton () {
        clickButton(ENTER_BUTTON);
    }

    //доскроллить до кнопки восстановить пароль и нажать на нее
    public void scrollAndClickRecoverPasswordButton(){
        scroll(RECOVER_PASSWORD_BUTTON);
        isVisible(RECOVER_PASSWORD_BUTTON);
        clickButton(RECOVER_PASSWORD_BUTTON);
    }

    //залогиниться с емаилом и паролем
    public void fullLogIn (String email, String password) {
        fillEmailAndPasswordFields(email, password);
        clickEnterButton();
    }

    public void clickRegistrationButton () {
        isVisible(ENTER_BUTTON);
        scroll(REGISTRATION_BUTTON);
        clickButton(REGISTRATION_BUTTON);
    }

}
