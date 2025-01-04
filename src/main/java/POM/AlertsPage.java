package POM;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AlertsPage {
    private WebDriver driver;
    private final By buttonAlerts = By.xpath(".//span[contains(text(),'Alerts')]");

    private  final String[]arrayButtonAlertInPage=new String[]{
            "alertButton",
            "timerAlertButton",
            "confirmButton",
            "promtButton"
    };

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    @Description("Клик по кнопке Alert для отображения списка кнопок оконо")
    public void clickButtonAlertOnLeftWindow(){
        WebElement element = driver.findElement(buttonAlerts);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(buttonAlerts).click();
    }
    @Step
    @Description("Клик на кнопку вызывающую всплывающее окно")
    public void clickButtonAlert(){
        driver.findElement(By.id(arrayButtonAlertInPage[0])).click();
    }
    @Step
    @Description("Метод проверки, что всплывающее окно отобразилось")
    public boolean windowAllertIsVisible() {
        try {
            //нажимаем кнопку, которая вызывает окно
            driver.findElement(By.id(arrayButtonAlertInPage[0])).click();
            //ожидаем пока аллерт станет доступным
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            //переключаемся на аллерт
            Alert alert = driver.switchTo().alert();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            // Получаем текст из алерта
            String text = alert.getText();
            //сравниваем текст
            if (text.equals("You clicked a button")) {
                //закрываем аллерт кнопкой ОК
                alert.accept();
                return true;
            } else {
                return false;
            }
            //Обработка на случай если алерт не появился
        } catch (NoAlertPresentException e) {
            return false;
        } catch (TimeoutException e) {
            // Обработка случая, когда алерт не появился в течение времени ожидания
            return false;
        } catch (UnhandledAlertException e){
            // Обработка случая, когда алерт ,блочит
            return true;
        }
    }
    @Step
    @Description("Нажатие кнопки ОК в всплывающем окне")
    public void clickingOkInSimpleAlert(){
        Alert simpleAlert = driver.switchTo().alert();
        simpleAlert.accept();
    }
    @Step
    @Description("Метод проверки, что всплывающее окно не отображается")
    public boolean isNotPresentAlert(){
        try{
            //если алерт найден возвращаем false
            Alert alert=driver.switchTo().alert();
            alert.accept();
            return false;
        }catch (NoAlertPresentException e){
            //если алерт не отобразился возвращаем true
            return true;
        }catch (UnhandledAlertException e ){
            // если найден другой алерт возвращаем false
            return false;
        }

    }
    @Step
    @Description("Клик по кнопке всплывающего окна, где при нажатии кнопки предупреждение появится через 5 секунд.")
    public void clickOnButtonWillAppearAfterFiveSeconds(){
        driver.findElement(By.id(arrayButtonAlertInPage[1])).click();
    }
    @Step
    @Description ("Метод проверки что всплывающее окно не отобразится спустя 4 секунды")
    public boolean isNotPresentAlertAfterFourSecond() {
        //ожидание на 4 секунды
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        try {
            //Если алерт найден то возвращается исключение
            wait.until(ExpectedConditions.alertIsPresent());
            //если алерт найден возвращаем false
            return false;
        } catch (NoAlertPresentException e) {
            //если алерт не отобразился возвращаем true
            return true;
        } catch (UnhandledAlertException e) {
            // если найден другой алерт возвращаем false
            return false;
        } catch (TimeoutException e) {
            //если алерт не отобразился по тайм-ауту возвращаем true
            return true;
        }
    }
    @Step
    @Description ("Метод проверки что всплывающее окно  отобразится спустя 5 секунд")
    public boolean isPresentAlertAfterFiveSecond() {
        //ожидание на 5 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            //Если алерт найден то возвращается исключение
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (NoAlertPresentException e) {
            //если алерт не отобразился возвращаем false
            return false;
        } catch (UnhandledAlertException e) {
            // если найден другой алерт возвращаем false
            return false;
        } catch (TimeoutException e) {
            //если алерт не отобразился по тайм-ауту возвращаем false
            return false;
        }
    }
    @Step
    @Description("Метод нажатия кнопки ок ,во всплывающем окне которое появляется спустя 5 секунд")
    public void clickingToButtonOkInAlertAfterFiveSecond() {
        //Ожидание 5 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            //Ожидаем появления всплывающего окна, если появилось успешно закрываем
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            //Если не появилось, то выводим сообщение
            System.out.println("Всплывающее окно не отобразилось");
        } catch (UnhandledAlertException e) {
            //Если имеется другое открытое окно, то выводим сообщение
            System.out.println("Имеется другое открытое всплывающее окно");
        } catch (TimeoutException e) {
            //Если не появилось за 5 секунд, то выводим сообщение
            System.out.println("Всплывающее окно не отобразилось за 5 секунд");
        }
    }

    @Step
    @Description("Метод что после нажатия кнопки ОК, всплывающее окно закрылось  и не отображается")
    public boolean alertIsNotVisibleAfterClickingButtonOk(){
        //Ожидание секунда
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(1));
        try{
            //Ожидаем отображение алерта, если отображается возвращаем false
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            return false;
        }catch (NoAlertPresentException e){
            //если алерт не отобразился возвращаем true
            return true;
        }catch (UnhandledAlertException e){
            // если найден другой алерт возвращаем false
            return false;
        } catch (TimeoutException e){
            //если алерт не отобразился  возвращаем true
            return true;
        }
    }
    @Step
    @Description("Метод проверки на соответствующий текст")
    public boolean checkedTextInAlertVisibleAfterFiveSecond(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            if(text.equals("This alert appeared after 5 seconds")){
                alert.accept();
                return true;
            }else{
                return false;
            }//Обработка на случай если алерт не появился
        } catch (NoAlertPresentException e) {
            return false;
        } catch (TimeoutException e) {
            // Обработка случая, когда алерт не появился в течение времени ожидания
            return false;
        } catch (UnhandledAlertException e){
            // Обработка случая, когда алерт ,блочит
            return false;
        }
    }
}