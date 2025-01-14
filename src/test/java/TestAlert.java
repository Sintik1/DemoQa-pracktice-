import POM.AlertsPage;
import POM.HeadPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;


public class TestAlert {
    private WebDriver driver;
    private static final String BASE_URL = "https://demoqa.com/";

    @Before
    public void setup() {
        /*WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        driver=new ChromeDriver(options);*/
        System.setProperty("webdriver.opera.driver","src/main/java/resources/operadriver.exe");
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.addArguments("--headless"); // Запуск в headless режиме, если необходимо
        operaOptions.addArguments("--no-sandbox");
        operaOptions.addArguments("--disable-dev-shm-usage");
        driver = new OperaDriver(operaOptions);
        driver.manage().window().maximize();
        navigateToAlertsPage();
    }

    private void navigateToAlertsPage() {
        driver.get(BASE_URL);
        HeadPage headPage = new HeadPage(driver);
        headPage.clickButtonAllert();
    }

    @Test
    @Description("Проверка отображения всплывающего окна")
    public void testAlertIsVisible() {
        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.clickButtonAlertOnLeftWindow();
        alertsPage.clickButtonAlert();
        Assert.assertTrue("Всплывающее окно не отобразилось", alertsPage.windowAllertIsVisible());
    }

    @Test
    @Description("Прямой сценарий работы простого всплывающего окна, должен отобразится и при нажатии кнопки ОК закрыться")
    public void shouldClickToButtonOkInAlert() {
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickButtonAlert();
        objAlertsPage.clickingOkInSimpleAlert();
        boolean alertIsNotVisible = objAlertsPage.isNotPresentAlert();
        Assert.assertTrue("Всплывающее окно отображается", alertIsNotVisible);
    }

    @Test
    @Description("Сценарий проверки что всплывающее окно не отобразилось спустя 4 секунды")
    public void checkedAlertDidNotAppearAfterFourSeconds() {
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickOnButtonWillAppearAfterFiveSeconds();
        boolean alertIsNotVisible = objAlertsPage.isNotPresentAlertAfterFourSecond();
        Assert.assertTrue("Всплывающее окно отобразилось", alertIsNotVisible);
    }

    @Test
    @Description("Сценарий проверки что всплывающее окно не отобразилось спустя 4 секунды")
    public void checkedAlertIsVisibleAfterFiveSeconds() {
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickOnButtonWillAppearAfterFiveSeconds();
        boolean alertIsNotVisible = objAlertsPage.isPresentAlertAfterFiveSecond();
        Assert.assertTrue("Всплывающее окно  не отобразилось", alertIsNotVisible);

    }

    @Test
    @Description("Сценарий проверки что всплывающее окно успешно закрывается при нажатии кнопки ОК")
    public void checkedAlertIsClickingOk() {
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickOnButtonWillAppearAfterFiveSeconds();
        objAlertsPage.clickingToButtonOkInAlertAfterFiveSecond();
        boolean alertIsNotVisibleAfterClosed = objAlertsPage.alertIsNotVisibleAfterClickingButtonOk();
        Assert.assertTrue("Всплывающее окно отобразилось", alertIsNotVisibleAfterClosed);
    }

    @Test
    @Description("Проверка на соответствие текста")
    public void checkedTextIsAlertFiveSecond() {
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickOnButtonWillAppearAfterFiveSeconds();
        boolean expectedResult = objAlertsPage.checkedTextInAlertVisibleAfterFiveSecond();
        Assert.assertTrue("Тест не удался", expectedResult);
    }

    @Test
    @Description("Прямой сценарий обработки алерта с нажатием ОК ")
    public void shouldAlertClickingOk() {
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickButtonAlertWithChoise();
        objAlertsPage.clickingOkToAlertWithChoise();
        boolean expectedResult = objAlertsPage.checkingClosedAlertAfterClickingOkToAlertWithChoise();
        Assert.assertTrue("Окно не закрылось", expectedResult);
    }

    @Test
    @Description("Прямой сценарий обработки алерта с нажатием ОК ")
    public void shouldAlertClickingCancel() {
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickButtonAlertWithChoise();
        objAlertsPage.clickingCancelToAlertWithChoise();
        boolean expectedResult = objAlertsPage.checkingClosedAlertAfterClickingCancelToAlertWithChoise();
        Assert.assertTrue("Окно не закрылось", expectedResult);
    }

    @Test
    @Description("Прямой сценарий проверки текста после закрытия алерта с нажатием ОК ")
    public void shouldCheckingTextAlertClickingOk() {
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickButtonAlertWithChoise();
        objAlertsPage.clickingOkToAlertWithChoise();
        boolean expectedResult = objAlertsPage.chekingTextAfterClickingOk();
        Assert.assertTrue("Текст не совпал", expectedResult);
    }

