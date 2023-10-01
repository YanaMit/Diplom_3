package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {

    private WebDriver driver;
    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    //кнопка войти на стр восстановления пароля
    private static final By ENTER_REC_PAS_BUTTON =
            By.xpath(".//*[text()='Войти']");

    public void clickButton(By button) {
        driver.findElement(button).click();
    }

    //нажать на кнопку войти на стр восстановления пароля
    public void clickEnterRecPasButton(){
        clickButton(ENTER_REC_PAS_BUTTON);
    }


}
