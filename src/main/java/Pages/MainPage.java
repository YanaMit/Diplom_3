package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class MainPage {

    //адрес стартовой страницы
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    //кнопка Л.к.
    private static final By PERSONAL_ACCOUNT_BUTTON =
            By.xpath(".//*[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");

    //кнопка войти на главной стр
    private static final By MAIN_PAGE_ENTER_BUTTON =
            By.xpath (".//button[text()='Войти в аккаунт']");

    //кнопка оформить заказ
    private static final By MAKE_ORDER_BUTTON =
            By.xpath(".//button[text()='Оформить заказ']");

    private static final By BURGER_CONSTRUCTOR_AREA =
            By.xpath(".//*[text()='Соберите бургер']");
    private static final By BUN_BUTTON =
            By.xpath(".//*[contains(@class, 'tab')]/*[text()='Булки']");

    private static final By BUN_TITLE =
            By.xpath(".//div/h2[@class='text text_type_main-medium mb-6 mt-10'][text()='Булки']");

    private static final By SAUCE_BUTTON =
            By.xpath(".//*[contains(@class, 'tab')]/*[text()='Соусы']");

    private static final By SAUCE_TITLE =
            By.xpath(".//div/h2[@class='text text_type_main-medium mb-6 mt-10'][text()='Соусы']");

    private static final By FILLING_BUTTON =
            By.xpath(".//*[contains(@class, 'tab')]/*[text()='Начинки']");

    private static final By FILLING_TITLE =
            By.xpath(".//div/h2[@class='text text_type_main-medium mb-6 mt-10'][text()='Начинки']");



    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    //открыть стартовую страницу
    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public void clickButton(By button) {
        isVisible(button);
        driver.findElement(button).click();
    }

    public boolean isVisible(By element) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }


    //дождаться видимости кнопки личн. кабинет и кликнуть на нее
    public void   isVisiblePersAccButtonAndClick () {
        isVisible(PERSONAL_ACCOUNT_BUTTON);
        clickButton(PERSONAL_ACCOUNT_BUTTON);
    }

    //дождаться видимости кнопки войти и кликнуть на нее
    public void   isVisibleEnterButtonAndClick () {
        isVisible(MAIN_PAGE_ENTER_BUTTON);
        clickButton(MAIN_PAGE_ENTER_BUTTON);
    }

    //дождаться видимости кнопки оформить заказ и получить ее текст
    public String   isVisibleMakeOrderButtonAndGetText () {
        isVisible(MAKE_ORDER_BUTTON);
        String makeOrderButtonText = driver.findElement(MAKE_ORDER_BUTTON).getText();
        return makeOrderButtonText;
    }

    public String isVisibleConstructorBurgerAreaAndGetText(){
        isVisible(BURGER_CONSTRUCTOR_AREA);
        String burgerConstructurText = driver.findElement(BURGER_CONSTRUCTOR_AREA).getText();
        return burgerConstructurText;
    }

    public void clickBunButton(){
        clickButton(BUN_BUTTON);
    }

    public String getTextFromBunTitle (){
        isVisible(BUN_TITLE);
        String bunText = driver.findElement(BUN_TITLE).getText();
        return bunText;
    }

    public void clickSauceButton(){
        clickButton(SAUCE_BUTTON);
    }

    public String getTextFromSauceTitle (){
        isVisible(SAUCE_TITLE);
        String bunText = driver.findElement(SAUCE_TITLE).getText();
        return bunText;
    }

    public void clickBFillingButton(){
        clickButton(FILLING_BUTTON);
    }

    public String getTextFromFillingTitle (){
        isVisible(FILLING_TITLE);
        String bunText = driver.findElement(FILLING_TITLE).getText();
        return bunText;
    }


}
