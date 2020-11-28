package example.example.pages;

import example.example.factory.PageInstancesFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ContactUsPage extends BasePage {

    private final WebDriver driver;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@id='name']//following-sibling::span")
    private WebElement errorTextName;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(xpath = "//input[@id='company']//following-sibling::span")
    private WebElement errorTextCompany;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='email']//following-sibling::span")
    private WebElement errorTextEmail;

    @FindBy(id = "telephone")
    private WebElement telephoneInput;

    @FindBy(xpath = "//input[@id='telephone']//following-sibling::span")
    private WebElement errorTextTelephone;

    @FindBy(xpath = "//a[@type = 'button']")
    private WebElement contactUsButton;

    // Constructor
    public ContactUsPage(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        PageInstancesFactory.initElements(driver, this);
    }


    // ClassMethod

    private WebElement getErrorTextElement(String fieldName) {
        switch (fieldName) {
            case "שם" :
                return errorTextName;
            case "חברה" :
                return errorTextCompany;
            case "אימייל" :
                return errorTextEmail;
            case "טלפון" :
                return errorTextTelephone;
            default:
                return null;
        }
    }

    // Global methods

    public void inputName(String name) {
        nameInput.sendKeys(name);
    }

    public void inputCompany(String company) {
        companyInput.sendKeys(company);
    }

    public void inputEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void inputTelephone(String telephone) {
        telephoneInput.sendKeys(telephone);
    }

    public void clickOnContactUs() {
        contactUsButton.click();
    }

    public void clearEmail() {
        emailInput.sendKeys(Keys.CONTROL + "A");
        emailInput.sendKeys(Keys.BACK_SPACE);
    }

    public void clearTelephone() {
        telephoneInput.sendKeys(Keys.CONTROL + "A");
        telephoneInput.sendKeys(Keys.BACK_SPACE);
    }

    public void emptyError(String fieldName) {
        waiter.until(ExpectedConditions.visibilityOf(getErrorTextElement(fieldName)));
        Assert.assertTrue(getErrorTextElement(fieldName).isDisplayed(),
                "Error message exists in the page");
        Assert.assertTrue(getErrorTextElement(fieldName).getText().equals("שדה " +
                fieldName + " הוא שדה חובה"), "Error message text is correct.");
    }

    public void notValidEmailError(String fieldName) {
        waiter.until(ExpectedConditions.visibilityOf(getErrorTextElement(fieldName)));
        Assert.assertTrue(getErrorTextElement(fieldName).isDisplayed(),
                "Error message exists in the page");
        Assert.assertTrue(getErrorTextElement(fieldName).getText().equals("כתובת " +
                fieldName + " לא חוקית"), "Error message text is correct.");
    }

    public void errorDoesntExist(String fieldName) {
        waiter.until(ExpectedConditions.invisibilityOf(getErrorTextElement(fieldName)));
        Assert.assertTrue(!getErrorTextElement(fieldName).isDisplayed(),
                "Error message doesn't exist in the page");
    }

    public void notValidTelephoneError(String fieldName) {
        waiter.until(ExpectedConditions.visibilityOf(getErrorTextElement(fieldName)));
        Assert.assertTrue(getErrorTextElement(fieldName).isDisplayed(),
                "Error message exists in the page");
        Assert.assertTrue(getErrorTextElement(fieldName).getText().equals("מספר " +
                fieldName + " לא חוקי"), "Error message text is correct.");
    }

    public void pagePresents() {
        waiter.until(ExpectedConditions.visibilityOf(nameInput));
        Assert.assertTrue(nameInput.isDisplayed(), "Contact us page presents.");
    }

}
