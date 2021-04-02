package macorva.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SurveyConfig extends SurveyTest{
	
	@Test(priority=6)
	public void testaddSurveys() throws ParseException, InterruptedException {
		driver.findElement(By.xpath("//div[contains(text(),'Configure')]")).click();
		driver.findElement(By.xpath("//a[@href='/config/surveys/']")).click();
		driver.findElement(By.xpath("//a[@href='/config/surveys/new/']")).click();

		// Set Start date of survey

		// String start_Date="5/21/2020" ;
		DateFormat ds = new SimpleDateFormat("M/d/yyyy");
		String start_Date = ds.format(new Date());
		System.out.println(start_Date);

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
		driver.findElement(By.xpath("//li[text()='10:30 AM']")).click();
		// end of start date

		// For Close date
		Thread.sleep(1000);

		String close_Date = "6/21/2020";
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
		driver.findElement(By.xpath("//li[text()='12:30 PM']")).click();

		// End of Close Date

		// start Result Date
		Thread.sleep(1000);

		String result_Date = "5/21/2020";
		WebElement resultDate = driver.findElement(By.xpath("//input[@name='results_available_date']"));
		resultDate.click();
		
		// String
		// currentDate=driver.findElement(By.className("react-datepicker__current-month")).getText();//
		// Currentdate
		Date reslDate = new SimpleDateFormat("MM/dd/yyyy").parse(result_Date);
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
		driver.findElement(By.xpath("//li[text()='12:45 PM']")).click();

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
		Date opningDate = new SimpleDateFormat("MM/dd/yyyy").parse(opn_Date);
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

	
}
