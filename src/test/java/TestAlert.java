import POM.AlertsPage;
import POM.HeadPage;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAlert {
    WebDriver driver;
    public static final String URI = "https://demoqa.com/";
    private static final Logger logger = LoggerFactory.getLogger(TestAlert.class);

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get(URI);
        driver.manage().window().maximize();
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonAllert();
    }

    @Test
    @Description("Сценарий проверки отображения всплывающего окна")
    public void shouldIsVisibleAlert() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Открываю страницу с алертами");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("нажал на алерт");
        objAlertsPage.clickButtonAlert();
        logger.info("работаю с алертом");
        boolean alertIsVisible = objAlertsPage.windowAllertIsVisible();
        Assert.assertTrue("Всплывающее окно не отобразилось", alertIsVisible);
    }

    @Test
    @Description("Прямой сценарий работы простого всплывающего окна, должен отобразится и при нажатии кнопки ОК закрыться")
    public void shouldClickToButtonOkInAlert() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("перешел в алерт");
        objAlertsPage.clickButtonAlert();
        logger.info("нажал кнопку ок ");
        objAlertsPage.clickingOkInSimpleAlert();
        logger.info("проверяю что алерт не отображается");
        boolean alertIsNotVisible = objAlertsPage.isNotPresentAlert();
        Assert.assertTrue("Всплывающее окно отображается", alertIsNotVisible);
        logger.info("Тест завершен");
    }

    @Test
    @Description("Сценарий проверки что всплывающее окно не отобразилось спустя 4 секунды")
    public void checkedAlertDidNotAppearAfterFourSeconds() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickOnButtonWillAppearAfterFiveSeconds();
        logger.info("Проверяю что в течении 4 секунд не появится алерт");
        boolean alertIsNotVisible = objAlertsPage.isNotPresentAlertAfterFourSecond();
        Assert.assertTrue("Всплывающее окно отобразилось", alertIsNotVisible);
    }

    @Test
    @Description("Сценарий проверки что всплывающее окно не отобразилось спустя 4 секунды")
    public void checkedAlertIsVisibleAfterFiveSeconds() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickOnButtonWillAppearAfterFiveSeconds();
        logger.info("Проверяю что в течении 5 секунд появится алерт");
        boolean alertIsNotVisible = objAlertsPage.isPresentAlertAfterFiveSecond();
        Assert.assertTrue("Всплывающее окно  не отобразилось", alertIsNotVisible);
        logger.info("Тест завершен");
    }

    @Test
    @Description("Сценарий проверки что всплывающее окно успешно закрывается при нажатии кнопки ОК")
    public void checkedAlertIsClickingOk() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickOnButtonWillAppearAfterFiveSeconds();
        logger.info("Нажимаю кнопку ОК");
        objAlertsPage.clickingToButtonOkInAlertAfterFiveSecond();
        boolean alertIsNotVisibleAfterClosed = objAlertsPage.alertIsNotVisibleAfterClickingButtonOk();
        Assert.assertTrue("Всплывающее окно отобразилось", alertIsNotVisibleAfterClosed);
        logger.info("Тест завершен");
    }

    @Test
    @Description("Проверка на соответствие текста")
    public void checkedTextIsAlertFiveSecond() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickOnButtonWillAppearAfterFiveSeconds();
        logger.info("Проверяю текст на соответствие");
        boolean expectedResult = objAlertsPage.checkedTextInAlertVisibleAfterFiveSecond();
        Assert.assertTrue("Тест не удался", expectedResult);
    }

    @Test
    @Description("Прямой сценарий обработки алерта с нажатием ОК ")
    public void shouldAlertClickingOk() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickButtonAlertWithChoise();
        objAlertsPage.clickingOkToAlertWithChoise();
        boolean expectedResult = objAlertsPage.checkingClosedAlertAfterClickingOkToAlertWithChoise();
        Assert.assertTrue("Окно не закрылось", expectedResult);
    }

    @Test
    @Description("Прямой сценарий обработки алерта с нажатием ОК ")
    public void shouldAlertClickingCancel() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickButtonAlertWithChoise();
        objAlertsPage.clickingCancelToAlertWithChoise();
        boolean expectedResult = objAlertsPage.checkingClosedAlertAfterClickingCancelToAlertWithChoise();
        Assert.assertTrue("Окно не закрылось", expectedResult);
    }

    @Test
    @Description("Прямой сценарий проверки текста после закрытия алерта с нажатием ОК ")
    public void shouldCheckingTextAlertClickingOk() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickButtonAlertWithChoise();
        objAlertsPage.clickingOkToAlertWithChoise();
        boolean expectedResult = objAlertsPage.chekingTextAfterClickingOk();
        Assert.assertTrue("Текст не совпал", expectedResult);
    }

    @Test
    @Description("Прямой сценарий проверки текста после закрытия алерта с нажатием Отмена ")
    public void shouldCheckingTextAlertClickingCancel() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickButtonAlertWithChoise();
        objAlertsPage.clickingCancelToAlertWithChoise();
        boolean expectedResult = objAlertsPage.chekingTextAfterClickingCancel();
        Assert.assertTrue("Текст не совпал", expectedResult);
    }

    @Test
    @Description("Прямой сценарий проверки отображения алерта после нажатия кнопки click me")
    public void shouldAlertWithChoiseIsVisible() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickButtonAlertWithChoise();
        boolean expectedResult = objAlertsPage.alertWithChoiseIsVisble();
        Assert.assertTrue("Алерт не отобразился", expectedResult);
    }

    @Test
    @Description("Сценарий проверки текста на соответствие тексту в  алерте")
    public void shouldTextEqualAlertWithChoise() {
        String expectedText = "Do you confirm action?";
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickButtonAlertWithChoise();
        logger.info("Проверяю текст");
        String expectedResult = objAlertsPage.checkintTextAlertWithChoise(expectedText);
        Assert.assertEquals("Текст не совпадает", expectedText, expectedResult);
    }

    @Test
    @Description("Прямой сценарий проверки ввода текста в алерт с полем для ввода")
    public void shouldEnterTextInAlert() {
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickAlertWithFieldEnter();
        logger.info("Ввожу текст в алерт и нажимаю ок");
        objAlertsPage.sendTextInALertWithFieldEnter("Тестовый текст");
        boolean expectedResult = objAlertsPage.alertPromtIsNotPresent();
        Assert.assertTrue("Алерт отображается,тест провален", expectedResult);
    }

    @Test
    @Description("Прямой сценарий проверки  текста в алерт с полем для ввода ,текст должен совпадать")
    public void getTextInAlertWithClickingOk() {
        String actualText = "Please enter your name";
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickAlertWithFieldEnter();
        logger.info("Ввожу текст в алерт и нажимаю ок");
        String expectedResult = objAlertsPage.getTextInAlertPromt(actualText);
        Assert.assertEquals("Текст не совпадает ,тест провален", expectedResult, actualText);
    }

    @Test
    @Description("Прямой сценарий проверки ввода текста в алерт с полем для ввода,после нажатия кнопки ок должен отобразится в поле ассерта")
    public void checkedSendTextInAlert() {
        String sendText = "Тестовый текст";
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickAlertWithFieldEnter();
        logger.info("Ввожу текст в алерт и нажимаю ок");
        objAlertsPage.sendTextInALertWithFieldEnter(sendText);
        boolean expectedResult = objAlertsPage.checkingTextInPageAlertAfterClickingOkInAlertPrompt(sendText);
        Assert.assertTrue("Текст не совпадает введенному тексту", expectedResult);
    }

    @Test
    @Description("Негативный сценарий отправки пустого поля ,алерт должен закрыться")
    public void checkedClosedAlertAfterEmptyInput() {
        String sendText = "";
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickAlertWithFieldEnter();
        logger.info("Ввожу текст в алерт и нажимаю ок");
        objAlertsPage.sendTextInALertWithFieldEnter(sendText);
        boolean expectedResult = objAlertsPage.alertPromtIsNotPresent();
        Assert.assertTrue("Алерт отобразился", expectedResult);
    }

    @Test
    @Description("Негативный сценарий отправки пустого поля , в поле алерта не должно отображаться текста после нажатия ОК")
    public void checkedEmptyInputInAlert() {
        String sendText = "";
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickAlertWithFieldEnter();
        logger.info("Ввожу текст в алерт и нажимаю ок");
        objAlertsPage.sendTextInALertWithFieldEnter(sendText);
        boolean expectedResult = objAlertsPage.presentIsEmptyText();
        Assert.assertTrue("Отобразился текст", expectedResult);
    }

    @Test
    @Description("Негативный сценарий закрытия алерта после ввода текста, в поле алерта не должно отображаться текста после нажатия Отмена")
    public void CancelAlertAfterTextInput() {
        String sendText = "Тестовый текст";
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickAlertWithFieldEnter();
        logger.info("Ввожу текст в алерт и нажимаю ок");
        objAlertsPage.closedAlertPrompt(sendText);
        boolean expectedResult = objAlertsPage.presentIsEmptyText();
        Assert.assertTrue("Отобразился текст", expectedResult);
    }

    @Test
    @Description("Негативный сценарий закрытия алерта после ввода текста, в поле алерта не должно отображаться текста после нажатия Отмена")
    public void isNotPresentAlertAfterClickCancel() {
        String sendText = "Тестовый текст";
        logger.info("Начало теста");
        AlertsPage objAlertsPage = new AlertsPage(driver);
        logger.info("Нажал слева кнопку алерт");
        objAlertsPage.clickButtonAlertOnLeftWindow();
        logger.info("Нажал на алерт");
        objAlertsPage.clickAlertWithFieldEnter();
        logger.info("Ввожу текст в алерт и нажимаю ок");
        objAlertsPage.closedAlertPrompt(sendText);
        boolean expectedResult = objAlertsPage.alertPromtIsNotPresent();
        Assert.assertTrue("Отобразился алерт", expectedResult);
    }
   @After
    public void quit(){
        driver.quit();
    }
    }



