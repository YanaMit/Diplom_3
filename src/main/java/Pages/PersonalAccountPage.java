package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {

    private WebDriver driver;
    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }


    private static final By PROFILE_BUTTON =
            By.xpath(".//*[text()='Профиль']");

    private static final By CONSTRUCTOR_BUTTON =
            By.xpath(".//*[text()='Конструктор']");

    private static final By LOGOTIP_BUTTON =
            By.xpath(".//*[@class='AppHeader_header__logo__2D0X2']/a");

    private static final By EXIT_BUTTON =
            By.xpath(".//button[text()='Выход']");


    public void clickButton(By button) {
        driver.findElement(button).click();
    }

    public boolean isVisible(By element) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }

    public String getTextProfileButton(){
        isVisible(PROFILE_BUTTON);
        String textProfile = driver.findElement(PROFILE_BUTTON).getText();
        return textProfile;
    }

    public void clickConstructorButton() {
        clickButton(CONSTRUCTOR_BUTTON);
    }

    public void clickLogotipButton() {
        clickButton(LOGOTIP_BUTTON);
    }

    public void isVisibleAndClickExitButton(){
        isVisible(EXIT_BUTTON);
        clickButton(EXIT_BUTTON);
    }


}
