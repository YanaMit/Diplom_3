import Pages.LogInPage;
import Pages.MainPage;
import Pages.RegistrationPage;
import apiPart.API;
import apiPart.BaseURI;
import apiPart.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.WebDriver;
public class RegistrationTests extends SetupBrowser {


    private static WebDriver driver;
    private static final String NAME = "yana"+RandomStringUtils.randomNumeric(5);
    private static final String EMAIL = "yana1"+RandomStringUtils.randomNumeric(5)+"@yandex.ru";
    private static final String PASSWORD_SIX = "123456";
    private final String PASSWORD_FIVE = "12345";

    @BeforeClass
    public static void beforeClass() {
        RestAssured.baseURI = BaseURI.BURGER_SERVICE_URI;
    }
    @Before
    public void before() {

        this.driver = setupBrowser("Chrome");
    }

    @Test
    @DisplayName("Register with six symbols password - registration is done")
    public void checkSixSymbolPasswordRegistrationOk (){

        RegistrationPage regPage = new RegistrationPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.isVisiblePersAccButtonAndClick();
        logInPage.clickRegistrationButton();
        regPage.fullRegistration(NAME,EMAIL,PASSWORD_SIX);

        Assert.assertEquals("Войти", logInPage.waitVisibleEnterButtonAndGetText());
    }

    @Test
    @DisplayName("Register with five symbols password - error result")
    public void checkSixFiveSymbolPasswordRegistrationError (){

        RegistrationPage regPage = new RegistrationPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.isVisiblePersAccButtonAndClick();
        logInPage.clickRegistrationButton();
        regPage.fullRegistration(NAME,EMAIL,PASSWORD_FIVE);

        Assert.assertEquals("Некорректный пароль", regPage.waitVisibleErrorAndGetText());
    }

    @After
    public void after() {

        driver.quit();
    }

    @AfterClass
    public static void afterClass(){

        String accessKey = API.getAccessToken(new User(EMAIL, PASSWORD_SIX, NAME));
        API.deleteUser(accessKey);
    }


}
