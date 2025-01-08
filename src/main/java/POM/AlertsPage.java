package POM;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AlertsPage {
    private final WebDriver driver;
    private final By buttonAlerts = By.xpath(".//span[contains(text(),'Alerts')]");

    private final String[] arrayButtonAlertInPage = new String[]{
            "alertButton",
            "timerAlertButton",
            "confirmButton",
            "promtButton"
    };
    private final By locatorConfirmText = By.id("confirmResult");
    private final By locatorPromptText = By.id("promptResult");

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    @Description("Клик по кнопке Alert для отображения списка кнопок оконо")
    public void clickButtonAlertOnLeftWindow() {
        WebElement element = driver.findElement(buttonAlerts);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(buttonAlerts).click();
    }

    @Step
    @Description("Клик на кнопку вызывающую всплывающее окно")
    public void clickButtonAlert() {
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
        } catch (UnhandledAlertException e) {
            // Обработка случая, когда алерт ,блочит
            return true;
        }
    }

    @Step
    @Description("Нажатие кнопки ОК в всплывающем окне")
    public void clickingOkInSimpleAlert() {
        Alert simpleAlert = driver.switchTo().alert();
        simpleAlert.accept();
    }

    @Step
    @Description("Метод проверки, что всплывающее окно не отображается")
    public boolean isNotPresentAlert() {
        try {
            //если алерт найден возвращаем false
            Alert alert = driver.switchTo().alert();
            alert.accept();
            return false;
        } catch (NoAlertPresentException e) {
            //если алерт не отобразился возвращаем true
            return true;
        } catch (UnhandledAlertException e) {
            // если найден другой алерт возвращаем false
            return false;
        }

    }

    @Step
    @Description("Клик по кнопке всплывающего окна, где при нажатии кнопки предупреждение появится через 5 секунд.")
    public void clickOnButtonWillAppearAfterFiveSeconds() {
        driver.findElement(By.id(arrayButtonAlertInPage[1])).click();
    }

    @Step
    @Description("Метод проверки что всплывающее окно не отобразится спустя 4 секунды")
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
    @Description("Метод проверки что всплывающее окно  отобразится спустя 5 секунд")
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
    public boolean alertIsNotVisibleAfterClickingButtonOk() {
        //Ожидание секунда
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        try {
            //Ожидаем отображение алерта, если отображается возвращаем false
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            return false;
        } catch (NoAlertPresentException e) {
            //если алерт не отобразился возвращаем true
            return true;
        } catch (UnhandledAlertException e) {
            // если найден другой алерт возвращаем false
            return false;
        } catch (TimeoutException e) {
            //если алерт не отобразился  возвращаем true
            return true;
        }
    }

    @Step
    @Description("Метод проверки на соответствующий текст")
    public boolean checkedTextInAlertVisibleAfterFiveSecond() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            if (text.equals("This alert appeared after 5 seconds")) {
                alert.accept();
                return true;
            } else {
                return false;
            }//Обработка на случай если алерт не появился
        } catch (NoAlertPresentException e) {
            return false;
        } catch (TimeoutException e) {
            // Обработка случая, когда алерт не появился в течение времени ожидания
            return false;
        } catch (UnhandledAlertException e) {
            // Обработка случая, когда алерт ,блочит
            return false;
        }
    }

    @Step
    @Description("Метод клика по кнопке, для того что бы появился алерт с вариантами выбора ОК или отменить")
    public void clickButtonAlertWithChoise() {
        driver.findElement(By.id(arrayButtonAlertInPage[2])).click();
    }

    @Step
    @Description("Метод нажатия кнопки ОК в алерте с выбором")
    public void clickingOkToAlertWithChoise() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("Всплывающее окно не отобразилось");
        } catch (UnhandledAlertException e) {
            System.out.println("Имеется открытое всплывающее окно");
        } catch (TimeoutException e) {
            System.out.println("Упал по тайм-ауту");
        }
    }

    @Step
    @Description("Метод нажатия кнопки Отмена в алерте с выбором")
    public void clickingCancelToAlertWithChoise() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            System.out.println("Всплывающее окно не отобразилось");
        } catch (UnhandledAlertException e) {
            System.out.println("Имеется открытое всплывающее окно");
        } catch (TimeoutException e) {
            System.out.println("Упал по тайм-ауту");
        }
    }

    @Step
    @Description("Метод проверки что после нажатия кнопки ОК в алерте с выбором, алерт успешно закрывается")
    public boolean checkingClosedAlertAfterClickingOkToAlertWithChoise() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return false;
        } catch (NoAlertPresentException e) {
            System.out.println("Всплывающее окно не отобразилось");
            return true;
        } catch (UnhandledAlertException e) {
            System.out.println("Имеется открытое всплывающее окно");
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    @Step
    @Description("Метод проверки что после нажатия кнопки ОК в алерте с выбором, алерт успешно закрывается")
    public boolean checkingClosedAlertAfterClickingCancelToAlertWithChoise() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return false;
        } catch (NoAlertPresentException e) {
            System.out.println("Всплывающее окно не отобразилось");
            return true;
        } catch (UnhandledAlertException e) {
            System.out.println("Имеется открытое всплывающее окно");
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    @Step
    @Description("Метод проверки отображения текста в названии алерта на странице при нажатии кнопки ОК")
    public boolean chekingTextAfterClickingOk() {
        WebElement element = driver.findElement(locatorConfirmText);
        String text = "You selected Ok";
        if (element.getText().equals(text)){
            return true;
        }else{
            System.out.println("Текст не совпадает");
            return false;
            }
    }
    @Step
    @Description("Метод проверки отображения текста в названии алерта на странице при нажатии кнопки Отменить")
    public boolean chekingTextAfterClickingCancel() {
        WebElement element = driver.findElement(locatorConfirmText);
        String text = "You selected Cancel";
        if (element.getText().equals(text)) {
            return true;
        } else {
            System.out.println("Текст не совпадает");
            return false;
        }
    }
        @Step
        @Description("Метод проверки отображения алерта, при нажатии на кнопку click me")
        public boolean alertWithChoiseIsVisble() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        try{
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        }catch(NoAlertPresentException e ){
            System.out.println("Окно не отобразилось");
            return false;
        }catch (UnhandledAlertException e ){
            System.out.println("Открыто другое всплывающее окно");
            return false;
        } catch(TimeoutException e ){
            System.out.println("ПО тайм-ауту алерт не отобразился");
            return false;
        }
    }
    @Step
    @Description ("Метод проверки текста на соответсвие в алерте")
    public String checkintTextAlertWithChoise(String text){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String actualText = alert.getText();
        if(actualText.equals(text)){
            return actualText;
        }else {
            return "Текст не соответствует: Ожидаемый результат: "+text+" "+"Фактический результат: "+actualText;
        }
    }
    @Step
    @Description("Метод клика по кнопке click me , алерта с полем для ввода")
    public void clickAlertWithFieldEnter(){
        driver.findElement(By.id(arrayButtonAlertInPage[3])).click();
    }
    @Step
    @Description("Метод ввода текста в открытом алерте в поле для ввода")
    public void sendTextInALertWithFieldEnter(String text){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        try{
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(text);
            alert.accept();
        }catch (NoAlertPresentException e){
            System.out.println("Алерт не отобразился");
        } catch (UnhandledAlertException e){
            System.out.println("Открыт другой алерт");
        } catch (TimeoutException e){
            System.out.println(" Не отобразился по тайм-ауту");
            }
        }
    @Step
    @Description("Метод ввода текста в открытом алерте в поле для ввода")
    public String getTextInAlertPromt (String expectedResult){
       Alert alert = driver.switchTo().alert();
       String actualResult = alert.getText();
       if (alert.getText().equals(expectedResult)){
           return actualResult;
       }else{
           return "Текст не соответствует: Ожидаемый результат: "+expectedResult+" "+"Фактический результат: "+actualResult;
       }

    }
    @Step
    @Description("Метод проверки, что после нажатия кнопки ОК, Алерт успешно закрылся и не отображается")
    public boolean alertPromtIsNotPresent(){
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(2));
        try{
            wait.until(ExpectedConditions.alertIsPresent());
            return false;
        }catch (NoAlertPresentException e){
            return true;
        } catch (UnhandledAlertException e ){
            return false;
        } catch (TimeoutException e){
            return true;
        }
    }
    @Step
    @Description("Метод пороверки отображения введенного текста на странице с алертом после нажатия ОК")
    public boolean checkingTextInPageAlertAfterClickingOkInAlertPrompt(String sendText){
        WebElement element = driver.findElement(locatorPromptText);
        if (element.getText().equals("You entered" + " "+sendText)){
            return true;
        }else {
            System.out.println("Текст не совпадает");
            return false;
        }
    }
    }
