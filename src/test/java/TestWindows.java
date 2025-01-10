import POM.HeadPage;
import POM.WindowPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class TestWindows {
    private WebDriver driver;
    private String buttonWindows;
    private static final String BASE_URL = "https://demoqa.com/";
    public TestWindows(String buttonWindows) {
        this.buttonWindows = buttonWindows;
    }
    @Parameterized.Parameters
    public static Object[][]getTestData(){
        return new Object[][]{
                {WindowPage.ButtonOnTheWindowBrowserWindows.BUTTON_NEW_WINDOW.getId()}
        };
    }

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
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
        boolean isDisplayedWindow = objWindowsPage.setWindowInList();
        Assert.assertTrue("Окно не отобразилось",isDisplayedWindow);
    }

}
