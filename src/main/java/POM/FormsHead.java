package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormsHead {
    private WebDriver driver;

    private By buttonHomePage = By.xpath(".//a[@href='https://demoqa.com']");
    private By buttonForm = By.xpath(".//span[@class='text'and contains(text(),'Practice Form')]");

    private By fieldName = By.xpath(".//input[@id='firstName']");
    private By fieldLastName = By.xpath(".//input[@id='lastName']");

    private By fieldEmail = By.xpath(".//input[@id='userEmail']");
   /* public static final String [] arrayButtonGender = new String[]{
            ".//label[@class ='custom-control-label'and contains(text(),'Male')]",
            ".//label[@class ='custom-control-label'and contains(text(),'Female')]",
            ".//label[@class ='custom-control-label'and contains(text(),'Other')]"
    };*/
    public enum ButtonGender{
        MALE(".//label[@class ='custom-control-label'and contains(text(),'Male')]"),
        FEMALE(".//label[@class ='custom-control-label'and contains(text(),'Female')]"),
       OTHER(".//label[@class ='custom-control-label'and contains(text(),'Other')]");

        private String xpath;

       ButtonGender(String xpath) {
           this.xpath = xpath;
       }

       public String getXpath() {
           return xpath;
       }
   }
    private By fieldUserNumber = By.xpath(".//input[@id='userNumber']");

    private By fieldDateBirth = By.xpath(".//input[@id='dateOfBirthInput']");

    private By listMonth = By.xpath(".//select[@class='react-datepicker__month-select']");

    private By listYear = By.xpath(".//select[@class='react-datepicker__year-select']");

    private By fieldSubject = By.xpath(".//div[@id='subjectsContainer']");

    private By buttonSubmit = By.xpath(".//button[@id='submit']");

    /*public static final String [] arrayButtonHobbies = new String[]{
            ".//label[@class ='custom-control-label'and contains(text(),'Sport')]",
            ".//label[@class ='custom-control-label'and contains(text(),'Reading')]",
            ".//label[@class ='custom-control-label'and contains(text(),'Music')]"
    };*/
    public enum ButtonHobbies{
        SPORT(".//label[@class ='custom-control-label'and contains(text(),'Sport')]"),
        READING(".//label[@class ='custom-control-label'and contains(text(),'Reading')]"),
        MUSIC(".//label[@class ='custom-control-label'and contains(text(),'Music')]");

        private String xpath;

        ButtonHobbies(String xpath){
            this.xpath=xpath;
        }
        public String getXpath(){
            return xpath;
        }
    }
    private By uploadFile = By.xpath(".//label[@class='form-file-label'and contains(text(),'Select picture')]");

    private By fieldCurrentAdress = By.xpath(".//textarea[@placeholder='Current Address']");
    private By dropListState = By.xpath(".//div[text()='Select State']");
    private By dropListCity = By.xpath(".//div[text()='Select City']");
    private By tableForms = By.xpath(".//div[@class='table-responsive']");
    /*public static final String[] arrayListOfState = new String[]{
            "react-select-3-option-0",
            "react-select-3-option-1",
            "react-select-3-option-2",
            "react-select-3-option-3"
    };*/
    public enum ListState{
        STATE_ONE("react-select-3-option-0"),
        STATE_TWO("react-select-3-option-1"),
        STATE_THREE( "react-select-3-option-2"),
        STATE_FOUR("react-select-3-option-3");

        private String id;

        ListState(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
   /* public static final String[] arrayListOfCity = new String[]{
            "react-select-4-option-0",
            "react-select-4-option-1",
            "react-select-4-option-2"
    };*/
    public enum ListCity{
        CITY_ONE("react-select-4-option-0"),
       CITY_TWO("react-select-4-option-1"),
       CITY_THREE("react-select-4-option-2");

        private String id;

        ListCity(String id){
            this.id=id;
        }
        public String getId(){
            return id;
        }
   }



    public FormsHead(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void clickButtonHomePage(){
        click(buttonHomePage);
    }

    @Step
    public void clickButtonPracticeForm() {
        click(buttonForm);
    }

    @Step
    public void clickFieldName() {
        click(fieldName);
    }

    @Step
    public void sendFieldName(String name) {
        sendKeys(fieldName,name);
    }
    @Step
    public void clickFieldLastName() {
        click(fieldLastName);
    }

    @Step
    public void sendFieldLastName(String lastName) {
        sendKeys(fieldLastName,lastName);
    }

    @Step
    public void clickFieldEmail() {
        click(fieldEmail);
    }

    @Step
    public void sendFieldEmail(String email) {
        sendKeys(fieldEmail,email);
    }

    @Step
    public void clickButtonGender(String gender) {
        click(By.xpath(gender));
    }

    @Step
    public void scrollToPhoneNumber() {
        WebElement element = driver.findElement(fieldUserNumber);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Step
    public void clickFieldUserNumber() {
        click(fieldUserNumber);
    }

    @Step
    public void sendFieldUserNumber(String phoneNumber) {
        sendKeys(fieldUserNumber,phoneNumber);
    }

    @Step
    public void clickFieldDateBirth() {
        click(fieldDateBirth);
    }

    public void clickListMonth() {
        click(listMonth);

    }

    public void choiseMonth(String monthValue) {
       sendKeys(listMonth,monthValue);
    }

    public void choiseYear(String yearsValue) {
       sendKeys(listYear,yearsValue);
    }


    public void selectDate(String day) {
        // Ожидаем, пока элемент станет видимым
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='"+day+"'][1]")));
        // Кликаем на нужный день
        dayElement.click();
    }

    public void scrollPageToSubmit() {
        WebElement element = driver.findElement(buttonSubmit);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickFieldSubject() {
        click(fieldSubject);
    }

    public void sendFieldSubject(String subject) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            // Ожидание видимости элемента ввода
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subjectsInput")));

            // Прокрутка к элементу, если он не виден
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            // Ожидание доступности элемента для клика
            wait.until(ExpectedConditions.elementToBeClickable(element));

            // Проверка, что элемент доступен и видим
            if (element.isDisplayed() && element.isEnabled()) {
                element.clear(); // Очистка поля перед вводом
                element.sendKeys(subject); // Ввод текста в поле
                element.sendKeys(Keys.ENTER); // Нажатие Enter
            } else {
                System.out.println("Элемент не доступен для взаимодействия.");
            }
        } catch (ElementNotInteractableException e) {
            System.out.println("Элемент не доступен для взаимодействия: " + e.getMessage());
        } catch (InvalidElementStateException e) {
            System.out.println("Недопустимое состояние элемента: " + e.getMessage());
        } catch (TimeoutException e) {
            System.out.println("Время ожидания истекло: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    public void clickButtonHobbies(String hobbies) {
        click(By.xpath(hobbies));
    }

    public void clickButtonPicture() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//label[@class='form-file-label' and contains(text(),'Select picture')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", uploadButton);

        // Загрузка файла
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        String filePath = "C:\\Users\\Привет\\Desktop\\image.jpg"; // Укажите путь к вашему файлу
        fileInput.sendKeys(filePath);
    }


    public void clickFieldCurrentAdress() {
        click(fieldCurrentAdress);
    }

    public void sendFieldCurrentAdress(String sendComment) {
        sendKeys(fieldCurrentAdress,sendComment);
    }

    public void clickDropListState() {
        click(dropListState);
    }

    public void clickDropListStateArray(String listState) {
        click(By.id(listState));
    }

    public void clickDropListCity() {
        click(dropListCity);
    }

    public void choiseDropListCityArray(String listCity) {
        click(By.id(listCity));
    }

    public void clickButtonSubmit() {
        click(buttonSubmit);
    }

    public boolean tableFormIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(tableForms));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Таблица не отображается в течение времени ожидания.");
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("Элемент таблицы не найден.");
            return false;
        }

    }
    private void click(By locator) {
        driver.findElement(locator).click();
    }
    private void sendKeys(By locator,String keys){
        driver.findElement(locator).sendKeys(keys);
    }
}




