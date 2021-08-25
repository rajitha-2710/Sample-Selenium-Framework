package testcases;

import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import commonmethods.CommonMethods;

public class Demo extends CommonMethods{

	//@Test
	public void demo()
	{
		
		CommonMethods cmnmethd=new CommonMethods();
		cmnmethd.launchBrowser("Http://Book.Theautomatedtester.Co.Uk/");
		cmnmethd.enterText("id", "q", "Rajitha");
		cmnmethd.clickElement("linktext","Chapter1");
		cmnmethd.selectDropdown("id", "selecttype", "selectbyvisibletext", "Selenium RC");
		cmnmethd.selectDropdown("id", "selecttype", "index", 0);
		cmnmethd.selectDropdown("id", "selecttype", "selectbyvalue", "Selenium Code");
		cmnmethd.assertElement("xpath", "//div[contains(text(),'Assert that this text is on the page')]", "Assert that this text is on the page");
		cmnmethd.getoptionsfromDropdown("id", "selecttype");
		cmnmethd.getTitle();
		cmnmethd.clickElement("xpath","//div[@class='multiplewindow' and contains(text(),'Click this link to launch another window')]");
		cmnmethd.getIntoLastWindow();
		cmnmethd.getText("id", "popuptext");
		cmnmethd.clickElement("id", "closepopup");
		cmnmethd.getIntoLastWindow();
		cmnmethd.clickElement("linktext","Home Page");
		cmnmethd.clickElement("linktext","Chapter4");
		cmnmethd.clickElement("id", "hoverOver");
		cmnmethd.alertHandling("accept", "");

	}

	//@Test
	public void datepicker() throws ParseException, InterruptedException
	{
		CommonMethods cmnmethd=new CommonMethods();
		cmnmethd.launchBrowser("http://www.seleniumeasy.com/test/jquery-date-picker-demo.html");
		cmnmethd.clickElement("xpath","//label[contains(text(),'From')]//following::input");
		cmnmethd.selectDate1();

	}

	//@Test
	public void fileUpload()
	{
		CommonMethods cmnmethd=new CommonMethods();
		cmnmethd.launchBrowser("http://demo.guru99.com/test/upload/");
		cmnmethd.enterText("xpath", "//input[contains(@id,'uploadfile')]", "C:\\Users\\rajit\\Documents\\Veerapandi\\IMG_20180617_072815188.jpg");
		cmnmethd.clickElement("id","terms");
		cmnmethd.clickElement("id","submitbutton");
	}

	//@Test
	public void fileDownload() throws InterruptedException
	{
		CommonMethods cmnmethd=new CommonMethods();
		cmnmethd.launchBrowser("http://demo.guru99.com/test/yahoo.html");
		String sourcelocation=cmnmethd.getAttribute("id","messenger-download","href");
		String wgetcmd="cmd /c C:\\Users\\rajit\\OneDrive\\Documents\\Applications\\wget.exe -P D:\\DownloadedApplication --no-check-certificate "+sourcelocation;

		try
		{
			Process exec=Runtime.getRuntime().exec(wgetcmd);
			int exitVal = exec.waitFor();
			System.out.println("Exit value: " + exitVal);

		} catch (IOException ex) {
			System.out.println(ex.toString());
		}

	}

	//@Test
	public void toolTip() throws InterruptedException
	{
		CommonMethods cmnmethd=new CommonMethods();
		cmnmethd.launchBrowser("http://jqueryui.com/tooltip/");
		cmnmethd.switchToFrame("index", "0");
		String str=cmnmethd.locateElement("xpath", "//input[@id='age']").getAttribute("title");
		System.out.println(str);
	}

	//@Test
	public void autoComplete() throws InterruptedException
	{
		CommonMethods cmnmethd=new CommonMethods();
		cmnmethd.launchBrowser("http://jqueryui.com/autocomplete/");
		cmnmethd.switchToFrame("index", "0");
		cmnmethd.enterText("xpath", "//label[contains(text(),'Tags')]/following::input","javascript");
		cmnmethd.locateElement("xpath","//label[contains(text(),'Tags')]/following::ul").sendKeys(Keys.TAB);
	}
	
	//@Test
	public void autoIt() throws IOException
	{
		CommonMethods cmnmethd=new CommonMethods();
		cmnmethd.launchBrowser("http://toolsqa.com/automation-practice-form/");
		cmnmethd.clickElement("id", "photo");
		Runtime.getRuntime().exec("C:\\Users\\rajit\\eclipse-workspace\\AutoIt\\FileUpload.exe");
		System.out.println("file uploaded successfully");
	}
	
	@Test
		public void clickingImageandgetPlaceholder() throws IOException
		{
			CommonMethods cmnmethd=new CommonMethods();
			cmnmethd.launchBrowser("https://www.facebook.com/");
			cmnmethd.clickElement("xpath", "//i[starts-with(@class,'fb_logo')]");
			//cmnmethd.clickElement("xpath", "//img[@src='https://static.xx.fbcdn.net/rsrc.php/v3/yn/r/099etSefEv-.png']");
			cmnmethd.getAttribute("name", "firstname", "placeholder");
		}
	
	

	
	
}





























