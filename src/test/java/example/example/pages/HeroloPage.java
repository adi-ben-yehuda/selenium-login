package example.example.pages;

import example.example.factory.PageInstancesFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;

public class HeroloPage extends BasePage{
    private final WebDriver driver;

    @FindBy(id = "home-page-header")
    private WebElement heroloSite;

    // Constructor
    public HeroloPage(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        PageInstancesFactory.initElements(driver, this);
    }

    // Global methods

    public void doesHeroloSitePresent() {
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        waiter.until(ExpectedConditions.visibilityOf(heroloSite));
        Assert.assertTrue(driver.getCurrentUrl().equals("https://herolo.co.il/"),
                "Herolo page presents.");    }
}
