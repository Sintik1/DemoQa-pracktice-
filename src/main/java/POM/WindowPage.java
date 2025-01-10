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
    private final By newWindowSelector = By.id("sampleHeading");

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
    public boolean setWindowInList() {
            //Записали индентификатор главного окна
            String mainWindowHandle = driver.getWindowHandle();
            //Записали идентификаторы всех окон включая дочерние
            Set<String> allWindowHandles = driver.getWindowHandles();
            //Cоздаем итератор для перебора всех окон
            Iterator<String> iterator = allWindowHandles.iterator();

            // Здесь мы проверим, есть ли у дочернего окна другие дочерние окна, и получим заголовок дочернего окна
            while (iterator.hasNext()) {
                String childWindow = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(childWindow)) {
                    driver.switchTo().window(childWindow);
                    WebDriverWait wait = new WebDriverWait(driver, 10);
                    try {
                        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(newWindowSelector));
                        return element.isDisplayed();
                    } catch (Exception e) {
                     handleException(e);
                    }
                }
            }
        //если окно не содержит элемент
        return false;
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