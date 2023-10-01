package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
public class RegistrationPage {

    private WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }


    //поля регистрации
    private static final By NAME_FIELD =
            By.xpath(".//label[contains(text(), 'Имя')]/" +
                    "following-sibling::input[@class='text input__textfield text_type_main-default']");

    private static final By EMAIL_FIELD =
            By.xpath(".//label[contains(text(), 'Email')]" +
                    "/following-sibling::input[@class='text input__textfield text_type_main-default']");

    private static final By PASSWORD_FIELD =
            By.xpath(".//label[contains(text(), 'Пароль')]" +
                    "/following-sibling::input[@class='text input__textfield text_type_main-default']");


    //кнопка регистрации
    private static final By REGISTRATION_BUTTON =
            By.xpath(".//button[contains(text(), 'Зарегистрироваться')]");

    //error registration
    private static final By ERROR_FIELD =
            By.xpath(".//*[@class='input__error text_type_main-default']");

    //кгопка войти на стр регистрации
    private static final By REG_ENTER_BUTTON =
            By.xpath(".//*[@class='Auth_link__1fOlj']");


    //methods
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


    //заполнение полей
    public void fillNameField (String name) {
        clickButton(NAME_FIELD);
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    public void fillEmailField (String email) {
        clickButton(EMAIL_FIELD);
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    public void fillPasswordField (String password) {
        clickButton(PASSWORD_FIELD);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    //шаг - заполнить ВСЕ поля
    public void fillAllFields(String name, String email, String password){
        fillNameField (name);
        fillEmailField(email);
        fillPasswordField(password);
    }

    public void fullRegistration(String name, String email, String password){
        fillAllFields(name,email, password);
        clickButton(REGISTRATION_BUTTON);
    }


   //дождаться появления надписиоб ошибки и получить ее текст
    public String  waitVisibleErrorAndGetText () {
        isVisible(ERROR_FIELD);
        String realResult = driver.findElement(ERROR_FIELD).getText();
        return realResult;
    }


    //доскроллить до кнопки регистрация и кликнуть на нее
    public void scrollAndClickRegistrationEnterButton(){
        scroll(REG_ENTER_BUTTON);
        isVisible(REG_ENTER_BUTTON);
        clickButton(REG_ENTER_BUTTON);
    }


}
