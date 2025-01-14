package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;

public class WindowPage {
    private final WebDriver driver;
    private final By buttonBrowseWindow = By.xpath(".//span[contains(text(),'Browser Windows')]");
    private final By newWindowSelector = By.xpath(".//h1[contains(text(),'This is a sample page')]");
    private final By windowMessageSelector = By.xpath("/html/body");

    public enum ButtonOnTheWindowBrowserWindows {
        BUTTON_NEW_TAB("tabButton"),
        BUTTON_NEW_WINDOW("windowButton"),
        BUTTON_NEW_WINDOW_MESSAGE("messageWindowButton");

        private String id;

        ButtonOnTheWindowBrowserWindows(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
    public enum ChildWindow {
        OPEN_NEW_WINDOW(".//h1[contains(text(),'This is a sample page')]"),
        OPEN_WINDOW_MESSAGE(".//body[contains(text(),'Knowledge increases')]");
        private String xpath;

        ChildWindow(String xpath){
            this.xpath=xpath;
        }
        public String getXpath(){
            return xpath;
        }
    }

    public WindowPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод клика по кнопке Browser Window,слева на странице")
    public void clickButtonBrowserWindows() {
        click(buttonBrowseWindow);
    }

    @Step("Метод клика по кнопке на странице окон")
    public void clickButtonWindows(String window) {
        click(By.id(window));
    }

    @Step("Метод проверки отображения нового окна")
    public boolean setWindowInList(String selectorChildWindow) {
            //Записали индентификатор главного окна
            String mainWindowHandle = driver.getWindowHandle();
            //Записали идентификаторы всех окон включая дочерние
            Set<String> allWindowHandles = driver.getWindowHandles();
            //Cоздаем итератор для перебора всех окон
            Iterator<String> iterator = allWindowHandles.iterator();

            // Здесь мы проверим, есть ли у дочернего окна другие дочерние окна, и проверим отображение  дочернего окна
            while (iterator.hasNext()) {
                String childWindow = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(childWindow)) {
                    driver.switchTo().window(childWindow);
                    WebDriverWait wait = new WebDriverWait(driver, 60);
                    try {
                        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectorChildWindow)));
                        return element.isDisplayed();
                    } catch (Exception e) {
                     handleException(e);
                    }
                }
            }
        //если окно не содержит элемент
        return false;
    }

    @Step("Метод проверки текста, в новом дочернем окне")
    public String checkedTextNewWindow(String selectorChildWindow){
        //Записали индентификатор главного окна
        String mainWindowHandle = driver.getWindowHandle();
        //Записали все окна включая дочерние
        Set<String>allHandleWindows = driver.getWindowHandles();
        //Cоздаем итератор для перебора всех окон
        Iterator<String>iterator= allHandleWindows.iterator();

        // Здесь мы проверим, есть ли у дочернего окна другие дочерние окна, и проверим заголовок  дочернего окна
        while (iterator.hasNext()){
            String childWindow = iterator.next();
            if(!mainWindowHandle.equalsIgnoreCase(childWindow)){
                driver.switchTo().window(childWindow);
                WebDriverWait wait = new WebDriverWait(driver,10);
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectorChildWindow)));
                String actualText = driver.findElement(By.xpath(selectorChildWindow)).getText();
                return actualText;
            }catch (Exception e){
                handleException(e);
            }
            }
        }return "Текст отличается , тест не удался";
    }
    @Step("Метод проверки отображения окна с сообщением")
    public boolean isDisplayedWindowMessage(){
        //Записали в переменную id Главного окна
        String mainWindowHandle = driver.getWindowHandle();
        //Записали в список дочерние окна
        Set<String>allWindowHandles=driver.getWindowHandles();
        //Создали итератор для запис перечисления окон
        Iterator<String>iterator=allWindowHandles.iterator();

        //Проверяем наличие дочернего окна и его отображение
        while (iterator.hasNext()){
            String childWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(childWindow)){
                driver.switchTo().window(childWindow);
                WebDriverWait wait = new WebDriverWait(driver,5);
                try{
                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(windowMessageSelector));
                    return element.isDisplayed();
                }catch (Exception e){
                    handleException(e);
                }
            }
        }return false;
    }
    @Step("Метод проверки текста на соответствие ожидаемому результату, в окне с сообщением")
    public String checkedTextInWindowMessage(){
        String mainWindowHandle = driver.getWindowHandle();
        Set<String>allWindowHandles=driver.getWindowHandles();
        Iterator<String>iterator=allWindowHandles.iterator();

        while (iterator.hasNext()){
            String childWindow=iterator.next();
            if(!mainWindowHandle.equalsIgnoreCase(childWindow)){
                driver.switchTo().window(childWindow);
                WebDriverWait wait = new WebDriverWait(driver,5);
                try{
                    wait.until(ExpectedConditions.visibilityOfElementLocated(windowMessageSelector));
                    WebElement element = driver.findElement(windowMessageSelector);
                    String actualResult = element.getText();
                    return actualResult;
                }catch (Exception e){
                    handleException(e);
                }
            }
        }return "Текст не соответствует требованиям,тест не удался";
    }
    @Step("Вспомогательный метод обработки исключений")
     public void handleException(Exception e){
        System.out.println("Произошла ошибка: "+e.getMessage());
    }
     @Step("Вспомогательный метод клика по кнопке")
     public void click(By locator){
         driver.findElement(locator).click();
 }




}