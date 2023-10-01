import Pages.LogInPage;
import Pages.MainPage;
import Pages.PersonalAccountPage;
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

public class PersonalAccountTests  extends SetupBrowser {

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
    @DisplayName("Enter personal account from main page")
    public void checkEnterPersonalAccountFromMainPage () {

        MainPage mainPage = new MainPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        mainPage.isVisiblePersAccButtonAndClick();

        Assert.assertEquals("Профиль", personalAccountPage.getTextProfileButton()) ;
    }

    @Test
    @DisplayName("Enter constructor by pushing constructor from personal account")
    public void checkEnterContstructorPushingConstructor(){

        MainPage mainPage = new MainPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        mainPage.isVisiblePersAccButtonAndClick();
        personalAccountPage.clickConstructorButton();

        Assert.assertEquals("Соберите бургер", mainPage.isVisibleConstructorBurgerAreaAndGetText()) ;

    }

    @Test
    @DisplayName("Enter constructor by pushing logotip from personal account")
    public void checkEnterContstructorPushingLogotip(){

        MainPage mainPage = new MainPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        mainPage.isVisiblePersAccButtonAndClick();
        personalAccountPage.clickLogotipButton();

        Assert.assertEquals("Соберите бургер", mainPage.isVisibleConstructorBurgerAreaAndGetText()) ;

    }

    @Test
    @DisplayName("Logout from personal account")
    public void checkExitFromPersonalAccount(){

        LogInPage logInPage = new LogInPage(driver);
        MainPage mainPage = new MainPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        mainPage.isVisiblePersAccButtonAndClick();
        personalAccountPage.isVisibleAndClickExitButton();

        Assert.assertEquals("Войти",logInPage.waitVisibleEnterButtonAndGetText()) ;

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
