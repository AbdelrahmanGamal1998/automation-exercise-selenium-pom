package framework.driver;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/** Creates the browser from the settings in configurations.properties or from -D options. */
public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver initDriver(String browserType, int implicitWaitSeconds, boolean maximize, boolean headless) {
        WebDriver driver;
        switch (browserType.toLowerCase()) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*", "--window-size=1920,1080");
                if (headless) {
                    options.addArguments("--headless=new");
                }
                driver = new ChromeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("-headless");
                }
                driver = new FirefoxDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                }
                driver = new EdgeDriver(options);
            }
            default -> throw new IllegalArgumentException(
                    "Unsupported browserType '" + browserType + "'. Use chrome, firefox or edge.");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitSeconds));
        if (maximize && !headless) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    /** Uses browserType, waits, maximizeWindow and headlessExecution from the system properties. */
    public static WebDriver initDriver() {
        return initDriver(
                System.getProperty("browserType"),
                Integer.parseInt(System.getProperty("waits")),
                Boolean.parseBoolean(System.getProperty("maximizeWindow")),
                Boolean.parseBoolean(System.getProperty("headlessExecution")));
    }
}