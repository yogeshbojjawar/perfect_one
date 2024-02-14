 package com.testcase;



import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageobject.loginpage;
import com.utilites.Baseclass;

public class TC_login extends Baseclass {
	
	@Test
	public void logintest() throws InterruptedException, IOException {
		
		loginpage lp=new loginpage(driver);
		lp.setusername(uname);
		logger.info("name entered");
		
		lp.setpassword(pwd);
		logger.info("pwd entered");
		
		Thread.sleep(3000);
		
		lp.clicksubmit();
		logger.info("click on login");     
		
		String title=driver.getTitle();
		System.out.println(title);
		if(title.equals("Guru99 Bank Manager HomePage123"))
		{
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
		
		
		
		
//logger.assertLog(false, "something is fishy");
	
	}
	

}