    @Test
    @Description("Прямой сценарий проверки текста после закрытия алерта с нажатием Отмена ")
    public void shouldCheckingTextAlertClickingCancel() {
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickButtonAlertWithChoise();
        objAlertsPage.clickingCancelToAlertWithChoise();
        boolean expectedResult = objAlertsPage.chekingTextAfterClickingCancel();
        Assert.assertTrue("Текст не совпал", expectedResult);
    }

    @Test
    @Description("Прямой сценарий проверки отображения алерта после нажатия кнопки click me")
    public void shouldAlertWithChoiseIsVisible() {
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickButtonAlertWithChoise();
        boolean expectedResult = objAlertsPage.alertWithChoiseIsVisble();
        Assert.assertTrue("Алерт не отобразился", expectedResult);
    }

    @Test
    @Description("Сценарий проверки текста на соответствие тексту в  алерте")
    public void shouldTextEqualAlertWithChoise() {
        String expectedText = "Do you confirm action?";
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickButtonAlertWithChoise();
        String expectedResult = objAlertsPage.checkintTextAlertWithChoise(expectedText);
        Assert.assertEquals("Текст не совпадает", expectedText, expectedResult);
    }

    @Test
    @Description("Прямой сценарий проверки ввода текста в алерт с полем для ввода")
    public void shouldEnterTextInAlert() {
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickAlertWithFieldEnter();
        objAlertsPage.sendTextInALertWithFieldEnter("Тестовый текст");
        boolean expectedResult = objAlertsPage.alertPromtIsNotPresent();
        Assert.assertTrue("Алерт отображается,тест провален", expectedResult);
    }

    @Test
    @Description("Прямой сценарий проверки  текста в алерт с полем для ввода ,текст должен совпадать")
    public void getTextInAlertWithClickingOk() {
        String actualText = "Please enter your name";
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickAlertWithFieldEnter();
        String expectedResult = objAlertsPage.getTextInAlertPromt(actualText);
        Assert.assertEquals("Текст не совпадает ,тест провален", expectedResult, actualText);
    }

    @Test
    @Description("Прямой сценарий проверки ввода текста в алерт с полем для ввода,после нажатия кнопки ок должен отобразится в поле ассерта")
    public void checkedSendTextInAlert() {
        String sendText = "Тестовый текст";
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickAlertWithFieldEnter();
        objAlertsPage.sendTextInALertWithFieldEnter(sendText);
        boolean expectedResult = objAlertsPage.checkingTextInPageAlertAfterClickingOkInAlertPrompt(sendText);
        Assert.assertTrue("Текст не совпадает введенному тексту", expectedResult);
    }

    @Test
    @Description("Негативный сценарий отправки пустого поля ,алерт должен закрыться")
    public void checkedClosedAlertAfterEmptyInput() {
        String sendText = "";
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickAlertWithFieldEnter();
        objAlertsPage.sendTextInALertWithFieldEnter(sendText);
        boolean expectedResult = objAlertsPage.alertPromtIsNotPresent();
        Assert.assertTrue("Алерт отобразился", expectedResult);
    }

    @Test
    @Description("Негативный сценарий отправки пустого поля , в поле алерта не должно отображаться текста после нажатия ОК")
    public void checkedEmptyInputInAlert() {
        String sendText = "";
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickAlertWithFieldEnter();
        objAlertsPage.sendTextInALertWithFieldEnter(sendText);
        boolean expectedResult = objAlertsPage.presentIsEmptyText();
        Assert.assertTrue("Отобразился текст", expectedResult);
    }

    @Test
    @Description("Негативный сценарий закрытия алерта после ввода текста, в поле алерта не должно отображаться текста после нажатия Отмена")
    public void CancelAlertAfterTextInput() {
        String sendText = "Тестовый текст";
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickAlertWithFieldEnter();
        objAlertsPage.closedAlertPrompt(sendText);
        boolean expectedResult = objAlertsPage.presentIsEmptyText();
        Assert.assertTrue("Отобразился текст", expectedResult);
    }

    @Test
    @Description("Негативный сценарий закрытия алерта после ввода текста, в поле алерта не должно отображаться текста после нажатия Отмена")
    public void isNotPresentAlertAfterClickCancel() {
        String sendText = "Тестовый текст";
        AlertsPage objAlertsPage = new AlertsPage(driver);
        objAlertsPage.clickButtonAlertOnLeftWindow();
        objAlertsPage.clickAlertWithFieldEnter();
        objAlertsPage.closedAlertPrompt(sendText);
        boolean expectedResult = objAlertsPage.alertPromtIsNotPresent();
        Assert.assertTrue("Отобразился алерт", expectedResult);
    }

    @After
    public void quit() {
        if (driver != null) {
            driver.quit(); // Закрываем драйвер после завершения теста
        }
    }
}



