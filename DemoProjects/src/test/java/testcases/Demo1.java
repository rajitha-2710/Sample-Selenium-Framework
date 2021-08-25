package testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import commonmethods.CommonMethods;

public class Demo1 extends CommonMethods{
	private static final TimeUnit SECONDS = null;
	WebDriver driver;

	//@Test

	public void settingChromeOption() throws InterruptedException
	{
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver","C:\\Users\\rajit\\eclipse-workspace\\DemoProjects\\Drivers\\chromedriver.exe");
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-notifications");
		option.addArguments("--disable-infobars");
		option.addArguments("--disable-popup-blocking");
		option.addArguments("--start-maximized");
		//dc.setCapability(ChromeOptions.CAPABILITY, option);
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"chrome");
		option.merge(dc);
		driver=new ChromeDriver(option);
		driver.get("https://blog.wishpond.com/post/94441887713/5-examples-of-website-popups-that-work");
		Thread.sleep(5000);
		System.out.println(driver.getCurrentUrl());	
	}

	//@Test
	public void windowHandling() throws InterruptedException
	{

		System.setProperty("webdriver.chrome.driver","C:\\Users\\rajit\\eclipse-workspace\\DemoProjects\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");
		String parentwindow=driver.getWindowHandle();
		driver.findElement(By.xpath("//button[@id='button1']")).click();
		Set<String> windows=driver.getWindowHandles();
		for(String win:windows)
		{
			driver.switchTo().window(win);
		}
		System.out.println(driver.getTitle());
		driver.switchTo().window(parentwindow);
		driver.findElement(By.xpath("//button[contains(text(),'New Message Window')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'New Browser Tab')]")).click();
		Set<String> windows1=driver.getWindowHandles();
		for(String win:windows1)
		{
			driver.switchTo().window(win);
		}
		System.out.println(driver.findElement(By.tagName("body")).getText());
		Set<String> windows2=driver.getWindowHandles();
		for(String win:windows2)
		{   
			driver.switchTo().window(win);
			String title=driver.getTitle();
			if((title).equalsIgnoreCase("QA Automation Tools Tutorial"))
			{
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[contains(text(),'Get this Slider Free ')]")).click();

				Set<String> windows3=driver.getWindowHandles();
				for(String win1:windows3)
				{
					driver.switchTo().window(win1);

				}
				System.out.println(driver.getTitle());
			}

		}}


	//@Test
	public void frameHandling() throws InterruptedException
	{

		System.setProperty("webdriver.chrome.driver","C:\\Users\\rajit\\eclipse-workspace\\DemoProjects\\Drivers\\chromedriver.exe");
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-popup-blocking");
		driver=new ChromeDriver(option);
		driver.get("http://toolsqa.com/iframe-practice-page/");
		Thread.sleep(3000);
		/*driver.switchTo().frame("iframe1");
		driver.findElement(By.xpath("//strong[contains(text(),'First name:')]/following::input[1]")).sendKeys("Rajitha");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("iframe2");
		driver.findElement(By.linkText("Hello world!")).click();
		driver.switchTo().parentFrame();*/
		List<WebElement> iframes=driver.findElements(By.tagName("iframe"));
		System.out.println(iframes.size());
		for(int i=0;i<=iframes.size();i++)
		{
			driver.switchTo().frame(i);
			try
			{
				if(driver.findElement(By.xpath("//a[contains(text(),'Hello world!')]")).isDisplayed())
				{
					System.out.println("Text is present in" +i+" "+ "frame");
				}
			}
			catch(Exception e)
			{
				System.out.println("element is not in this frame");
			}
			driver.switchTo().defaultContent();
		}

		/*driver.switchTo().parentFrame();
		driver.get("http://way2automation.com/way2auto_jquery/frames-windows/frameset.html");
		driver.switchTo().frame("topFrame");
		System.out.println(driver.findElement(By.xpath("//h2[contains(text(),'www.way2automation.com')]")).getTagName());*/


		/*	driver.get("http://way2automation.com/way2auto_jquery");

		JavascriptExecutor js=(JavascriptExecutor) driver;
		//js.executeScript("arguments[0].click();", driver.findElement(By.linkText("Signin")));
		//js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@type='submit']")));
		//js.executeScript("arguments[0].click();", driver.findElement(By.className("button")));
		Thread.sleep(3000);
		driver.findElement(By.linkText("Signin")).click();
		Thread.sleep(3000);


		js.executeScript("arguments[0].value='Rajitha'",driver.findElement(By.name("username")));
		js.executeScript("arguments[0].value='Rajitha27!'",driver.findElement(By.name("password")));

		//js.executeScript("document.getElementsByName('username')[0].value='Rajitha'");
		//js.executeScript("document.getElementsByName('password')[0].value='Rajitha27!'");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Rajitha");
		driver.findElement(By.name("password")).sendKeys("Rajitha27!");
		//Thread.sleep(3000);
		//js.executeScript("arguments[0].value='Rajitha';",driver.findElement(By.name("username")));
		//js.executeScript("arguments[0].value='password';",driver.findElement(By.name("password")));


		js.executeScript("arguments[0].click();", driver.findElement(By.className("button")));

		driver.findElement(By.xpath("//h2[contains(text(),'Tooltip')]")).click();
		List unames=(List) js.executeScript("return document.getElementsByName('username').value='Rajitha';");
		List pwds=(List) js.executeScript("return document.getElementsByName('password').value='Rajitha27!';");
		;
		//js.executeScript("arguments[0].click();", "button");
		//js.executeScript("document.getElementsByClassName('button')[0].click()");
		 */		
	}
	//@Test
	public void scrollBar()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\rajit\\eclipse-workspace\\DemoProjects\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		JavascriptExecutor js=(JavascriptExecutor) driver;
		driver.get("http://jqueryui.com/slider/");
		js.executeScript("window.scrollBy(0,500)");
		driver.switchTo().frame(0);
		WebElement we=driver.findElement(By.id("slider"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(we, 25, 0).perform();

	}

	@Test
	public void webTable()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\rajit\\eclipse-workspace\\DemoProjects\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://demo.guru99.com/test/table.html");
		//driver.switchTo().frame(0);
		//System.out.println(driver.findElements(By.xpath("//div[@id='includedContent']/following::table/tbody/tr")));
		List<WebElement> rows=driver.findElements(By.xpath("//div[@id='includedContent']/following::table/tbody/tr"));
		String str;
		for(int i=0;i<rows.size();i++)
		{
			int flag=0;
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			for(int j=0;j<cols.size();j++)
			{
				System.out.println(cols.get(j).getText());
				JavascriptExecutor js=(JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style','background:Yellow;border:2px solid red;font-weight:bold')",cols.get(j));
				System.out.println(cols.get(j).getCssValue("color"));
				System.out.println(cols.get(j).getCssValue("background-color"));
				System.out.println(cols.get(j).getCssValue("font-weight"));
			}
			/*if(str.equals())
			{
				JavascriptExecutor js=(JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style','background:Yellow;border:2px solid red')",cols.get(j));
			}*/
			
					
		}

	}
	
	//@Test
	public void takeScreenshot() throws IOException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\rajit\\eclipse-workspace\\DemoProjects\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://demo.guru99.com/test/table.html");
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		/*long d=System.currentTimeMillis();
		FileUtils.copyFile(src, new File("C:\\Users\\rajit\\Documents\\html"+d+".jpg"));*/
		BufferedImage fullimage=ImageIO.read(src);
		WebElement we=driver.findElement(By.xpath("//div[@class='logo']/a/img"));
		Point point=we.getLocation();
		int width=we.getSize().getWidth();
		int height=we.getSize().getHeight();
		BufferedImage subimage=fullimage.getSubimage(point.getX(), point.getY(), width,height);
		ImageIO.write(subimage, "png", src);
		
		FileUtils.copyFile(src, new File("C:\\Users\\rajit\\Documents\\subimage.png"));
	}
}




























