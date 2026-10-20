package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {

    public static final String URL = "https://automationexercise.com/";

    private final WebDriver driver;
    private final By signupLoginMenuButton = By.linkText("Signup / Login");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.navigate().to(URL);
    }

    public By signupLoginMenuButton() {
        return signupLoginMenuButton;
    }

    public void assertOnUrl() {
        Assert.assertEquals(driver.getCurrentUrl(), URL);
    }
}