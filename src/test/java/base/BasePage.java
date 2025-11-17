package base;

import factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Set;

public class BasePage
{
    // region Constructor
    public BasePage()
    {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }
    // endregion

    public static void clickOnElement(By locator)
    {
        WebElement element = waitForElement1(locator);
        element.click();
    }

    public static void clickOnElementNS(String value)
    {
        WebElement element = waitForElement1(By.xpath("//*[normalize-space()='" + value + "']"));
        element.click();
    }

    public static void clickOnElement1(WebElement ele)
    {
        WebElement element = waitForElement(ele);
        element.click();
    }

    public static WebElement waitForElement(WebElement element)
    {
        Wait<WebDriver> fluentWait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return fluentWait.until(d ->
        {
            WebElement el = element;
            return (el.isDisplayed() || el.isEnabled()) ? el : null;
        });
    }

    public static WebElement waitForElement1(By locator)
    {
        Wait<WebDriver> fluentWait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return fluentWait.until(d ->
        {
            WebElement el = DriverFactory.getDriver().findElement(locator);
            return (el.isDisplayed() || el.isEnabled()) ? el : null;
        });
    }

    public static void clearAndProvide(By locator, String value)
    {
        WebElement element = waitForElement1(locator);
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    public static void clearAndProvide1(WebElement locator, String value)
    {
        WebElement element = waitForElement(locator);
        element.click();
        Actions actions = new Actions(DriverFactory.getDriver());
        waitTS(1);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
        waitTS(1);
        element.sendKeys(value);
    }

    public static void waitTS(int seconds)
    {
        try
        {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void pressEnter()
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void scrollIntoView(WebDriver driver, WebElement element)
    {
        executeScript(DriverFactory.getDriver(), "arguments[0].scrollIntoView(true);", element);
    }

    public static void executeScript(WebDriver driver, String script, Object... args)
    {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getDriver();
        jsExecutor.executeScript(script, args);
    }

    public void hoverOverElement(WebElement locator)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(locator).perform();
    }

    public static void switchTab()
    {
        String originalWindow = DriverFactory.getDriver().getWindowHandle();
        // Get all window handles
        Set<String> allWindows = DriverFactory.getDriver().getWindowHandles();
        // Iterate through the window handles
        for (String windowHandle : allWindows)
        {
            if (!windowHandle.equals(originalWindow))
            {
                // Switch to the new window
                DriverFactory.getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    public static void navigateBack(WebDriver driver)
    {
        driver.navigate().back();
    }

    public boolean isMsgDisplay(WebElement ele, String expectedString)
    {
        String actualResult=ele.getText();
        String expectedResult=expectedString;
        if(actualResult.equalsIgnoreCase(expectedResult))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void navigateForward(WebDriver driver)
    {
        driver.navigate().forward();
    }

}