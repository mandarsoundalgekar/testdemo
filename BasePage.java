//package com.base;
package macorva.test;

import com.utility.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestContext;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static WebDriver driver;
    public static Properties prop;
    
    public BasePage() {
        try{
            prop = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            prop.load(fis);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver setUp(String browser){
        if(browser.equalsIgnoreCase("Chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\Users\\Mandar.Soundalgekar\\eclipse-workspace\\Survey\\drivers\\chromedriver.exe");
            System.setProperty("webdriver.chrome.silentOutput", "true");
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);
            options.setHeadless(true);
            return new ChromeDriver(options);
        }
        else if(browser.equalsIgnoreCase("FF")) {
            //System.setProperty("webdriver.gecko.driver", "path of the gecko driver")
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--diable-inforbars");
            options.setHeadless(true);
            return new FirefoxDriver(options);
        }
        else if(browser.equalsIgnoreCase("Safari")){
            //System.setProperty("webdriver.safari.driver", "path of the safari driver")
            //SafariOptions options = new SafariOptions();
            return new SafariDriver();
        }
        else {
            //System.setProperty("webdriver.edge.driver", "path of the edge driver")
            return new EdgeDriver();
        }
    }

    public static void initialization() {
        String browser = prop.getProperty("browser");
        String baseUrl = prop.getProperty("baseUrl");
        driver = setUp(browser);
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(CommonUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(CommonUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public static void closing(){
        CommonUtils.quitDriver();
    }
}
