package userDefinedLibraries;

// Importing necessary libraries
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseClass {
	// Declaring WebDriver and Properties as public static so they can be used across all methods in this class
	public static WebDriver driver;	
	public static Properties p;
	
	// File path for storing screenshots
	public static String filepath = ".\\ScreenShot\\";

	// Method to capture screenshot
	public String captureScreen(String tname) throws IOException {
		// Takes a screenshot and stores it in sourceFile
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		// Defines the target file path and renames the source file to the target file
		String targetFilePath=System.getProperty("user.dir")+filepath + tname +".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
			
		// Returns the path of the screenshot file
		return targetFilePath;
	}
	
	// Method to scroll to a specific WebElement
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("window.scrollBy(0,350)", "");
	}
	
	// Method to instantiate the WebDriver based on the browser type
	public static WebDriver driverInstantiate(String browser) throws IOException {
		// Loading properties file
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);

		// Switch case to handle different browser types
		if (browser.equalsIgnoreCase("chrome")) {
		    driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
		    driver = new EdgeDriver();
		} else {
		    System.out.println("No matching browser..");
		}
//		switch(browser.toLowerCase())
//		{
//		case "chrome": driver=new ChromeDriver(); break;
//		case "edge": driver=new EdgeDriver(); break;
//		default: System.out.println("No matching browser..");
//		}
		// Maximizing the browser window and deleting all cookies
		driver.manage().window().maximize();	
		driver.manage().deleteAllCookies();

		// Navigating to the URL specified in the properties file
		driver.get(p.getProperty("URL"));
		return driver;
	}

	// Method to close the WebDriver
	public static void driverTearDown() {
		driver.quit();
	}
}
