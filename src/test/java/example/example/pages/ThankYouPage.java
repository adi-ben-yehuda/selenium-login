package example.example.pages;

import example.example.factory.PageInstancesFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ThankYouPage extends BasePage{
    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='thankYou__content-avz2fr-2 hCWrSe']")
    private WebElement thanksPage;

    @FindBy(xpath = "//button[@class='thankYou__button-avz2fr-9 fIeAdZ']")
    private WebElement goToSiteButton;

    @FindBy(xpath = "//a[@class='thankYou__backLink-avz2fr-10 bBzcJF']")
    private WebElement returnButton;

    // Constructor
    public ThankYouPage(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        PageInstancesFactory.initElements(driver, this);
    }

    // Global methods

    public void thanksPageExists() {
        waiter.until(ExpectedConditions.visibilityOf(thanksPage));
        Assert.assertTrue(thanksPage.isDisplayed(), "Thank you page presents.");
    }

    public void clickOnReturnButton() {
        waiter.until(ExpectedConditions.elementToBeClickable(returnButton));
        returnButton.click();
    }

    public void clickOnGoToSiteButton() {
        waiter.until(ExpectedConditions.elementToBeClickable(goToSiteButton));
        goToSiteButton.click();
    }

}
