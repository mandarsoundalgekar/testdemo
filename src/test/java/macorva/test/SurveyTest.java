package macorva.test;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import static org.testng.Assert.assertEquals;

import java.awt.List;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import javax.annotation.Untainted;
import javax.xml.xpath.XPathExpression;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class SurveyTest extends InstitutionSetupData {

	public static WebDriver driver;
	public static Properties prop;

	WebDriverWait wait;

	// login page
	private By usermail = By.name("user_email");
	private By accept = By.xpath("//a[@id='hs-eu-confirmation-button']");
	private By send = By.xpath("//span[contains(text(),'Send')]");
	private By accountexistmsg = By.xpath("//span[contains(text(),'Cannot find associated account.')]");
	private By createAccount = By.xpath("//a[@href='/newuser/']");
	// Newuser page
	private By primaryemail = By.xpath("//input[@name='primary_account_email']");
	private By submit = By.xpath("//button[@type='submit']");
	private By actualvalidationMessageforDots = By.xpath("//p[@class='login-modal__error']");
	private By actualAlreadyMessages = By.xpath("//p[@class='login-modal__error']");
	private By actualTitles = By.xpath("//h2[text()='Check your email!']");
	private By yopmailLogin = By.xpath("//input[@id='login']");
	private By loginSend = By.xpath("//input[@class='sbut']");
	private By magicLink = By.xpath("//a[@class='magic-link']");
	// setup page
	private By companyName = By.xpath("//input[@name='company_name']");
	private By subdomain = By.xpath("//input[@name='subdomain']");
	private By next = By.xpath("//button");
	private By decline = By.xpath("//span[text()='Decline']");
	private By loginheaders = By.xpath("//h2");
	private By firstName = By.xpath("//input[@name='first_name']");
	private By lastName = By.xpath("//input[@name='last_name']");
	private By companyMessage = By.xpath("(//p[text()='This field is required.'])[1]");
	private By subdomainMessage = By.xpath("(//p[text()='This field is required.'])[2]");
	private By companyRequired = By.xpath("(//p[text()='This field is required.'])[1]");
	private By subdomainRequired = By.xpath("(//p[text()='This field is required.'])[2]");

	String yopmail = "http://www.yopmail.com/";

	@BeforeClass
	/*
	 * public void setUp() { WebDriverManager.chromedriver().setup(); driver = new
	 * ChromeDriver(); }
	 */

	public static void initialization() {
		String browser = prop.getProperty("browser");
		String baseurl = prop.getProperty("url");
		driver = setUp(browser);
		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		// driver.manage().timeouts().pageLoadTimeout(CommonUtils.PAGE_LOAD_TIMEOUT,
		// TimeUnit.SECONDS);
		// driver.manage().timeouts().implicitlyWait(CommonUtils.IMPLICIT_WAIT,
		// TimeUnit.SECONDS);
	}

	public static WebDriver setUp(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Mandar.Soundalgekar\\chromedriver\\chromedriver.exe");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			options.setExperimentalOption("useAutomationExtension", false);
			options.setHeadless(false);
			return new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mandar.Soundalgekar\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--diable-inforbars");
			options.setHeadless(false);
			return new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("Safari")) {
			// System.setProperty("webdriver.safari.driver", "path of the safari driver")
			// SafariOptions options = new SafariOptions();
			return new SafariDriver();
		} else {
			// System.setProperty("webdriver.edge.driver", "path of the edge driver")
			return new EdgeDriver();
		}
	}

	public SurveyTest() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\Mandar.Soundalgekar\\eclipse-workspace\\Survey\\src\\main\\resources\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void beforeClass() {
		System.out.println("BeforeClass");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\Users\\Mandar.Soundalgekar\\chromedriver\\chromedriver.exe");
		 * ChromeOptions options = new ChromeOptions(); options.
		 * addArguments("user-agent=\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36\""
		 * ); driver = new ChromeDriver(options);
		 */

	}

	@AfterClass
	public void afterClass() {
		System.out.println("AfterClass");
		// driver.quit();
	}

	// 1.1 case check email is not already exist

	@Test(enabled = false)
	public void testEmailExist() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		// driver.get("https://app.doselab.com/");
		driver.manage().window().maximize();
		driver.findElement(usermail).sendKeys(prop.getProperty("EMAIL"));
		// switch to window here
		String parentWinHandle = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			// System.out.println(winHandle);
		}
		System.out.println("Parent window handle: " + parentWinHandle);
		driver.findElement(accept).click();
		// end window handle
		driver.findElement(send).click();

		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Cannot find associated account.')]")));
		String actualvalidmessage = driver.findElement(accountexistmsg).getText();
		String validmessage = prop.getProperty("EMAILEXISTMESSAGE");
		Assert.assertEquals(actualvalidmessage, validmessage);
	}

	// test case 1.2
	@Test(enabled = false)
	public void testEmailValidationBlankfield() throws InterruptedException {
		driver.findElement(createAccount).click();
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(primaryemail).sendKeys(selectAll);
		driver.findElement(primaryemail).sendKeys(Keys.DELETE);
		driver.findElement(primaryemail).sendKeys("");
		driver.findElement(submit).click();
		String blankValidmessage1 = prop.getProperty("BLANKVALIDMESSAGES");
		String actualvalidmessage = driver.findElement(primaryemail).getAttribute("validationMessage");
		Assert.assertEquals(actualvalidmessage, blankValidmessage1);
		System.out.println("Account blank Message displayed");
		// driver.findElement(By.xpath("//a[@href='/newuser/']")).click();
	}

	// test case 3
	// @Test(priority=3)
	public void testEmailValidationMissingAtsign() throws InterruptedException {
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(primaryemail).sendKeys(selectAll);
		driver.findElement(primaryemail).sendKeys(Keys.DELETE);
		driver.findElement(primaryemail).sendKeys(prop.getProperty("INVALIDEMAIL"));
		driver.findElement(submit).click();
		// assert for vaidation message for @ is missing in email address
		String validMessageforAt = prop.getProperty("VALIDMESSAGEFORAT");
		String actualvalidmessage = driver.findElement(primaryemail).getAttribute("validationMessage");
		// .getAttribute("validationMessage");
		Assert.assertEquals(actualvalidmessage, validMessageforAt);

	}

	// test case 4
	@Test(enabled = false)
	public void testEmailValidationMissingDotsign() throws InterruptedException {
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(primaryemail).sendKeys(selectAll);
		driver.findElement(primaryemail).sendKeys(Keys.DELETE);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(primaryemail).sendKeys(prop.getProperty("MISSINGDOT"));
		driver.findElement(submit).click();
		String validationMessageforDot = prop.getProperty("VALIDATIONMESSAGEDOT");
		String actualvalidationMessageforDot = driver.findElement(actualvalidationMessageforDots).getText();
		Assert.assertEquals(actualvalidationMessageforDot, validationMessageforDot);
	}

	// test case 5
	// @Test(priority=5)
	public void testEmailExistInNewuserpage() throws InterruptedException {
		// switch to window here
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(primaryemail).sendKeys(selectAll);
		driver.findElement(primaryemail).sendKeys(Keys.DELETE);
		driver.findElement(primaryemail).sendKeys(prop.getProperty("EMAILEXIST"));
		driver.findElement(submit).click();
		String alreadyAccount = prop.getProperty("ALREADYACCOUNT");
		String actualAlreadyMessage = driver.findElement(actualAlreadyMessages).getText();
		Assert.assertEquals(actualAlreadyMessage, alreadyAccount);
	}

	// test case 6
	// @Test(priority=6)
	public void testCompanyurlRequired() throws InterruptedException {
		// clear initial value
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(primaryemail).sendKeys(selectAll);
		driver.findElement(primaryemail).sendKeys(Keys.DELETE);
		// add email in field
		driver.findElement(primaryemail).sendKeys(prop.getProperty("EMAIL"));
		driver.findElement(submit).click();
		String actualTitle = driver.findElement(actualTitles).getText();
		String ExpectedTitle = prop.getProperty("EXPECTEDTITLE");
		Assert.assertEquals(actualTitle, ExpectedTitle);
		// for testing of assert on email page
		driver.get(yopmail);
		driver.findElement(yopmailLogin).sendKeys(prop.getProperty("YOPID"));
		driver.findElement(loginSend).click();
		// Find frame
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ifmail");
		driver.findElement(magicLink).click();
		// int size = driver.findElements(By.tagName("iframe")).size();
		String parentWinHandle = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			// System.out.println(winHandle);
		}
		System.out.println("Parent window handle: " + parentWinHandle);
		Thread.sleep(500);
		// comment accept button as not present
		// driver.findElement(By.xpath("//a[@id='hs-eu-confirmation-button']")).click();
		driver.findElement(companyName).sendKeys("");
		driver.findElement(subdomain).sendKeys("");
		driver.findElement(firstName).sendKeys(prop.getProperty("FIRSTNAME"));
		driver.findElement(lastName).sendKeys(prop.getProperty("LASTNAME"));
		driver.findElement(next).click();
		driver.findElement(companyMessage).getText();
		driver.findElement(subdomainMessage).getText();
		// assert for company name
		String ActualCompany = driver.findElement(companyRequired).getText();
		String ExpectedCompany = prop.getProperty("EXPECTEDCOMPANYS");
		Assert.assertEquals(ActualCompany, ExpectedCompany);
		System.out.println("Assert passed");
		// assert for url
		String Actualurl = driver.findElement(subdomainRequired).getText();
		String Expectedurl = "This field is required.";
		Assert.assertEquals(Actualurl, Expectedurl);
		System.out.println("Assert passed");
	}

	// test case 7
	// @Test(priority=7)
	public void testDeclineTerms() throws InterruptedException {
		// comment for suite
		driver.findElement(By.xpath("//input[@name='company_name']")).sendKeys(prop.getProperty("INSTITUTIONNAME"));
		driver.findElement(By.xpath("//input[@name='subdomain']")).sendKeys(prop.getProperty("SUBDOMAIN"));
		// comment for suite
		driver.findElement(next).click();
		driver.findElement(decline).click();
		Thread.sleep(500);
		String header = prop.getProperty("HEADERS");
		String loginheader = driver.findElement(loginheaders).getText();
		Assert.assertEquals(loginheader, header);
	}

	// test case 8
	@Test(priority = 1)
	public void testCreateInstitution() throws InterruptedException {

		// commented for run in suite instead of individual function
		driver.get(prop.getProperty("url"));
		driver.findElement(createAccount).click();
		driver.findElement(primaryemail).sendKeys(this.email);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// driver.quit();
		Thread.sleep(5000);
		String ActualTitle = driver.findElement(By.xpath("//h2[text()='Check your email!']")).getText();
		String ExpectedTitle = "Check your email!";
		Assert.assertEquals(ActualTitle, ExpectedTitle);
		System.out.println("Assert passed");
		// for testing of assert on email page

		driver.get("http://www.yopmail.com/");
		driver.findElement(By.xpath("//input[@id='login']")).clear();
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys(this.yopid);
		driver.findElement(By.xpath("//input[@class='sbut']")).click();
		// Find frame
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ifmail");
		driver.findElement(By.xpath("//a[@class='magic-link']")).click();
		int size = driver.findElements(By.tagName("iframe")).size();

		// driver.findElement(By.linkText("//a[@href='/a/']")).click();
		driver.switchTo().defaultContent();
// switch to window here
		String parentWinHandle = driver.getWindowHandle();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			// System.out.println(winHandle);
		}
		System.out.println("Parent window handle: " + parentWinHandle);
		Thread.sleep(500);
		// driver.findElement(By.xpath("//a[@id='hs-eu-confirmation-button']")).click();

		// above section comment to run in suite

		driver.findElement(By.xpath("//input[@name='company_name']")).sendKeys(this.institutionname);
		driver.findElement(By.xpath("//input[@name='subdomain']")).sendKeys(this.subdomains);
		driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys(this.firstname);
		driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys(this.lastname);
		driver.findElement(By.xpath("//button")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// 1. assert for home
		String firstTitle = "Home";
		String firstActulTitle = driver.findElement(By.xpath("//div[contains(text(),'Home')]")).getText();
		Assert.assertEquals(firstActulTitle, firstTitle);
	}

	// update institution name
	@Test(enabled = false)
	public void testupdateInstitution() throws InterruptedException {
		driver.findElement(By.xpath("//div[contains(text(),'Configure')]")).click();
		driver.findElement(By.xpath("//a[@href='/config/institution/']")).click();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("dailycheck");
		driver.findElement(By.xpath("//input[@name='subdomain']")).sendKeys("dailycheck");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	// test updatw institution with only special characters
	@Test(enabled = false)
	public void testupdateInstitutionwithSpecialChar() throws InterruptedException {

		driver.findElement(By.xpath("//div[contains(text(),'Configure')]")).click();
		driver.findElement(By.xpath("//a[@href='/config/institution/']")).click();

		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("@daily#!IT");
		driver.findElement(By.xpath("//input[@name='subdomain']")).sendKeys("@daily#!IT");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String message = "Please enter a valid subdomain. The subdomain must consist only of alphanumeric characters or hyphens, start and end with an alphanumeric character, and be between 4 and 40 characters.";
		String actualmessage = driver.findElement(By.xpath("//div[@class='form-field__error-message']")).getText();
		Assert.assertEquals(actualmessage, message);
	}

	@Test(enabled = false)
	public void testOldurllogin() throws InterruptedException {
		// driver.get("https://april20.doselab.com//login-manual/");
		driver.get("https://dailyseven.doselab.com/login-manual/");

		String message = "Sorry, but the page you were trying to view does not exist.";
		String actualmessage = driver
				.findElement(
						By.xpath("//p[contains(text(),'Sorry, but the page you were trying to view does not exist.')]"))
				.getText();
		Assert.assertEquals(actualmessage, message);

	}

	@Test(enabled = false)
	public void testResetpassword() throws InterruptedException {
		// driver.get("https://april20.doselab.com//login-manual/");
		driver.get("https://dailyjuly.doselab.com/login-manual/");
		driver.findElement(By.xpath("//a[@href='/password_reset/']")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("june11ms1@yopmail.com");
		Thread.sleep(100);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// Assert for message
		String messagetext = "Password reset sent";
		String Actualmessage = driver.findElement(By.xpath("//h2[text()='Password reset sent']")).getText();

		Assert.assertEquals(Actualmessage, messagetext);
		//
		driver.get("http://www.yopmail.com/");
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys("june11ms1");
		driver.findElement(By.xpath("//input[@class='sbut']")).click();
		// Open mail
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ifmail");

		driver.findElement(By.xpath("//a[@class='magic-link']")).click();
		Thread.sleep(10500);
		// 1. assert for home
		// assert snapshot

		String parentWinHandle = driver.getWindowHandle();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			// System.out.println(winHandle);
		}

		driver.findElement(By.xpath("//input[@name='new_password1']")).sendKeys("Test_123");

		driver.findElement(By.xpath("//input[@name='new_password2']")).sendKeys("Test_123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// assert login page
		String loginlabel = "Email or mobile phone number";
		String ActualLoginlabel = driver.findElement(By.xpath("//label[@class='login-modal__label']")).getText();
		assertEquals(ActualLoginlabel, loginlabel);
		System.out.println("password reset successfully");
	}

	@Test(enabled = false)
	public void testResetpasswordwithDifferentconfirmPassword() throws InterruptedException {
		// driver.get("https://april20.doselab.com//login-manual/");
		driver.get("https://dailyjuly.doselab.com/login-manual/");
		driver.findElement(By.xpath("//a[@href='/password_reset/']")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("june11ms1@yopmail.com");
		Thread.sleep(100);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// Assert for message
		String messagetext = "Password reset sent";
		String Actualmessage = driver.findElement(By.xpath("//h2[text()='Password reset sent']")).getText();

		Assert.assertEquals(Actualmessage, messagetext);
		//
		driver.get("http://www.yopmail.com/");
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys("june11ms1");
		driver.findElement(By.xpath("//input[@class='sbut']")).click();
		// Open mail
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ifmail");
		driver.findElement(By.xpath("//a[@class='magic-link']")).click();
		Thread.sleep(10500);
		// 1. assert for home
		// assert snapshot
		String parentWinHandle = driver.getWindowHandle();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			// System.out.println(winHandle);
		}

		driver.findElement(By.xpath("//input[@name='new_password1']")).sendKeys("Test_123");

		driver.findElement(By.xpath("//input[@name='new_password2']")).sendKeys("Test_125");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// assert for confirm password validation message
		String confirmmessage = "The two password fields didn't match.";
		String actualConfirmMessage = driver
				.findElement(By.xpath("//li[contains(text(),\"The two password fields didn't match.\")]")).getText();
		Assert.assertEquals(actualConfirmMessage, confirmmessage);

	}

//password rest unsuccessful

	@Test(enabled = false)
	public void testResetpasswordagainafterReset() throws InterruptedException {

		driver.get("http://www.yopmail.com/");
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys("june11ms1");
		driver.findElement(By.xpath("//input[@class='sbut']")).click();
		// Open mail
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ifmail");
		driver.findElement(By.xpath("//a[@class='magic-link']")).click();
		Thread.sleep(10500);
		// 1. assert for home
		// assert snapshot
		String parentWinHandle = driver.getWindowHandle();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			// System.out.println(winHandle);
		}

		// Assert for message
		String messagetext = "Password reset unsuccessful";
		String Actualmessage = driver.findElement(By.xpath("//h2[text()='Password reset unsuccessful']")).getText();

		Assert.assertEquals(Actualmessage, messagetext);
	}

	@Test(enabled = false)
	public void testwrongpassword() throws InterruptedException {
		// driver.get("https://april20.doselab.com//login-manual/");
		driver.get("https://dailyjuly.doselab.com/login-manual/");

		driver.manage().window().maximize();
		// Input name and password
		driver.findElement(By.xpath("//input[@id='id_email']")).sendKeys("june11ms1@yopmail.com");
		driver.findElement(By.xpath("//input[@id='id_password']")).sendKeys("Test_12");
		driver.findElement(By.xpath("//input[@class='login-modal__submit button--primary']")).click();

		// assert for wrong password message
		String message = "Please enter a correct email and password. Note that both fields may be case-sensitive.";
		String Actualmessages = driver.findElement(By.xpath(
				"//span[contains(text(),'Please enter a correct email and password. Note that both fields may be case-sensitive.')]"))
				.getText();

		Assert.assertEquals(Actualmessages, message);
	}

	@Test(enabled = false)
	public void testwrongemail() throws InterruptedException {

		// driver.get("https://april20.doselab.com//login-manual/");
		driver.get("https://dailyjuly.doselab.com/login-manual/");
		// Login page
		driver.manage().window().maximize();
		// Input name and password
		driver.findElement(By.xpath("//input[@id='id_email']")).sendKeys("june11-1@yopmail.com");
		driver.findElement(By.xpath("//input[@id='id_password']")).sendKeys("Test_123");
		driver.findElement(By.xpath("//input[@class='login-modal__submit button--primary']")).click();

		// assert for wrong password message
		String message = "Please enter a correct email and password. Note that both fields may be case-sensitive.";
		String Actualmessages = driver.findElement(By.xpath(
				"//span[contains(text(),'Please enter a correct email and password. Note that both fields may be case-sensitive.')]"))
				.getText();
		Assert.assertEquals(Actualmessages, message);

	}

	@Test(enabled = false)
	public void testResetpasswordwithDifferntdomain() throws InterruptedException {

		// driver.get("https://april20.doselab.com//login-manual/");
		driver.get("https://Dailyon.doselab.com/login-manual/");
		driver.findElement(By.xpath("//a[@href='/password_reset/']")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("june11ms1@yopmail.com");
		Thread.sleep(100);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// Assert for message
		String messagetext = "Password reset sent";
		String Actualmessage = driver.findElement(By.xpath("//h2[text()='Password reset sent']")).getText();

		Assert.assertEquals(Actualmessage, messagetext);
		//
		driver.get("http://www.yopmail.com/");
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys("june11ms1");
		driver.findElement(By.xpath("//input[@class='sbut']")).click();

	}

	@Test(enabled = false)
	public void testdifferentDomainandCorrectemailPassword() throws InterruptedException {

		// driver.get("https://april20.doselab.com//login-manual/");
		driver.get("https://Dailyon.doselab.com/login-manual/");
		// Login page

		driver.manage().window().maximize();

		// Input name and password

		driver.findElement(By.xpath("//input[@id='id_email']")).sendKeys("june11-1@yopmail.com");
		driver.findElement(By.xpath("//input[@id='id_password']")).sendKeys("Test_123");
		driver.findElement(By.xpath("//input[@class='login-modal__submit button--primary']")).click();

		// assert for wrong password message
		String message = "Please enter a correct email and password. Note that both fields may be case-sensitive.";
		String Actualmessages = driver.findElement(By.xpath(
				"//span[contains(text(),'Please enter a correct email and password. Note that both fields may be case-sensitive.')]"))
				.getText();
		Assert.assertEquals(Actualmessages, message);

	}

	// MyAccount
	// test reset password from myaccount page with wrong current password
	@Test(enabled = false)
	public void testwrongcurrentpasswordinMyAccount() throws InterruptedException {

		// driver.get("https://april20.doselab.com//login-manual/");
		driver.get("https://dailyjuly.doselab.com/login-manual/");
		// Login page

		driver.manage().window().maximize();

		// Input name and password
		driver.findElement(By.xpath("//input[@id='id_email']")).sendKeys("week9-1m@yopmail.com");
		driver.findElement(By.xpath("//input[@id='id_password']")).sendKeys("Test_123");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Login button
		driver.findElement(By.xpath("//input[@class='login-modal__submit button--primary']")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// My account page
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("(//span[@class='nav-bar__auth-item'])[1]"))).build()
				.perform();
		// driver.findElement(By.xpath("(//span[@class='nav-bar__auth-item'])[1]")).click();

		// Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='/account/']")).click();

		driver.findElement(By.id("current-password")).sendKeys("Test");
		driver.findElement(By.id("new-password")).sendKeys("Test_123");
		driver.findElement(By.id("confirm-password")).sendKeys("Test_123");
		driver.findElement(By.xpath("//a[text()='Change password']")).click();

	}

	@Test(priority = 1)
	public void login() throws InterruptedException {

		driver.get("https://dailyseven.doselab.com/login-manual/");
		// Login page
		driver.manage().window().maximize();
		// Input name and password
		driver.findElement(By.xpath("//input[@id='id_email']")).sendKeys("week5s-m1@yopmail.com");
		driver.findElement(By.xpath("//input[@id='id_password']")).sendKeys("Test_123");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Login button
		driver.findElement(By.xpath("//input[@class='login-modal__submit button--primary']")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// orgchart
		Thread.sleep(500);
		//
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 20);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.
		 * xpath("//div[contains(text(),'Org chart')]")));
		 * 
		 * driver.findElement(By.xpath("//a[@href='/orgchart/']")).click();
		 */
		System.out.println("test for home page after login");
	}

	@Test(enabled = false)
	public void home() throws InterruptedException {

		Thread.sleep(500);

		// WebDriverWait wait = new WebDriverWait(driver, 20);

		// 1. assert for home
		String firstTitle = "Home";
		String firstActulTitle = driver.findElement(By.xpath("//div[contains(text(),'Home')]")).getText();
		Assert.assertEquals(firstActulTitle, firstTitle);

		// 2. Assert for OrgChart
		String secondTitle = "Org chart";
		String secondActulTitle = driver.findElement(By.xpath("//div[contains(text(),'Org chart')]")).getText();
		Assert.assertEquals(secondActulTitle, secondTitle);

		// 3. Assert for Configure
		String thirdTitle = "Configure";
		String thirdActulTitle = driver.findElement(By.xpath("//div[contains(text(),'Configure')]")).getText();
		Assert.assertEquals(thirdActulTitle, thirdTitle);

		System.out.println("This is asserting for all tabs ");
	}

	@Test(enabled = false)
	public void testSearchInorgChart() throws Exception {

		// div[contains(text(),'Org chart')]
		driver.findElement(By.xpath("//div[contains(text(),'Org chart')]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("mack");
		Thread.sleep(500);

	}

	@Test(priority = 5)
	public void testaddSurvey() throws ParseException, InterruptedException {

		login();

		driver.findElement(By.xpath("//div[contains(text(),'Configure')]")).click();
		driver.findElement(By.xpath("//a[@href='/config/surveys/']")).click();
		// (//div[@class='nav-bar__link-text'])[6]
		driver.findElement(By.xpath("//a[@href='/config/surveys/new/']")).click();

		// Set Start date of survey

		// String start_Date="5/21/2020" ;
		DateFormat sd = new SimpleDateFormat("M/d/yyyy");
		String start_Date = sd.format(new Date());

		// **********************current date *********************
		WebElement startDate = driver.findElement(By.xpath("//input[@name='start_date']"));
		startDate.click();

		// driver.findElement(By.xpath("//input[@name='close_date']")).click();

		String currentDate = driver.findElement(By.className("react-datepicker__current-month")).getText();// Currentdate
		Date stDate = new SimpleDateFormat("M/d/yyyy").parse(start_Date);
		Date curDate = new SimpleDateFormat("MMM yyyy").parse(currentDate);
		// replacing stDate to dateobj
		int monthDiff = Months
				.monthsBetween(new DateTime(curDate).withDayOfMonth(1), new DateTime(stDate).withDayOfMonth(1))
				.getMonths();
		boolean isFuture = true;

		if (monthDiff < 0) {
			isFuture = false;
			monthDiff = -1 * monthDiff;
		}

		for (int i = 0; i < monthDiff; i++) {

			if (isFuture)
				driver.findElement(By.xpath("//button[text()='Next Month']")).click();
			else
				driver.findElement(By.xpath("//button[text()='Previous Month']")).click();

		}

		String day;
		// replacing stDate to dateobj
		day = new SimpleDateFormat("d").format(stDate);
		driver.findElement(By.xpath("(//div[text()='" + day + "'])[1]")).click();
		driver.findElement(By.xpath("//input[@name='start_date']")).click();
		driver.findElement(By.xpath("//li[text()='11:30 AM']")).click();
		// end of start date

		// For Close date
		Thread.sleep(1000);

		// String close_Date = "6/21/2020";
		DateFormat cd = new SimpleDateFormat("M/d/yyyy");
		String close_Date = cd.format(new Date());
		WebElement closeDate = driver.findElement(By.xpath("//input[@id='survey-config-end-date']"));
		closeDate.click();

		// String
		// currentDate=driver.findElement(By.className("react-datepicker__current-month")).getText();//
		// Currentdate
		Date clDate = new SimpleDateFormat("M/d/yyyy").parse(close_Date);
		// Date curDate= new SimpleDateFormat("MMM yyyy").parse(currentDate);

		monthDiff = Months
				.monthsBetween(new DateTime(curDate).withDayOfMonth(1), new DateTime(clDate).withDayOfMonth(1))
				.getMonths();
		isFuture = true;

		if (monthDiff < 0) {
			isFuture = false;
			monthDiff = -1 * monthDiff;
		}

		for (int i = 0; i < monthDiff; i++) {

			if (isFuture)
				driver.findElement(By.xpath("//button[text()='Next Month']")).click();
			else
				driver.findElement(By.xpath("//button[text()='Previous Month']")).click();

		}
		// String day;
		day = new SimpleDateFormat("d").format(clDate);
		driver.findElement(By.xpath("//div[text()='" + day + "']")).click();

		driver.findElement(By.xpath("//input[@id='survey-config-end-date']")).click();
		driver.findElement(By.xpath("//li[text()='6:30 PM']")).click();

		// End of Close Date

		// start Result Date
		Thread.sleep(1000);

		// String result_Date = "5/21/2020";
		DateFormat rd = new SimpleDateFormat("M/d/yyyy");
		String result_Date = rd.format(new Date());
		WebElement resultDate = driver.findElement(By.xpath("//input[@name='results_available_date']"));
		resultDate.click();

		// String
		// currentDate=driver.findElement(By.className("react-datepicker__current-month")).getText();//
		// Currentdate
		Date reslDate = new SimpleDateFormat("M/d/yyyy").parse(result_Date);
		// Date curDate= new SimpleDateFormat("MMM yyyy").parse(currentDate);

		monthDiff = Months
				.monthsBetween(new DateTime(curDate).withDayOfMonth(1), new DateTime(reslDate).withDayOfMonth(1))
				.getMonths();
		isFuture = true;

		if (monthDiff < 0) {
			isFuture = false;
			monthDiff = -1 * monthDiff;
		}

		for (int i = 0; i < monthDiff; i++) {

			if (isFuture)
				driver.findElement(By.xpath("//button[text()='Next Month']")).click();
			else
				driver.findElement(By.xpath("//button[text()='Previous Month']")).click();

		}
		// String day;
		day = new SimpleDateFormat("dd").format(reslDate);
		driver.findElement(By.xpath("//div[text()='" + day + "']")).click();

		driver.findElement(By.xpath("//input[@name='results_available_date']")).click();
		driver.findElement(By.xpath("//li[text()='17:45 PM']")).click();

		// Endof Result date

		driver.findElement(By.xpath("//input[@name='title']")).clear();
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(By.xpath("//input[@name='title']")).sendKeys(selectAll);
		driver.findElement(By.xpath("//input[@name='title']")).sendKeys(Keys.DELETE);

		driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Survey1");

		// add survey questions
		driver.findElement(By.xpath("(//span[@class='button__icon'])[1]")).click();
		driver.findElement(By.xpath("//span[(text()='Add new question')]")).click();

		driver.findElement(By.xpath("(//input[@class='survey-questions-chooser__new-input'])[1]"))
				.sendKeys("environment");
		driver.findElement(By.xpath("(//input[@class='survey-questions-chooser__new-input'])[2]"))
				.sendKeys("is environment secure and healthy");
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		driver.findElement(By.xpath("//span[text()='Update questions']")).click();

		// add negative attribute
		driver.findElement(By.xpath("(//span[@class='button__icon'])[2]")).click();
		driver.findElement(By.xpath("//span[(text()='Add new attribute')]")).click();

		driver.findElement(By.xpath("//input[@name='label']")).sendKeys("slow");
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		driver.findElement(By.xpath("//span[text()='Update attributes']")).click();

		// add positive attributes

		driver.findElement(By.xpath("(//span[@class='button__icon'])[3]")).click();
		driver.findElement(By.xpath("//span[(text()='Add new attribute')]")).click();

		driver.findElement(By.xpath("//input[@name='label']")).sendKeys("fast");
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		driver.findElement(By.xpath("//span[text()='Update attributes']")).click();

		// add notification

		driver.findElement(By.xpath("(//span[@class='button__icon'])[4]")).click();
		Select notification = new Select(driver.findElement(By.xpath("//select[@name='type']")));
		notification.selectByValue("SO");
		// select open survey date

		// driver.findElement(By.xpath("//input[@id='delivery_date']")).click();

		String opn_Date = "5/21/2020";
		WebElement openDate = driver.findElement(By.xpath("//input[@id='delivery_date']"));
		openDate.click();

		// String
		// currentDate=driver.findElement(By.className("react-datepicker__current-month")).getText();//
		// Currentdate
		Date opningDate = new SimpleDateFormat("M/d/yyyy").parse(opn_Date);
		// Date curDate= new SimpleDateFormat("MMM yyyy").parse(currentDate);

		monthDiff = Months
				.monthsBetween(new DateTime(curDate).withDayOfMonth(1), new DateTime(opningDate).withDayOfMonth(1))
				.getMonths();
		isFuture = true;

		if (monthDiff < 0) {
			isFuture = false;
			monthDiff = -1 * monthDiff;
		}

		for (int i = 0; i < monthDiff; i++) {

			if (isFuture)
				driver.findElement(By.xpath("//button[text()='Next Month']")).click();
			else
				driver.findElement(By.xpath("//button[text()='Previous Month']")).click();

		}
		// String day;
		day = new SimpleDateFormat("dd").format(opningDate);
		driver.findElement(By.xpath("//div[text()='" + day + "']")).click();
		driver.findElement(By.xpath("//input[@id='delivery_date']")).click();
		driver.findElement(By.xpath("//li[text()='10:45 PM']")).click();

		// end of open survey date
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();

		driver.findElement(By.xpath("//span[text()='Create new survey']")).click();

	}

	@Test(enabled = false)
	public void testSendMessages() throws Exception {

		driver.findElement(By.xpath("//a[@href='/config/']")).click();
		driver.findElement(By.xpath("//a[@href='/config/people/53540/']")).click();

		driver.findElement(By.xpath("//span[text()='Message']")).click();
		driver.findElement(By.xpath("(//span[text()='Send'])[1]")).click();

		// Assert for message
		String firstTitle = "Message successfully sent";
		String firstActulTitle = driver.findElement(By.xpath("//span[text()='Message successfully sent']")).getText();
		Assert.assertEquals(firstActulTitle, firstTitle);

		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));

	}

	@Test(enabled = false)
	public void testQuiteSurvey() throws Exception {

		driver.findElement(By.xpath("//a[@href='/config/']")).click();

		driver.findElement(By.xpath("(//div[@class='nav-bar__link-text'])[6]")).click();
		// assert for status
		String firstTitle = "Private";
		String firstActulTitle = driver.findElement(By.xpath("//span[contains(text(),'Private')]")).getText();
		Assert.assertEquals(firstActulTitle, firstTitle);

	}

	@Test(enabled = false)
	public void updateUser() throws Exception {

		driver.findElement(By.xpath("//a[@href='/config/']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// assert for add new person button

		driver.findElement(By.xpath("//a[@href='/config/people/53540/']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Department viewers']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(500);

	}

	@Test(enabled = false)
	public void updateID() throws Exception {

		driver.findElement(By.xpath("//a[@href='/config/']")).click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		// driver.findElement(By.xpath("//a[@href='/config/people/53352/']")).click();

		driver.findElement(By.partialLinkText("Bill")).click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(By.xpath("//input[@name='id_external']")).sendKeys(selectAll);
		driver.findElement(By.xpath("//input[@name='id_external']")).sendKeys(Keys.DELETE);

		driver.findElement(By.xpath("//input[@name='id_external']")).sendKeys("mse21");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Test(enabled = false)
	public void uploadPhoto() throws Exception {
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[contains(text(),'Configure')]")).click();
		driver.findElement(By.xpath("//a[@href='/config/people/bulk/']")).click();

		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		WebElement uploadElement = driver.findElement(By.xpath("(//input[@type='file'])[2]"));

		String xpath1 = "C:\\Users\\Mandar.Soundalgekar\\Desktop\\CSVFiles\\days\\DailyCheck\\ms1.jpg";
		String xpath2 = "C:\\Users\\Mandar.Soundalgekar\\Desktop\\CSVFiles\\days\\DailyCheck\\s-0.jpg";
		String xpath3 = "C:\\Users\\Mandar.Soundalgekar\\Desktop\\CSVFiles\\days\\DailyCheck\\s-1.jpg";
		String xpath4 = "C:\\Users\\Mandar.Soundalgekar\\Desktop\\CSVFiles\\days\\DailyCheck\\s-2.jpg";

		uploadElement.sendKeys(xpath1 + "\n" + xpath2 + "\n" + xpath3 + "\n" + xpath4);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement button = driver
				.findElement(By.xpath("//form//button[@class='button button--primary Button-sc-1xac9mk-0 AGdpU']"));
		// div[@class='import-people-from-csv']//button[@class='button button--primary
		// Button-sc-1xac9mk-0 AGdpU']
		js.executeScript("arguments[0].click();", button);

	}

	@Test(enabled = false)
	public void upload() throws Exception {
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[contains(text(),'Configure')]")).click();
		driver.findElement(By.xpath("//a[@href='/config/people/bulk/']")).click();

		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		WebElement uploadElement = driver.findElement(By.xpath("(//input[@type='file'])[1]"));

		uploadElement.sendKeys("C:\\Users\\Mandar.Soundalgekar\\Desktop\\CSVFiles\\days\\auto1.csv");
		// for button upload

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement button = driver.findElement(By.xpath(
				"//div[@class='import-people-from-csv']//button[@class='button button--primary Button-sc-1xac9mk-0 AGdpU']"));
		js.executeScript("arguments[0].click();", button);
	}

	@Test(enabled = false)
	public void surveys() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(),'Surveys')]")).click();

	}

	@Test(enabled = false)
	public void payment() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='/config/']")).click();
		// Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//div[@class='nav-bar__link-text'])[9]")).click();
		driver.findElement(By.xpath("//span[text()=\"Replace payment method\"]")).click();
		driver.findElement(By.xpath("//input[@name='cardholder_name']")).sendKeys("Sam");
		// driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);

		driver.switchTo().frame("__privateStripeFrame5");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div/form/span/span/input")).sendKeys("4242");

		Thread.sleep(1500);
		driver.findElement(By.xpath("//div/form/span/span/input")).sendKeys("4242");
		Thread.sleep(1500);

		driver.findElement(By.xpath("//div/form/span/span/input")).sendKeys("4242");
		Thread.sleep(1500);

		driver.findElement(By.xpath("//div/form/span/span/input")).sendKeys("4242");
		Thread.sleep(1000);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("__privateStripeFrame6");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div/form/span/span/input")).sendKeys("1122");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("__privateStripeFrame7");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div/form/span/span/input")).sendKeys("432");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//span[text()='Save payment information']")).click();
		String message = "4242";
		String actualMessage = driver.findElement(By.xpath("//span[text()='4242']")).getText();
		assertEquals(actualMessage, message);

	}

	@Test(enabled = false)
	public void addUser() throws Exception {

		driver.findElement(By.xpath("//a[@href='/config/']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// assert for add new person button

		String addPerson = "Add new person";
		String ActualaddPerson = driver.findElement(By.xpath("//a[@href='/config/people/new/']")).getText();
		Assert.assertEquals(ActualaddPerson, addPerson);

		// ________________ add new user____________________________

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/config/people/new/']")));
		driver.findElement(By.xpath("//a[@href='/config/people/new/']")).click();

		driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("mans");
		driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("mansuser");

		// ***********************************for manager
		driver.findElement(By.xpath("//select[@name='manager']")).click();
		// search****************************
		Thread.sleep(100);
		driver.findElement(By.xpath("//input[@name='position']")).sendKeys("Accountant");

		/*
		 * driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		 * 
		 * driver.findElement(By.xpath("//select[@id='manager']")).click();
		 * Thread.sleep(500);
		 * 
		 * driver.findElement(By.
		 * xpath("(.//*[normalize-space(text()) and normalize-space(.)='Jacky Adam (6)'])[2]/following::div[2]"
		 * )).click();
		 */

		// ******************for department
		// dropdown**************************************
		Thread.sleep(3000);
		driver.findElement(By.xpath("//select[@name='division_id']/option[text() = 'Sales']")).click();
		driver.manage().timeouts().implicitlyWait(21, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='work_email']")).sendKeys("may29ms20@gmail.com");
		// driver.findElement(By.xpath("//span[text()='Cancel']")).click();
		// driver.findElement(By.xpath("//span[contains(text(),'Remove from org
		// chart')]")).click();

		/*
		 * String ActualFirstTitle=
		 * driver.findElement(By.xpath("//a[@href='/config/people/bulk/']")).getText();
		 * String FirstTitle="Bulk download/upload";
		 * Assert.assertEquals(ActualFirstTitle, FirstTitle);
		 * driver.findElement(By.xpath("//a[@href='/config/people/bulk/']")).click();
		 * 
		 */

		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Test(enabled = false)
	public void myAccount() throws Exception {
		Thread.sleep(1000);

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("(//span[@class='nav-bar__auth-item'])[1]"))).build()
				.perform();
		// driver.findElement(By.xpath("(//span[@class='nav-bar__auth-item'])[1]")).click();
		// Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='/account/']")).click();
		driver.findElement(By.xpath("//input[@id='first-name']")).clear();
		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Douglas");
		driver.findElement(By.xpath("//input[@id='last-name']")).clear();
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("mayer");
		driver.findElement(By.xpath("//input[@value='Save account info']")).click();

		String success = "Success";
		String Actualtext1 = driver.findElement(By.xpath("//p[@class='banner__item banner__item--title']")).getText();
		Thread.sleep(500);
		Assert.assertEquals(Actualtext1, success);

	}

	@Test(enabled = false)
	public void surveyQuest() throws Exception {

		driver.findElement(By.xpath("//div[contains(text(),'Home')]")).click();

		driver.findElement(By.xpath("//button")).click();

		driver.findElement(By.xpath("(//label[text()='Agree'])[1]")).click();
		driver.findElement(By.xpath("(//label[text()='Strongly Agree'])[2]")).click();
		driver.findElement(By.xpath("(//label[text()='Agree'])[3]")).click();
		driver.findElement(By.xpath("(//label[text()='Strongly Agree'])[4]")).click();
		driver.findElement(By.xpath("(//label[text()='Agree'])[5]")).click();

		driver.findElement(By.xpath("//span[contains(text(),'Save')]")).click();

// Coworkers rating and Attributes rating

		driver.findElement(By.xpath("(//li[@class='TeaserBox-pglzut-0 jrpCMo teaser-box-container'])[1]")).click();
		// java.util.List<WebElement> lst
		// =driver.findElements(By.xpath("//li[@class='TeaserBox-pglzut-0 jrpCMo
		// teaser-box-container']"));
		// System.out.println(lst);

		driver.findElement(By.xpath("//label[text()='3']")).click();

		driver.findElement(By.xpath("//span[text()='Finish']")).click();

		// logout

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("(//span[@class='nav-bar__auth-item'])[1]"))).build()
				.perform();
		// driver.findElement(By.xpath("(//span[@class='nav-bar__auth-item'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='/logout/']")).click();
		Thread.sleep(500);

	}

	@Test(enabled = false)
	public void magiclink() throws Exception {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		Thread.sleep(1000);
		driver.get("https://dailyjuly.doselab.com/login/");
		driver.findElement(By.xpath("//input[@name='user_email']")).sendKeys("june11ms1@yopmail.com");
		driver.findElement(By.xpath("//span[text()='Send a new sign-in link']")).click();
		// Assert for message

		String message = "Link sent";
		String Actualmessage = driver.findElement(By.xpath("//span[text()='Link sent']")).getText();
		Assert.assertEquals(Actualmessage, message);

		driver.get("http://www.yopmail.com/");
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys("june11ms1");
		driver.findElement(By.xpath("//input[@class='sbut']")).click();

		// Find frame
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ifmail");
		driver.findElement(By.xpath("//a[@class='magic-link']")).click();
		Thread.sleep(10500);
		// 1. assert for home
		// assert snapshot
		String parentWinHandle = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			// System.out.println(winHandle);
		}

	}

	// Admin site login and CX configuration

	@Test(enabled = false)
	public void adminCXconfigure() throws Exception {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		Thread.sleep(500);
		driver.get("https://macorva-staging.herokuapp.com/admin/login/?next=/admin/");
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin@macorva.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin");
		// Login button

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		Thread.sleep(1500);
		driver.findElement(By.xpath("(//a[@href='/admin/common/institution/'])[2]")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Dailyjuly")).click();
		// FOR upload logo
		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));

		uploadElement.sendKeys("C:\\Users\\Mandar.Soundalgekar\\Desktop\\CSVFiles\\days\\Logo.png");

		driver.findElement(By.xpath("//input[@name='options-0-has_cx']")).click();
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(By.xpath("(//input[@type='number'])[1]")).sendKeys(selectAll);
		driver.findElement(By.xpath("(//input[@type='number'])[1]")).sendKeys(Keys.DELETE);
		driver.findElement(By.xpath("(//input[@type='number'])[1]")).sendKeys("5");
		String selectAll1 = Keys.chord(Keys.CONTROL, "a");
		driver.findElement(By.xpath("(//input[@type='number'])[1]")).sendKeys(selectAll1);
		driver.findElement(By.xpath("(//input[@type='number'])[1]")).sendKeys(Keys.DELETE);
		driver.findElement(By.xpath("(//input[@type='number'])[1]")).sendKeys("4");

		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='_save']")).click();
		driver.findElement(By.xpath("(//a[@href='/admin/'])[2]")).click();
		Thread.sleep(500);

		// Add first Location
		driver.findElement(By.xpath("(//a[@href='/admin/common/location/'])[2]")).click();

		driver.findElement(By.xpath("//a[@href='/admin/common/location/add/']")).click();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Alabamas");
		driver.findElement(By.xpath("//input[@name='id_external']")).sendKeys("ABs1");
		Thread.sleep(100);
		driver.findElement(By.xpath("//select[@name='institution']")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("//option[contains(text(),'dailyjuly')]")).click();
		// (//option[contains(text(),'dailyjuly <https://dailyjuly.doselab.com')])[1]
		driver.findElement(By.xpath("//input[@value='Save']")).click();

		// Add Second Location

		driver.findElement(By.xpath("//a[@href='/admin/common/location/add/']")).click();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Arkansass");
		driver.findElement(By.xpath("//input[@name='id_external']")).sendKeys("AR1");
		Thread.sleep(100);
		driver.findElement(By.xpath("//select[@name='institution']")).click();
		driver.findElement(By.xpath("//option[contains(text(),'dailyjuly')]")).click();

		// driver.findElement(By.xpath("//option[@value='482']")).click();
		driver.findElement(By.xpath("//input[@value='Save']")).click();

		// Location grouping
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[@href='/admin/'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[@href='/admin/common/locationgrouping/'])[2]")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//a[@class='addlink']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//input[@id='id_name']")).sendKeys("US-G-1");
		driver.findElement(By.xpath("//select[@name='institution']")).click();
		// driver.findElement(By.xpath("//option[@value='482']")).click();
		driver.findElement(By.xpath("//option[contains(text(),'dailyjuly')]")).click();

		Thread.sleep(100);
		driver.findElement(By.xpath("//select[@id='id_LocationGrouping_locations-0-location']")).click();
		Thread.sleep(100);

		driver.findElement(By.xpath("(//option[contains(text(),'Alabamas')])[1]")).click();
		// driver.findElement(By.xpath("//option[@value='139']")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("//a[contains(text(),'Add another Locationgrouping-location relationship')]"))
				.click();
		driver.findElement(By.xpath("//select[@id='id_LocationGrouping_locations-1-location']")).click();
		driver.findElement(By.xpath("(//option[contains(text(),'Arkansass')])[1]")).click();

		// driver.findElement(By.xpath("//option[@value='140']")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("//input[@name='_save']")).click();

		// add survey option...
		Thread.sleep(1000);

		driver.findElement(By.xpath("(//a[@href='/admin/'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[@href='/admin/customer/surveyoptions/'])[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'dailyjuly')]")).click();
		Thread.sleep(100);

		driver.findElement(By.xpath("//input[@name='sms_prompt']")).clear();
		driver.findElement(By.xpath("//input[@name='sms_prompt']")).sendKeys(
				"Thanks for visiting dailyjuly please give us your 10 seconds to rate us and serve you better! ");
		driver.findElement(By.xpath("//textarea[@id='id_email_prompt']")).clear();
		driver.findElement(By.xpath("//textarea[@id='id_email_prompt']"))
				.sendKeys("Please provide your valuable feedback to serve you better!Thanks");
		Thread.sleep(110);
		driver.findElement(By.xpath("//input[@name='_save']")).click();

		/*
		 * // public void addUser() throws Exception { WebDriverWait wait = new
		 * WebDriverWait(driver, 55);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(configure));
		 * driver.findElement(configure).click(); String addPerson =
		 * prop.getProperty("ADDPERSON");
		 * wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(newperson));
		 * String ActualaddPerson = driver.findElement(newperson).getText();
		 * Assert.assertEquals(ActualaddPerson, addPerson); // ________________ add new
		 * user____________________________
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(newperson));
		 * driver.findElement(newperson).click(); //
		 * _________________________________________________________
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(ID));
		 * driver.findElement(ID).clear(); String selectAll = Keys.chord(Keys.CONTROL,
		 * "a"); driver.findElement(ID).sendKeys(selectAll);
		 * driver.findElement(ID).sendKeys(Keys.DELETE);
		 * driver.findElement(ID).sendKeys(prop.getProperty("PERSONID"));
		 * driver.findElement(firstname).sendKeys(prop.getProperty("FIRSTNAME"));
		 * driver.findElement(lastname).sendKeys(prop.getProperty("LASTNAME"));
		 * 
		 * // ***********************************for manager //
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(manager));
		 * 
		 * // driver.findElement(manager).click(); //
		 * driver.findElement(By.xpath("//option[contains(text(),'Frank //
		 * Oropesa')]")).click();
		 * driver.findElement(position).sendKeys(prop.getProperty("POSITION")); //
		 * ******************for department driver.findElement(department).click();
		 * wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(workemail));
		 * driver.findElement(workemail).sendKeys(prop.getProperty("WORKEMAIL"));
		 * driver.findElement(addtoOrgchart).click(); Assert String addmsg =
		 * prop.getProperty("ADDMSG");
		 * wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addmsgs));
		 * String ActualAddmsg = driver.findElement(addmsgs).getText();
		 * Assert.assertEquals(ActualAddmsg, addmsg); }
		 */

		/*
		 * // @Test(priority=4) public void payment() throws Exception {
		 * driver.findElement(configure).click(); // Thread.sleep(2000);
		 * driver.findElement(By.xpath("//div[text()='Payment']")).click();
		 * driver.findElement(By.xpath("//span[text()=\"Replace payment method\"]")).
		 * click();
		 * driver.findElement(By.xpath("//input[@name='cardholder_name']")).sendKeys(
		 * "Sam");
		 * 
		 * driver.switchTo().frame("__privateStripeFrame5"); Thread.sleep(5000);
		 * driver.findElement(By.xpath("//div/form/span/input")).sendKeys("4242");
		 * 
		 * Thread.sleep(1500);
		 * driver.findElement(By.xpath("//div/form/span/span/input")).sendKeys("4242");
		 * Thread.sleep(1500);
		 * 
		 * driver.findElement(By.xpath("//div/form/span/span/input")).sendKeys("4242");
		 * Thread.sleep(1500);
		 * 
		 * driver.findElement(By.xpath("//div/form/span/span/input")).sendKeys("4242");
		 * Thread.sleep(1000);
		 * 
		 * driver.switchTo().defaultContent();
		 * 
		 * driver.switchTo().frame("__privateStripeFrame6"); Thread.sleep(2000);
		 * driver.findElement(By.xpath("//div/form/span/span/input")).sendKeys("1122");
		 * driver.switchTo().defaultContent();
		 * driver.switchTo().frame("__privateStripeFrame7"); Thread.sleep(1000);
		 * driver.findElement(By.xpath("//div/form/span/span/input")).sendKeys("432");
		 * driver.switchTo().defaultContent();
		 * driver.findElement(By.xpath("//span[text()='Save payment information']")).
		 * click(); String message = "4242"; String actualMessage =
		 * driver.findElement(By.xpath("//span[text()='4242']")).getText();
		 * assertEquals(actualMessage, message); }
		 */
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

}
