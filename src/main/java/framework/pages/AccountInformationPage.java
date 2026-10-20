package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/** The "Enter account information" form and the "Account created" confirmation. */
public class AccountInformationPage {

    private final WebDriver driver;
    private final By formTitle = By.cssSelector("h2.title b");
    private final By accountCreatedTitle = By.cssSelector("h2[data-qa='account-created'] b");
    private final By password = By.id("password");
    private final By days = By.name("days");
    private final By months = By.name("months");
    private final By years = By.name("years");
    private final By newsletter = By.id("newsletter");
    private final By specialOffers = By.id("optin");
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.name("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobileNumber = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("button[data-qa='create-account']");

    public AccountInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void userAccountInformation(String day, String month, String year) {
        driver.findElement(password).sendKeys("Body@123");
        new Select(driver.findElement(days)).selectByValue(day);
        new Select(driver.findElement(months)).selectByValue(month);
        new Select(driver.findElement(years)).selectByValue(year);
        driver.findElement(newsletter).click();
        driver.findElement(specialOffers).click();
    }

    public void userAddressInformation() {
        driver.findElement(firstName).sendKeys("Tarek");
        driver.findElement(lastName).sendKeys("Megahd");
        driver.findElement(company).sendKeys("GizaSystems");
        driver.findElement(address1).sendKeys("Dokki");
        driver.findElement(address2).sendKeys("Dokki");
        new Select(driver.findElement(country)).selectByValue("Canada");
        driver.findElement(state).sendKeys("Ontario");
        driver.findElement(city).sendKeys("Toronto");
        driver.findElement(zipcode).sendKeys("123");
        driver.findElement(mobileNumber).sendKeys("01114193988");
        driver.findElement(createAccountButton).click();
    }

    public void assertOnAccountInformation(String expected) {
        Assert.assertEquals(driver.findElement(formTitle).getText(), expected);
    }

    public void assertOnAccountCreatedText(String expected) {
        Assert.assertEquals(driver.findElement(accountCreatedTitle).getText(), expected);
    }

    public void assertOnAccountCreatedColor(String expectedColor) {
        Assert.assertEquals(driver.findElement(accountCreatedTitle).getCssValue("color"), expectedColor);
    }
}