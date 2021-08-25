package commonmethods;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.util.Asserts;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class CommonMethods {

	WebDriver driver;

	public void launchBrowser(String url) 
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\rajit\\eclipse-workspace\\DemoProjects\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		System.out.println("kjbkj");
		driver.get(url);
	}
	public WebElement locateElement(String locator,String value)
	{
		WebElement we = null;

		if(locator=="xpath")
		{
			we=driver.findElement(By.xpath(value));

		}
		else if(locator=="id")
		{
			we=driver.findElement(By.id(value));

		}
		else if(locator=="name")
		{
			we=driver.findElement(By.name(value));
		}
		else if(locator=="classname")
		{
			we=driver.findElement(By.id(value));
		}
		else if(locator=="linktext")
		{
			we=driver.findElement(By.linkText(value));
		}
		else if(locator=="partiallinktext")
		{
			we=driver.findElement(By.partialLinkText(value));
		}
		else if(locator=="css")
		{
			we=driver.findElement(By.cssSelector(value));

		}

		return we;
	}

	public void enterText(String locator,String value,String text)
	{

		WebElement element=locateElement(locator,value);
		element.sendKeys(text);
	}

	public void clickElement(String locator,String value)
	{
		WebElement element=locateElement(locator,value);
		element.click();
	}

	public void selectDropdown(String locator,String value,String selectby,Object selectbyvalue)
	{
		WebElement element=locateElement(locator,value);

		Select sel=new Select(element);

		if(selectby.equals("index"))
		{
			sel.selectByIndex((Integer) selectbyvalue);
		}
		else if(selectby.equals("selectbyvalue"))
		{
			sel.selectByValue((String) selectbyvalue);
		}
		else if(selectby.equals("selectbyvisibletext"))
		{
			sel.selectByVisibleText((String) selectbyvalue);
			
		}
	}

	public void getoptionsfromDropdown(String locator,String value)
	{
		String drpdwnvalue="";
		WebElement element=locateElement(locator,value);
		Select sel=new Select(element);
		List<WebElement> options=sel.getOptions();
		for(WebElement option:options)
		{
			drpdwnvalue=option.getText();
			System.out.println(drpdwnvalue);
		}

	}

	public void assertElement(String locator,String value, String expectedtext)
	{
		WebElement element=locateElement(locator,value);
		String actualtext=element.getText();
		Assert.assertEquals(actualtext, expectedtext);
		System.out.println("Actual Text:"+actualtext+" "+"Expected Text:"+expectedtext);

	}

	public void getText(String locator,String value)
	{
		WebElement element=locateElement(locator,value);
		String text=element.getText();
		System.out.println(text);
	}

	public String getAttribute(String locator,String value,String attribute)
	{
		WebElement element=locateElement(locator,value);
		String text=element.getAttribute(attribute);
		System.out.println(text);
		return text;
	}

	public void assertElement(boolean condition, String message)
	{
		Assert.assertTrue(condition, message);
		Assert.assertTrue(driver.getTitle().equals("abc"));
	}

	public void getTitle()
	{
		String title=driver.getTitle();
		System.out.println(title);
	}

	public void closeWindow()
	{
		driver.close();
	}

	public void alertHandling(String alerttype,String value)
	{
		Alert alert= driver.switchTo().alert();

		if(alerttype.equals("accept"))
		{
			alert.accept();
		}

		else if(alerttype.equals("dismiss"))
		{
			alert.dismiss();
		}
		else if("inputtext".equals(alerttype))
		{
			alert.sendKeys(value);
			alert.accept();
		}

		else if(alerttype.equals("getalerttext"))
		{
			String text=alert.getText();
			System.out.println(text);
		}
	}

	public void getIntoLastWindow()
	{
		Set<String> windows=driver.getWindowHandles();
		for(String win:windows)
		{
			driver.switchTo().window(win);
		}
	}

	public void switchToFrame(String frameby,String framebyvalue)
	{
		if(frameby.equalsIgnoreCase("id")||frameby.equalsIgnoreCase("name"))
		{
			driver.switchTo().frame(framebyvalue);
		}

		if(frameby.equalsIgnoreCase("index"))
		{
			int indexvalue=Integer.parseInt(framebyvalue);
			driver.switchTo().frame(indexvalue);
		}

		if(frameby.equalsIgnoreCase("webelement"))
		{
			driver.switchTo().frame(framebyvalue);
		}

	}
	public void exitFrame()
	{
		driver.switchTo().defaultContent();
	}

	public void selectDate(String targetdate) throws ParseException, InterruptedException
	{

		LocalDateTime curntdate=LocalDateTime.now();
		DateTimeFormatter fmt=DateTimeFormatter.ofPattern("dd/MMM/yyyy");
		String todaydate=fmt.format(curntdate);

		String[] str=todaydate.split("/");
		String cdate=str[0];
		String cmonth=str[1];
		String cyear=str[2];
		int curryear=Integer.parseInt(str[2]);

		String[] str1=targetdate.split("/");
		String tdate=str1[0];
		String tmonth=str1[1];
		String tyear=str1[2];
		int taryear=Integer.parseInt(str1[2]);


		//selecting the year
		while(taryear!=curryear)
		{
			if(taryear<curryear)
			{
				clickElement("xpath","//a[contains(@class,'ui-datepicker-prev')]/span");
				String uiyear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
				if(uiyear.equals(tyear))
				{
					break;
				}
			}

			else if(taryear>curryear)
			{
				clickElement("xpath","//a[contains(@class,'ui-datepicker-next')]/span");
				String uiyear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
				if(uiyear.equals(tyear))
				{
					break;
				}
			}
		}

		//selecting the month
		selectDropdown("xpath","//select[@class='ui-datepicker-month']","selectbyvisibletext",tmonth);

		//selecting the date
		List<WebElement> daterows=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr"));
		System.out.println(daterows.size());

		for(int i=0;i<daterows.size();i++)
		{
			int flag=0;
			List<WebElement> datecols= daterows.get(i).findElements(By.tagName("td"));

			for(int j=0;j<datecols.size();j++)
			{
				String date=datecols.get(j).getText();
				if(date.equals(tdate))
				{
					datecols.get(j).click();
					flag=1;
					break;
				}
			}

			if(flag==1)
			{
				break;
			}
		}


	}


	public void selectDate1() throws ParseException, InterruptedException
	{
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, 35);
		Date tardat=cal.getTime();
		String targetdate=new SimpleDateFormat("dd/MMM/yyyy").format(tardat);

		Calendar cal1=Calendar.getInstance();
		Date currdate=cal1.getTime();
		String currentdate=new SimpleDateFormat("dd/MMM/yyyy").format(currdate);

		String[] str=currentdate.split("/");
		String cdate=str[0];
		String cmonth=str[1];
		String cyear=str[2];
		int curryear=Integer.parseInt(str[2]);

		String[] str1=targetdate.split("/");
		String tdate=str1[0];
		String tmonth=str1[1];
		String tyear=str1[2];
		int taryear=Integer.parseInt(str1[2]);

		//selecting the year
		while(taryear!=curryear)
		{
			if(taryear<curryear)
			{
				clickElement("xpath","//a[contains(@class,'ui-datepicker-prev')]/span");
				String uiyear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
				if(uiyear.equals(tyear))
				{
					break;
				}
			}

			else if(taryear>curryear)
			{
				clickElement("xpath","//a[contains(@class,'ui-datepicker-next')]/span");
				String uiyear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
				if(uiyear.equals(tyear))
				{
					break;
				}
			}
		}

		//selecting the month
		selectDropdown("xpath","//select[@class='ui-datepicker-month']","selectbyvisibletext",tmonth);

		//selecting the date
		List<WebElement> daterows=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr"));
		System.out.println(daterows.size());

		for(int i=0;i<daterows.size();i++)
		{
			int flag=0;
			List<WebElement> datecols= daterows.get(i).findElements(By.tagName("td"));

			for(int j=0;j<datecols.size();j++)
			{
				String date=datecols.get(j).getText();
				if(date.equals(tdate))
				{
					datecols.get(j).click();
					flag=1;
					break;
				}
			}

			if(flag==1)
			{
				break;
			}
		}


	}

	public void mouseOver(String locator,String value)
	{
		WebElement target=locateElement(locator,value);
		Actions action=new Actions(driver);
		action.moveToElement(target).build().perform();
	
	}
	
	public void explicitWait(String locator,String value)
	{ 	
		Wait wait=new WebDriverWait(driver,30);
		if(locator=="id")
		{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value)));
		}
		if(locator=="xpath")
		{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
		}
	}
	
	public void pageLoadTimeout(int time)
	{
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}


}


