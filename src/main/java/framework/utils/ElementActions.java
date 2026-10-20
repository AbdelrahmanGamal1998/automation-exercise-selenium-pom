package framework.utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Small helpers that wait for an element before using it. */
public final class ElementActions {

    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    private ElementActions() {
    }

    public static void click(WebDriver driver, By locator) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}