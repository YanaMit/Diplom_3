import Pages.LogInPage;
import Pages.MainPage;
import Pages.RecoverPasswordPage;
import Pages.RegistrationPage;
import apiPart.API;
import apiPart.BaseURI;
import apiPart.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class EnterTests extends SetupBrowser {

    private WebDriver driver;
    private static final String NAME = "yana"+RandomStringUtils.randomNumeric(5);
    private static final String EMAIL = "yana1"+RandomStringUtils.randomNumeric(5)+"@yandex.ru";
    private static final String PASSWORD = "123456";
    public static final User USER = new User(EMAIL, PASSWORD, NAME);
    private static String accessToken;


    @BeforeClass
    public static void beforeClass() {

        RestAssured.baseURI = BaseURI.BURGER_SERVICE_URI;
        Response response = API.createUser(USER);
        response.then().statusCode(SC_OK).and().assertThat().body("success", equalTo(true));
        accessToken = response.then().extract().path("accessToken").toString();
    }

    @Before
    public void before() {

        this.driver = setupBrowser("Chrome");
    }

    @Test
    @DisplayName("Enter from main page")
    public void enterByMainPageEnterButton () {

        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);

        mainPage.open();
        mainPage.isVisibleEnterButtonAndClick();
        logInPage.fullLogIn(EMAIL,PASSWORD);

        Assert.assertEquals("Оформить заказ", mainPage.isVisibleMakeOrderButtonAndGetText());

    }

    @Test
    @DisplayName("Enter by personal account button")
    public void enterByPersonalAccountButton () {

        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);

        mainPage.open();
        mainPage.isVisiblePersAccButtonAndClick();
        logInPage.fullLogIn(EMAIL,PASSWORD);

        Assert.assertEquals("Оформить заказ", mainPage.isVisibleMakeOrderButtonAndGetText());

    }

    @Test
    @DisplayName("Enter from registration page")
    public void enterByRegFormEnterButton () {

        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        mainPage.open();
        mainPage.isVisiblePersAccButtonAndClick();
        logInPage.waitVisibleEnterButtonAndGetText();
        logInPage.scrollAndClickRegistrationButton();
        registrationPage.scrollAndClickRegistrationEnterButton();

        logInPage.fullLogIn(EMAIL,PASSWORD);

        Assert.assertEquals("Оформить заказ", mainPage.isVisibleMakeOrderButtonAndGetText());

    }

    @Test
    @DisplayName("Enter by password recovery form")
    public void enterByPasswordRecoveryForm () {

        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);

        mainPage.open();
        mainPage.isVisibleEnterButtonAndClick();
        logInPage.scrollAndClickRecoverPasswordButton();
        recoverPasswordPage.clickEnterRecPasButton();

        logInPage.fullLogIn(EMAIL,PASSWORD);

        Assert.assertEquals("Оформить заказ", mainPage.isVisibleMakeOrderButtonAndGetText());

    }

    @After
    public void after() {

        driver.quit();
    }

    @AfterClass
    public static void afterClass() {

        API.deleteUser(accessToken);
    }

}
