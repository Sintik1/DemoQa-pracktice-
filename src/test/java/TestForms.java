import POM.FormsHead;
import POM.HeadPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class TestForms {
    WebDriver driver;
    public static final String URI = "https://demoqa.com/";
    static int length = 9;
    private final String name;
    private final String lastName;
    private final String email;
    private final String gender;
    private final String phoneNumber;
    private final String monthValue;
    private final String yearsValue;
    private final String day;
    private final String subject;
    private final String hobbies;
    private final String sendComment;
    private final String stateLocator;
    private final String cityLocator;

    public TestForms(String name, String lastName, String email, String gender, String phoneNumber, String monthValue, String yearsValue, String day, String subject, String hobbies, String sendComment, String stateLocator, String cityLocator) {

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.monthValue = monthValue;
        this.yearsValue = yearsValue;
        this.day = day;
        this.subject=subject;
        this.hobbies=hobbies;
        this.sendComment=sendComment;
        this.stateLocator=stateLocator;
        this.cityLocator=cityLocator;
    }

    @Parameterized.Parameters
    public static Object[][]getTestData(){
        return new Object[][]{
                {"Тест","Тест", RandomStringUtils.randomAlphabetic(3,6)+"@mail.com",FormsHead.arrayButtonGender[0],"9"+RandomStringUtils.random(length, "0123456789"),RandomStringUtils.randomAlphanumeric(1 ,12),"1999","24","English",FormsHead.arrayButtonHobbies[0],"Комментарий заполнения",FormsHead.arrayListOfState[0],FormsHead.arrayListOfCity[1]},
                {"Тесто","Тесто", RandomStringUtils.randomAlphabetic(3,6)+"@mail.com",FormsHead.arrayButtonGender[1],"9"+RandomStringUtils.random(length, "0123456789"),RandomStringUtils.randomAlphanumeric(1 ,12),"1998","10","History",FormsHead.arrayButtonHobbies[1],"Коммент тестовый",FormsHead.arrayListOfState[1],FormsHead.arrayListOfCity[0]},
                {"Тестовый","Тестовый", RandomStringUtils.randomAlphabetic(3,6)+"@mail.com",FormsHead.arrayButtonGender[2],"9"+RandomStringUtils.random(length, "0123456789"),RandomStringUtils.randomAlphanumeric(1 ,12),"2001","1","Chemistry",FormsHead.arrayButtonHobbies[2],"Комментарий тестовый",FormsHead.arrayListOfState[2],FormsHead.arrayListOfCity[1]}
        };
    }

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.get(URI);
        driver.manage().window().maximize();
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonForms();
    }

    @Test
    @Description("Сценарий перехода на главную страницу по кнопке в хедере, при нажатии должен произойти переход на главную страницу")
    public void transitionHomePage() {
        FormsHead objFormsHead = new FormsHead(driver);
        objFormsHead.clickButtonPracticeForm();
        objFormsHead.clickButtonHomePage();
        HeadPage objHeadPage = new HeadPage(driver);
        boolean isDisplayedHeadPage = objHeadPage.headPageIsDisplayed();
        Assert.assertTrue("Страница не отобразилась",isDisplayedHeadPage);
    }

    @Test
    @Description("Негативный сценарий отправки и заполнения формы регистрации с не заполнеными данными,после нажатия кнопки отправки модальное окно не должно отображаться")
    public void submittingAnEmptyForm(){
        FormsHead objFormsHead = new FormsHead(driver);
        objFormsHead.clickButtonPracticeForm();
        objFormsHead.scrollPageToSubmit();
        objFormsHead.clickButtonSubmit();
        boolean isNotDisplayedModalWindow =objFormsHead.tableFormIsDisplayed();
        Assert.assertFalse("Модальное окно отобразилось", isNotDisplayedModalWindow);
    }

    @Test
    @Description("Прямой сценарий отправки и заполнения формы регистрации,должно успешно отобразится модальное окно с введенными данными")
    public void testFormSubmissionSuccessMessage() {
        FormsHead objFormsHead = new FormsHead(driver);
        objFormsHead.clickButtonPracticeForm();
        objFormsHead.clickFieldName();
        objFormsHead.sendFieldName(name);
        objFormsHead.clickFieldLastName();
        objFormsHead.sendFieldLastName(lastName);
        objFormsHead.clickFieldEmail();
        objFormsHead.sendFieldEmail(email);
        objFormsHead.clickButtonGender(gender);
        objFormsHead.scrollToPhoneNumber();
        objFormsHead.clickFieldUserNumber();
        objFormsHead.sendFieldUserNumber(phoneNumber);
        objFormsHead.scrollPageToSubmit();
        objFormsHead.clickFieldDateBirth();
        objFormsHead.clickListMonth();
        objFormsHead.choiseMonth(monthValue);
        objFormsHead.choiseYear(yearsValue);
        objFormsHead.selectDate(day);
        objFormsHead.clickFieldSubject();
        objFormsHead.sendFieldSubject(subject);
        objFormsHead.clickButtonHobbies(hobbies);
        objFormsHead.clickButtonPicture();
        objFormsHead.clickFieldCurrentAdress();
        objFormsHead.sendFieldCurrentAdress(sendComment);
        objFormsHead.clickDropListState();
        objFormsHead.clickDropListStateArray(stateLocator);
        objFormsHead.clickDropListCity();
        objFormsHead.choiseDropListCityArray(cityLocator);
        objFormsHead.clickButtonSubmit();
        boolean isDisplayedModalWindow =objFormsHead.tableFormIsDisplayed();
        Assert.assertTrue("Модальное окно не отобразилось",isDisplayedModalWindow);
    }
    @After
    public void quit(){
        driver.quit();
    }
}