package com.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

  public class loginpage{
	
	WebDriver driver;
	
	public loginpage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name="uid")
	WebElement txtusername;
	
	@FindBy(name="password")
	WebElement txtpassword;
	
	@FindBy(name="btnLogin")
	WebElement btnclick;
	
	@FindBy(xpath="/html/body/div[3]/div/ul/li[15]/a")
	@CacheLookup
	WebElement lnklogout;
	
	
	public void setusername(String uname) {
		 txtusername.sendKeys(uname);
	}
	
	public void setpassword(String pwd) {
		txtpassword.sendKeys(pwd);
	}
	public void clicksubmit() {
		btnclick.click();
	}

	public void clicklogout() {
		lnklogout.click();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
