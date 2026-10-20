package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SignupLoginPage {

    private final WebDriver driver;
    private final By signupTitle = By.cssSelector(".signup-form h2");
    private final By nameField = By.cssSelector(".signup-form input[name='name']");
    private final By emailField = By.cssSelector(".signup-form input[name='email']");
    private final By signupButton = By.cssSelector(".signup-form button[type='submit']");

    public SignupLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void userSignUp(String name, String email) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(signupButton).click();
    }

    public void assertOnSignupPage(String expectedTitle) {
        Assert.assertEquals(driver.findElement(signupTitle).getText(), expectedTitle);
    }
}