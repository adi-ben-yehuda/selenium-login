package example.example.tests;

import example.example.pages.ContactUsPage;
import example.example.pages.HeroloPage;
import example.example.pages.ThankYouPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ContactUsTest extends BaseTest {
    ContactUsPage contactUsPage;

    // Class method

    private void validData(String validEmail, String validTelephone, String validName,
                           String validCompany) {
        contactUsPage.inputName(validName);
        contactUsPage.inputCompany(validCompany);
        contactUsPage.inputEmail(validEmail);
        contactUsPage.inputTelephone(validTelephone);
        contactUsPage.clickOnContactUs();
    }

    // Global methods

    @Test(priority = 1)
    public void emptyErrorTest() throws Exception {
        contactUsPage = new ContactUsPage(driver);
        contactUsPage.clickOnContactUs();
        contactUsPage.emptyError("שם");
        contactUsPage.emptyError("חברה");
        contactUsPage.emptyError("אימייל");
        contactUsPage.emptyError("טלפון");
    }

    @Parameters({"oneNumber", "justStrudel", "strudelWithoutDot",
            "strudelAndDotWithoutText", "strudelAndDotWithOneLetter", "validEmail"})
    @Test(priority = 2)
    public void notValidEmailErrorTest(String oneNumber,
                                       String justStrudel,
                                       String strudelWithoutDot,
                                       String strudelAndDotWithoutText,
                                       String strudelAndDotWithOneLetter,
                                       String validEmail) {
        contactUsPage.inputEmail(oneNumber);
        contactUsPage.notValidEmailError("אימייל");
        contactUsPage.clearEmail();

        contactUsPage.inputEmail(justStrudel);
        contactUsPage.notValidEmailError("אימייל");
        contactUsPage.clearEmail();

        contactUsPage.inputEmail(strudelWithoutDot);
        contactUsPage.notValidEmailError("אימייל");
        contactUsPage.clearEmail();

        contactUsPage.inputEmail(strudelAndDotWithoutText);
        contactUsPage.notValidEmailError("אימייל");
        contactUsPage.clearEmail();

        contactUsPage.inputEmail(strudelAndDotWithOneLetter);
        contactUsPage.notValidEmailError("אימייל");
        contactUsPage.clearEmail();

        contactUsPage.inputEmail(validEmail);
        contactUsPage.errorDoesntExist("אימייל");
        contactUsPage.clearEmail();
    }

    @Parameters({"oneNumber", "tenDigits", "notValidBeginningPhone",
            "notValidBeginningTelephone", "validTelephone", "validPhone"})
    @Test(priority = 3)
    public void notValidTelephoneErrorTest(String oneNumber,
                                           String tenDigits,
                                           String notValidBeginningPhone,
                                           String notValidBeginningTelephone,
                                           String validTelephone,
                                           String validPhone) {
        contactUsPage.inputTelephone(oneNumber);
        contactUsPage.notValidTelephoneError("טלפון");
        contactUsPage.clearTelephone();

        contactUsPage.inputTelephone(tenDigits);
        contactUsPage.notValidTelephoneError("טלפון");
        contactUsPage.clearTelephone();

        contactUsPage.inputTelephone(notValidBeginningPhone);
        contactUsPage.notValidTelephoneError("טלפון");
        contactUsPage.clearTelephone();

        contactUsPage.inputTelephone(notValidBeginningTelephone);
        contactUsPage.notValidTelephoneError("טלפון");
        contactUsPage.clearTelephone();

        contactUsPage.inputTelephone(validTelephone);
        contactUsPage.errorDoesntExist("טלפון");
        contactUsPage.clearTelephone();

        contactUsPage.inputTelephone(validPhone);
        contactUsPage.errorDoesntExist("טלפון");
        contactUsPage.clearTelephone();
    }

    @Parameters({"validEmail", "validTelephone", "validName", "validCompany"})
    @Test(priority = 4)
    public void validDataTest(String validEmail, String validTelephone, String validName,
                              String validCompany) throws Exception {
        ThankYouPage thankYouPage = new ThankYouPage(driver);
        HeroloPage heroloPage = new HeroloPage(driver);

        // Check return button
        validData(validEmail, validTelephone, validName, validCompany);
        thankYouPage.thanksPageExists();
        thankYouPage.clickOnReturnButton();
        contactUsPage.pagePresents();

        // Check go to site button
        validData(validEmail, validTelephone, validName, validCompany);
        thankYouPage.clickOnGoToSiteButton();
        heroloPage.doesHeroloSitePresent();
    }


}
