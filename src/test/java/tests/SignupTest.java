package tests;

import framework.driver.DriverFactory;
import framework.pages.AccountInformationPage;
import framework.pages.HomePage;
import framework.pages.SignupLoginPage;
import framework.utils.ElementActions;
import framework.utils.JsonFileManager;
import framework.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignupTest {

    private WebDriver driver;
    private JsonFileManager testData;
    private HomePage homePage;
    private SignupLoginPage signupLoginPage;
    private AccountInformationPage accountInformationPage;

    @BeforeClass
    public void loadTestData() {
        testData = new JsonFileManager("src/test/resources/testData.json");
    }

    @BeforeMethod
    public void openBrowser() {
        PropertiesReader.loadProperties();
        driver = DriverFactory.initDriver();
        homePage = new HomePage(driver);
        signupLoginPage = new SignupLoginPage(driver);
        accountInformationPage = new AccountInformationPage(driver);
        homePage.open();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void newUserCanSignUp() {
        // The site accepts an e-mail address only once, so every run uses a new one.
        String email = "tester" + System.currentTimeMillis() + "@example.com";

        homePage.assertOnUrl();
        ElementActions.click(driver, homePage.signupLoginMenuButton());
        signupLoginPage.assertOnSignupPage(testData.getTestData("signup.expectedTitle"));
        signupLoginPage.userSignUp("Ahmed", email);
        accountInformationPage.assertOnAccountInformation("ENTER ACCOUNT INFORMATION");
        accountInformationPage.userAccountInformation("2", "2", "2001");
        accountInformationPage.userAddressInformation();
        accountInformationPage.assertOnAccountCreatedText("ACCOUNT CREATED!");
        accountInformationPage.assertOnAccountCreatedColor("rgba(0, 128, 0, 1)");
    }
}