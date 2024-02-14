package com.testcase;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.devtools.v94.performancetimeline.model.LargestContentfulPaint;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageobject.Addcustomerpage;
import com.pageobject.loginpage;
import com.utilites.Baseclass;

public class TC_addcustomerpage extends Baseclass {
	
	@Test
	public void addnewcustomer() throws InterruptedException, IOException {
		loginpage lp=new loginpage(driver);
		lp.setusername(uname);
		lp.setpassword(pwd);
		lp.clicksubmit();
		
		Thread.sleep(3000);
		
		Addcustomerpage addcust= new Addcustomerpage(driver);
		addcust.clickAddNewCustomer();
		logger.info("providing customer details....");
		
		addcust.custname("yogesh");
		addcust.custgender("male");
		 Thread.sleep(5000);
		 
		 addcust.custaddress("gallihoon");
		 addcust.custcity("tula");
		 addcust.custstate("MH");
		 addcust.custpinno("245466");
		 addcust.custtelephoneno("9898989898");	 
		 
		 String email=randomstring()+"@gmail.com";
		 addcust.custemailid(email);
		 
		 addcust.custpassword("abcdefghu");
			addcust.custdob("10","15","1985");

		 Thread.sleep(3000);

		 addcust.custsubmit();
		 
		 Thread.sleep(3000);
		 
		 logger.info("validation started.......");
		 
			boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		 logger.info("validation of pageresource.......");

		 if(res==true) {
			 Assert.assertTrue(true);
			 logger.info("test case passed......");
		 }
		 else {
			 logger.info("test case failed.......");
			 captureScreen(driver, "addNewCustomer");
			 Assert.assertTrue(false);
		 }
	}
	
	
	

}
