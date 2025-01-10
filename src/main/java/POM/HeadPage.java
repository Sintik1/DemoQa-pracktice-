package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeadPage {
    private WebDriver driver;
    private By buttonForms = By.xpath(".//h5[contains(text(),'Forms')]");
    private By buttonAllerts = By.xpath(".//h5[contains(text(),'Alerts, Frame & Windows')]");
    private By headPageBanner = By.xpath(".//div[@class='home-banner']");

    public HeadPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void clickButtonForms(){
        WebElement element = driver.findElement(buttonForms);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(buttonForms).click();
    }

    @Step
    public void clickButtonAllert(){
        WebElement element = driver.findElement(buttonAllerts);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(buttonAllerts).click();
    }
    @Step
    public boolean headPageIsDisplayed(){
        try {
            WebDriverWait wait = new WebDriverWait(driver,5);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(headPageBanner));
            return element.isDisplayed();
        }catch (TimeoutException e){
            System.out.println("Элемент не найден, в течении времени ожидания");
            return false;
        }catch (NoSuchElementException e){
            System.out.println("Элемент не существует");
            return false;
        }
    }
}
