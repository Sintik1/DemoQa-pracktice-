package POM;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class AlertsPage {
    private final WebDriver driver;
    private final By buttonAlerts = By.xpath(".//span[contains(text(),'Alerts')]");

   /* private final String[] arrayButtonAlertInPage = new String[]{
            "alertButton",
            "timerAlertButton",
            "confirmButton",
            "promtButton"
    };*/
    public enum AlertButton{
       ALERT_BUTTON("alertButton"),
       TIMER_ALERT_BUTTON("timerAlertButton"),
       CONFIRM_BUTTON("confirmButton"),
       PROMPT_BUTTON("promtButton");

        private final String id;


       AlertButton(String id) {
           this.id = id;
       }
       public String getId(){
           return id;
       }
   }
    private final By locatorConfirmText = By.id("confirmResult");
    private final By locatorPromptText = By.id("promptResult");

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    @Description("Клик по кнопке Alert для отображения списка кнопок окон")
    public void clickButtonAlertOnLeftWindow() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonAlerts));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Step
    private void waitForAlertAndAccept(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    @Step
    private void handleAlertException(Exception e){
        System.out.println("Произошла ошибка: "+e.getMessage());
    }
    @Step
    @Description("Клик на кнопку вызывающую всплывающее окно")
    public void clickButtonAlert() {
        click(By.id(AlertButton.ALERT_BUTTON.getId()));
    }

    @Step
    @Description("Метод проверки, что всплывающее окно отобразилось")
    public boolean windowAllertIsVisible() {
        try {
            //нажимаем кнопку, которая вызывает окно
            driver.findElement(By.id(AlertButton.ALERT_BUTTON.getId())).click();
            //ожидаем пока аллерт станет доступным
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.alertIsPresent());
            //переключаемся на аллерт
            Alert alert = driver.switchTo().alert();
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
        } catch (NoAlertPresentException|TimeoutException e){
            handleAlertException(e);
            return false;
        } catch (UnhandledAlertException e) {
            // Обработка случая, когда алерт ,блочит
            return true;
        }
    }

    @Step
    @Description("Нажатие кнопки ОК в всплывающем окне")
    public void clickingOkInSimpleAlert() {
        waitForAlertAndAccept();
    }

    @Step
    @Description("Метод проверки, что всплывающее окно не отображается")
    public boolean isNotPresentAlert() {
        try {
            //если алерт найден возвращаем false
            waitForAlertAndAccept();
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
        driver.findElement(By.id(AlertButton.TIMER_ALERT_BUTTON.getId())).click();
    }

    @Step
    @Description("Метод проверки что всплывающее окно не отобразится спустя 4 секунды")
    public boolean isNotPresentAlertAfterFourSecond() {
        //ожидание на 4 секунды
        WebDriverWait wait = new WebDriverWait(driver, 4);
        try {
            //Если алерт найден то возвращается исключение
            wait.until(ExpectedConditions.alertIsPresent());
            //если алерт найден возвращаем false
            return false;
        } catch (NoAlertPresentException | TimeoutException e) {
            //если алерт не отобразился возвращаем true
            handleAlertException(e);
            return true;
        } catch (UnhandledAlertException e) {
            // если найден другой алерт возвращаем false
            return false;
        }
    }

    @Step
    @Description("Метод проверки что всплывающее окно  отобразится спустя 5 секунд")
    public boolean isPresentAlertAfterFiveSecond() {
        //ожидание на 5 секунд
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            //Если алерт найден то возвращается исключение
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (NoAlertPresentException |UnhandledAlertException|TimeoutException e) {
            handleAlertException(e);
            return false;
        }
    }

    @Step
    @Description("Метод нажатия кнопки ок ,во всплывающем окне которое появляется спустя 5 секунд")
    public void clickingToButtonOkInAlertAfterFiveSecond() {
        try {
            //Ожидаем появления всплывающего окна в течении 5 секунд , если появилось успешно закрываем
            waitForAlertAndAccept();
        } catch (NoAlertPresentException | UnhandledAlertException | TimeoutException e) {
            handleAlertException(e);
        }
    }

    @Step
    @Description("Метод что после нажатия кнопки ОК, всплывающее окно закрылось  и не отображается")
    public boolean alertIsNotVisibleAfterClickingButtonOk() {
        //Ожидание секунда
        WebDriverWait wait = new WebDriverWait(driver, 1);
        try {
            //Ожидаем отображение алерта, если отображается возвращаем false
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            return false;
        } catch (NoAlertPresentException |TimeoutException e) {
            handleAlertException(e);
            return true;
        } catch (UnhandledAlertException e) {
            // если найден другой алерт возвращаем false
            return false;
        }
    }

    @Step
    @Description("Метод проверки на соответствующий текст")
    public boolean checkedTextInAlertVisibleAfterFiveSecond() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
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
        } catch (NoAlertPresentException |UnhandledAlertException|TimeoutException e){
            handleAlertException(e);
            return false;
        }
    }

    @Step
    @Description("Метод клика по кнопке, для того что бы появился алерт с вариантами выбора ОК или отменить")
    public void clickButtonAlertWithChoise() {
       click(By.id(AlertButton.CONFIRM_BUTTON.getId()));
    }

    @Step
    @Description("Метод нажатия кнопки ОК в алерте с выбором")
    public void clickingOkToAlertWithChoise() {
        try {
            waitForAlertAndAccept();
        } catch (NoAlertPresentException | UnhandledAlertException | TimeoutException e) {
            handleAlertException(e);
        }
    }

    @Step
    @Description("Метод нажатия кнопки Отмена в алерте с выбором")
    public void clickingCancelToAlertWithChoise() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException | UnhandledAlertException | TimeoutException e) {
            handleAlertException(e);
        }
    }

    @Step
    @Description("Метод проверки что после нажатия кнопки ОК в алерте с выбором, алерт успешно закрывается")
    public boolean checkingClosedAlertAfterClickingOkToAlertWithChoise() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return false;
        } catch (NoAlertPresentException | TimeoutException e) {
            handleAlertException(e);
            return true;
        } catch (UnhandledAlertException e) {
            System.out.println("Имеется открытое всплывающее окно");
            return false;
        }
    }

    @Step
    @Description("Метод проверки что после нажатия кнопки ОК в алерте с выбором, алерт успешно закрывается")
    public boolean checkingClosedAlertAfterClickingCancelToAlertWithChoise() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return false;
        } catch (NoAlertPresentException | TimeoutException e) {
            handleAlertException(e);
            return true;
        } catch (UnhandledAlertException e) {
            System.out.println("Имеется открытое всплывающее окно");
            return false;
        }
    }

    @Step
    @Description("Метод проверки отображения текста в названии алерта на странице при нажатии кнопки ОК")
    public boolean chekingTextAfterClickingOk() {
        WebElement element = driver.findElement(locatorConfirmText);
        String text = "You selected Ok";
        if (element.getText().equals(text)) {
            return true;
        } else {
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
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (NoAlertPresentException | UnhandledAlertException | TimeoutException e) {
            handleAlertException(e);
            return false;
        }
    }

    @Step
    @Description("Метод проверки текста на соответсвие в алерте")
    public String checkintTextAlertWithChoise(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String actualText = alert.getText();
        if (actualText.equals(text)) {
            return actualText;
        } else {
            return "Текст не соответствует: Ожидаемый результат: " + text + " " + "Фактический результат: " + actualText;
        }
    }

    @Step
    @Description("Метод клика по кнопке click me , алерта с полем для ввода")
    public void clickAlertWithFieldEnter() {
        click(By.id(AlertButton.PROMPT_BUTTON.getId()));
    }

    @Step
    @Description("Метод ввода текста в открытом алерте в поле для ввода")
    public void sendTextInALertWithFieldEnter(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(text);
            alert.accept();
        } catch (NoAlertPresentException | UnhandledAlertException | TimeoutException e) {
            handleAlertException(e);
        }
    }

    @Step
    @Description("Метод ввода текста в открытом алерте в поле для ввода")
    public String getTextInAlertPromt(String expectedResult) {
        Alert alert = driver.switchTo().alert();
        String actualResult = alert.getText();
        if (alert.getText().equals(expectedResult)) {
            return actualResult;
        } else {
            return "Текст не соответствует: Ожидаемый результат: " + expectedResult + " " + "Фактический результат: " + actualResult;
        }
    }

    @Step
    @Description("Метод проверки, что после нажатия кнопки ОК, Алерт успешно закрылся и не отображается")
    public boolean alertPromtIsNotPresent() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return false;
        } catch (NoAlertPresentException | TimeoutException e) {
            handleAlertException(e);
            return true;
        } catch (UnhandledAlertException e) {
            return false;
        }
    }

    @Step
    @Description("Метод пороверки отображения введенного текста на странице с алертом после нажатия ОК")
    public boolean checkingTextInPageAlertAfterClickingOkInAlertPrompt(String sendText) {
        WebElement element = driver.findElement(locatorPromptText);
        if (element.getText().equals("You entered" + " " + sendText)) {
            return true;
        } else {
            System.out.println("Текст не совпадает");
            return false;
        }
    }

    @Step
    @Description("Метод проверки отображения элемента при отправке пустого поля")
    public boolean presentIsEmptyText() {
        try {
            List<WebElement> elements = driver.findElements(locatorPromptText);
            if (elements.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException |TimeoutException e) {
            handleAlertException(e);
            return true;
        } catch (UnhandledAlertException e) {
            System.out.println("Открыт другой алерт: " + e.getMessage());
            return false;
        }

    }
    @Step
    @Description("Метод закрытия алерта с вводом текста")
    public void closedAlertPrompt(String sendText){
        try{
            WebDriverWait wait = new WebDriverWait(driver,3);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(sendText);
            alert.dismiss();
        } catch (NoAlertPresentException | UnhandledAlertException | TimeoutException e) {
            handleAlertException(e);
        }
    }
    public void click(By locator){
        driver.findElement(locator).click();
    }
}