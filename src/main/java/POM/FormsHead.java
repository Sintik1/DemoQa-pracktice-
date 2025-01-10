package POM;

import io.qameta.allure.Step;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.util.Random;


public class FormsHead {
    private WebDriver driver;

    private By buttonHomePage = By.xpath(".//a[@href='https://demoqa.com']");
    private By buttonForm = By.xpath(".//span[@class='text'and contains(text(),'Practice Form')]");

    private By fieldName = By.xpath(".//input[@id='firstName']");
    private By fieldLastName = By.xpath(".//input[@id='lastName']");

    private By fieldEmail = By.xpath(".//input[@id='userEmail']");
    public static final String [] arrayButtonGender = new String[]{
            ".//label[@class ='custom-control-label'and contains(text(),'Male')]",
            ".//label[@class ='custom-control-label'and contains(text(),'Female')]",
            ".//label[@class ='custom-control-label'and contains(text(),'Other')]"
    };
    private By fieldUserNumber = By.xpath(".//input[@id='userNumber']");

    private By fieldDateBirth = By.xpath(".//input[@id='dateOfBirthInput']");

    private By listMonth = By.xpath(".//select[@class='react-datepicker__month-select']");

    private By listYear = By.xpath(".//select[@class='react-datepicker__year-select']");

    private By fieldSubject = By.xpath(".//div[@id='subjectsContainer']");

    private By buttonSubmit = By.xpath(".//button[@id='submit']");

    private By radioButtonSport = By.xpath(".//label[@class ='custom-control-label'and contains(text(),'Sport')]");
    private By radioButtonReading = By.xpath(".//label[@class ='custom-control-label'and contains(text(),'Reading')]");
    private By radioButtonMusic = By.xpath(".//label[@class ='custom-control-label'and contains(text(),'Music')]");
    public static final String [] arrayButtonHobbies = new String[]{
            ".//label[@class ='custom-control-label'and contains(text(),'Sport')]",
            ".//label[@class ='custom-control-label'and contains(text(),'Reading')]",
            ".//label[@class ='custom-control-label'and contains(text(),'Music')]"
    };
    private By uploadFile = By.xpath(".//label[@class='form-file-label'and contains(text(),'Select picture')]");

    private By fieldCurrentAdress = By.xpath(".//textarea[@placeholder='Current Address']");
    private By dropListState = By.xpath(".//div[text()='Select State']");
    private By dropListCity = By.xpath(".//div[text()='Select City']");
    private By tableForms = By.xpath(".//div[@class='table-responsive']");
    public static final String[] arrayListOfState = new String[]{
            "react-select-3-option-0",
            "react-select-3-option-1",
            "react-select-3-option-2",
            "react-select-3-option-3"
    };
    public static final String[] arrayListOfCity = new String[]{
            "react-select-4-option-0",
            "react-select-4-option-1",
            "react-select-4-option-2"
    };


    public FormsHead(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void clickButtonHomePage(){
        driver.findElement(buttonHomePage).click();
    }

    @Step
    public void clickButtonPracticeForm() {
        driver.findElement(buttonForm).click();
    }

    @Step
    public void clickFieldName() {
        driver.findElement(fieldName).click();
    }

    @Step
    public void sendFieldName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    @Step
    public void clickFieldLastName() {
        driver.findElement(fieldLastName).click();
    }

    @Step
    public void sendFieldLastName(String lastName) {
        driver.findElement(fieldLastName).sendKeys(lastName);
    }

    @Step
    public void clickFieldEmail() {
        driver.findElement(fieldEmail).click();
    }

    @Step
    public void sendFieldEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step
    public void clickButtonGender(String arrayButtonGender) {
        driver.findElement(By.xpath(arrayButtonGender)).click();
    }

    @Step
    public void scrollToPhoneNumber() {
        WebElement element = driver.findElement(fieldUserNumber);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Step
    public void clickFieldUserNumber() {
        driver.findElement(fieldUserNumber).click();
    }

    @Step
    public void sendFieldUserNumber(String phoneNumber) {
        driver.findElement(fieldUserNumber).sendKeys(phoneNumber);
    }

    @Step
    public void clickFieldDateBirth() {
        driver.findElement(fieldDateBirth).click();
    }

    public void clickListMonth() {
        driver.findElement(listMonth).click();

    }

    public void choiseMonth(String monthValue) {
        driver.findElement(listMonth).sendKeys(monthValue);

        //Select dropdown = new Select(dropdownElement);
        //dropdown.selectByValue(monthValue);
    }

    public void choiseYear(String yearsValue) {
        //WebElement dropdownElement =
        driver.findElement(listYear).sendKeys(yearsValue);
        //Select dropdown = new Select(dropdownElement);
        //dropdown.selectByValue(yearsValue);
    }


    public void selectDate(String day) {
        // Открываем календарь
        //  driver.findElement(By.id("datePickerId")).click(); // Замените на правильный локатор для открытия календаря

        // Ожидаем, пока элемент станет видимым
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='"+day+"'][1]")));
        // Кликаем на нужный день
        dayElement.click();
    }
    //Select dayElement = new Select(dayButton);
    //dayElement.selectByValue(day);



    public void scrollPageToSubmit() {
        WebElement element = driver.findElement(buttonSubmit);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickFieldSubject() {
        driver.findElement(fieldSubject).click();
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

    public void clickButtonHobbies(String arrayButtonHobbies) {
        driver.findElement(By.xpath(arrayButtonHobbies)).click();
    }

    public void clickButtonPicture() {
        //driver.findElement(uploadFile).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//label[@class='form-file-label' and contains(text(),'Select picture')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", uploadButton);

        // Загрузка файла
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        String filePath = "C:\\Users\\Привет\\Desktop\\image.jpg"; // Укажите путь к вашему файлу
        fileInput.sendKeys(filePath);
    }


    public void clickFieldCurrentAdress() {
        driver.findElement(fieldCurrentAdress).click();
    }

    public void sendFieldCurrentAdress(String sendComment) {
        driver.findElement(fieldCurrentAdress).sendKeys(sendComment);
    }

    public void clickDropListState() {
        driver.findElement(dropListState).click();
    }

    public void clickDropListStateArray(String arrayListOfState) {
        driver.findElement(By.id(arrayListOfState)).click();
    }

    public void clickDropListCity() {
        driver.findElement(dropListCity).click();
    }

    public void choiseDropListCityArray(String arrayListOfCity) {
        driver.findElement(By.id(arrayListOfCity)).click();
    }

    public void clickButtonSubmit() {
        driver.findElement(buttonSubmit).click();
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
}




