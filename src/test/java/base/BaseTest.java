package base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.github.javafaker.Faker;
import factory.DriverFactory;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import org.testng.asserts.SoftAssert;

public class BaseTest
{
    //region Global Variables Initialization
    public Properties p;
    public SoftAssert softAssert;

    // Getter for WebDriver
    public static WebDriver getDriver()
    {
        //return driver.get();
        return DriverFactory.getDriver();
    }
    //endregion

    //region Setup
    @SuppressWarnings("deprecation")
    @BeforeClass(groups = {"regression", "datadriven", "functional"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException
    {
        //region CloseBrowserWhenClickStopDebugging
        Runtime.getRuntime().addShutdownHook(new Thread(DriverFactory::quitAllDrivers));
        //endregion\

        //region SoftAssert Initialization
        softAssert = new SoftAssert();
        //endregion

        //region config.properties file setup
        // Loading config.properties file
        // read- e- input stream
        FileReader file = new FileReader("./src//test//resources//config.properties");
        p = new Properties();
        p.load(file);
        //endregion

        //region If execution on Local
        if (p.getProperty("execution_env").equals("local"))
        {
            ChromeOptions options = new ChromeOptions();

            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);

            options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>()
            {{
                put("credentials_enable_service", false);
                put("profile.password_manager_enabled", false);
            }});

            WebDriver localDriver;

            switch (browser.toLowerCase())
            {
                case "chrome":
//                     driver = new ChromeDriver(options);
                    localDriver = new ChromeDriver(options);
                    break;

                case "edge":
                    localDriver = new EdgeDriver();

                case "firefox":
                    localDriver = new FirefoxDriver();
                    break;

                default:
                    System.out.println("invalid browser name");
                    return; // return- totally exit from the execution
            }
            //driver.set(localDriver); // assign driver to current thread
            DriverFactory.setDriver(localDriver);
        }

        // region Browser Setup
        // driver = new ChromeDriver();
        DriverFactory.getDriver().manage().deleteAllCookies();
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverFactory.getDriver().get(p.getProperty("appurl")); // Reading URL from properties file
        //endregion


        //endregion

    }
    //endregion

    //region TearDown
    @AfterClass(groups = {"regression", "datadriven", "functional"})
    public void teardown()
    {
       /*
        if (getDriver() != null)
        {
            getDriver().quit();
            driver.remove();
        }
       */
        // DriverFactory.cleanupDriver();
        softAssert.assertAll();
    }
    //endregion
}