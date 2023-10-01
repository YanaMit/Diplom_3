import Pages.*;
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

public class ConstructorTests  extends SetupBrowser {

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
    public void before()  {

        this.driver = setupBrowser("Chrome");
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        mainPage.open();
        mainPage.isVisibleEnterButtonAndClick();
        logInPage.fullLogIn(EMAIL,PASSWORD);
    }

    @Test
    @DisplayName("Checking of transition to the bun section")
    public void checkTransitionToBun () {

        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        Assert.assertEquals("Булки", mainPage.getTextFromBunTitle());
    }

    @Test
    @DisplayName("Checking of transition to the sauce section")
    public void checkTransitionToSauce () {

        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        Assert.assertEquals("Соусы", mainPage.getTextFromSauceTitle());
    }

    @Test
    @DisplayName("Checking of transition to the filling section")
    public void checkTransitionToFilling () {

        MainPage mainPage = new MainPage(driver);
        mainPage.clickBFillingButton();
        Assert.assertEquals("Начинки", mainPage.getTextFromFillingTitle());
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
