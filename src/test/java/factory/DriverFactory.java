package factory;

import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DriverFactory
{
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final List<WebDriver> allDrivers = new CopyOnWriteArrayList<>();

    private DriverFactory()
    {
        // prevent external instantiation
    }

    public static WebDriver getDriver()
    {
        return tlDriver.get();
    }

    public static void setDriver(WebDriver driver)
    {
        tlDriver.set(driver);
        allDrivers.add(driver);
    }

    // Cleanup current thread's driver
    public static void cleanupDriver()
    {
        WebDriver driver = tlDriver.get();
        if (driver != null)
        {
            try
            {
                driver.quit();
            } catch (Exception ignored)
            {
            }
            tlDriver.remove();
        }
    }

    // Cleanup all drivers (for shutdownHook)
    public static void quitAllDrivers()
    {
        for (WebDriver driver : allDrivers)
        {
            if (driver != null)
            {
                try
                {
                    driver.quit();
                } catch (Exception ignored)
                {
                }
            }
        }
        allDrivers.clear();
    }
}