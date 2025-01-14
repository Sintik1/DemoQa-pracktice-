import POM.HeadPage;
import POM.WindowPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

@RunWith(Parameterized.class)
public class TestWindows {
    private WebDriver driver;
    private String buttonWindows;
    private String expectedText;

    private String locatorChildWindow;
    private static final String BASE_URL = "https://demoqa.com/";
    public TestWindows(String buttonWindows,String expectedText,String locatorChildWindow) {
        this.buttonWindows = buttonWindows;
        this.expectedText= expectedText;
        this.locatorChildWindow=locatorChildWindow;
    }
    @Parameterized.Parameters
    public static Object[][]getTestData(){
        return new Object[][]{
                //{WindowPage.ButtonOnTheWindowBrowserWindows.BUTTON_NEW_WINDOW.getId(),"This is a sample page",WindowPage.ChildWindow.OPEN_NEW_WINDOW.getXpath()},
                {WindowPage.ButtonOnTheWindowBrowserWindows.BUTTON_NEW_WINDOW_MESSAGE.getId(),"Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.",WindowPage.ChildWindow.OPEN_WINDOW_MESSAGE.getXpath()},
        };
    }

    @Before
    public void setup(){
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
        navigateToWindowPage();
    }

    private void navigateToWindowPage() {
        driver.get(BASE_URL);
        HeadPage headPage = new HeadPage(driver);
        headPage.clickButtonAllert();
    }
    @Test
    @DisplayName("Сценарий проверки отображения нового окна")
    public void newWindowTabIsDisplayed(){
        WindowPage objWindowsPage = new WindowPage(driver);
        objWindowsPage.clickButtonBrowserWindows();
        objWindowsPage.clickButtonWindows(buttonWindows);
        boolean isDisplayedWindow = objWindowsPage.setWindowInList(locatorChildWindow);
        Assert.assertTrue("Окно не отобразилось",isDisplayedWindow);
    }
    @Test
    @DisplayName("Сценарий проверки отображения текста в новом окне")
    public void testTextInNewTable(){
        WindowPage objWindowsPage = new WindowPage(driver);
        objWindowsPage.clickButtonBrowserWindows();
        objWindowsPage.clickButtonWindows(buttonWindows);
        String actualText = objWindowsPage.checkedTextNewWindow(locatorChildWindow);
        Assert.assertEquals("Текст отличается : ",expectedText,actualText);
    }

@After
    public void quit(){
        if(driver!=null){
        driver.quit();
    }
}
}
