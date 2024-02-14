package com.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Readconfig {
	
	Properties pro;       //import from java.util
	
	public Readconfig() {
		
		File src= new File("./Configuration/config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);           //when u have to read the data in read mode then we use fileinputstream
			pro=new Properties();
			pro.load(fis);                                         //load and read the file
			}
		catch(Exception e) {
			System.out.println("Exception is" + e.getMessage());
			
		}
	}
		
		
		public String getappurl() {
			String url=pro.getProperty("baseurl");
			return url;
		}
		 
		public String getusername(){
			String usirname=pro.getProperty("name");
			return usirname;
		}
			
		public String getpassword() {
			String pass=pro.getProperty("pwd");      //they are case sensitive, check before executing
			return pass;
		}
		
		
		public String getchromepath() {
			String chromepath=pro.getProperty("chromepath");
			return chromepath;	
		}
		
		public String getfirefoxpath() {
			String firefox=pro.getProperty("firrfoxx");
			return firefox;	
		}
		
		
	}

	
	
	
	
	
	
	
	
	

