package com.testcase;



import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobject.loginpage;
import com.utilites.Baseclass;
import com.utilites.XLUtils;

public class TC_loginDDT extends Baseclass {
	
	@Test(dataProvider="logindata")
	public void loginDDT(String user, String pwd) throws InterruptedException {
		loginpage lp=new loginpage(driver);
		lp.setusername(user);
		logger.info("user name provided");
		
		lp.setpassword(pwd);
		logger.info("pwd provided");
		lp.clicksubmit();
		
		Thread.sleep(3000);
		
		if(isAlertpresent()==true) {
			driver.switchTo().alert().accept();   //close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("login failed");
		}
		else {
			Assert.assertTrue(true);
			logger.info("login pass zal re");
			lp.clicklogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();   //close logout alert
			driver.switchTo().defaultContent();
			
		}
	}
	
	public boolean isAlertpresent() {     //user defined method created to check alert is present or not
		try {
		driver.switchTo().alert();
		return true;
				}
		catch(NoAlertPresentException e) {
			return false;
		}
		
	}

	
	@DataProvider(name="logindata")                      //we have give name to dataprovider         
	String [][] getdata() throws IOException, InterruptedException {
		
	String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testdata/LoginData.xlsx";
		//String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		Thread.sleep(3000);
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum; i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);  //1,0
			}
		}
		return logindata;
		
		
		
		
		
	}
	
	
	
	
	
}
