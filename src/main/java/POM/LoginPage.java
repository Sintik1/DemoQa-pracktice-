package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    private By fieldLogin = By.className("Textinput-Control");
    private By buttonLogin = By.cssSelector("#item-0");

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }


    @Step
    public void clickFieldLogin(){
        driver.findElement(fieldLogin).click();
    }
    @Step
    public void sendFieldLogin(){
        driver.findElement(fieldLogin).sendKeys("vsentyakov");
    }

    @Step
    public void clickButtonLog(){
        driver.findElement(buttonLogin).click();
    }
}