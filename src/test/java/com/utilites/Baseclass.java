package com.utilites;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Baseclass {
	
	Readconfig readconfig=new Readconfig();  //as soon as u create object it invoke constructor and invoke configuration file
	
	public String baseurl=readconfig.getappurl();
	public String uname=readconfig.getusername();
	public String pwd=readconfig.getpassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@SuppressWarnings("deprecation")
	@Parameters("browser")
	
	@BeforeClass
	public void setup(String bro) {
		
		logger =Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(bro.equals("chrome")) {
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Driver//chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",readconfig.getchromepath());
		driver=new ChromeDriver();
		}
		
		else if(bro.equals("firefox")){
			System.setProperty("webdriver.gecko.driver",readconfig.getfirefoxpath());
			driver=new FirefoxDriver();
		}
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseurl);
		logger.info("url entered");
		
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshot/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomstring() {
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return generatedstring;
	}

     public static String randomnum() {
	    String generatedstring2=RandomStringUtils.randomAlphabetic(4);
	    return generatedstring2;
	
}
			
	public void drawborder(WebElement element, WebDriver driver) {
		JavascriptExecutor js=((JavascriptExecutor)driver);

		js.executeScript("arguments[0].style.border='3px solid red'", element);
		
	}
}
