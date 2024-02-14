package com.utilites;
//////////////Listener class used to generate extent reports
import java.io.File;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest logger;
	

	public void onTestStart(ITestResult testContext) {
		String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timestamp+".html";
		
		htmlreporter= new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/"+ repName); //specify location
		htmlreporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Yogesh");
		
		htmlreporter.config().setDocumentTitle("Internt banking test project");  //Title of report
		htmlreporter.config().setReportName("Functional Test Report");           //name of the report
		htmlreporter.config().setTestViewChartLocation(ChartLocation.TOP);      //location of the chart
		htmlreporter.config().setTheme(Theme.DARK);
		
	}

	public void onTestSuccess(ITestResult tr) {
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
	
	}
	
	public void onTestFailure(ITestResult tr) {
		logger=extent.createTest(tr.getName());    
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenshotpath=System.getProperty("user.dir")+"\\Screenshot\\" +tr.getName()+ ".png";
		
		File f =new File(screenshotpath);
		 
		if(f.exists())
		{
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotpath));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult tr) {
		logger=extent.createTest(tr.getName());  //create new report in th report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext testcontext) {
		
		extent.flush();
		
	}
	

}
