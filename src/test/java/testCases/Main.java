package testCases;

// Importing necessary libraries
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.EthicsAndCompliance;
import pageObjects.HomePage;
import pageObjects.userInformation;
import userDefinedLibraries.BaseClass;

public class Main extends BaseClass{
	// Declaring WebDriver and page objects
	WebDriver driver;
	EthicsAndCompliance ec;
	HomePage hp;
	userInformation ui;
	
	// Declaring lists to store values
	List<String> listnames,listnames1,listnames2,listnames3;
	
	// Declaring logger to log information
	public Logger logger=LogManager.getLogger(this.getClass());
	
	// Method to set up the WebDriver before tests
	@BeforeClass(groups= {"sanity","functional","master"})
	@Parameters({"browser"})
	void setUp(@Optional("chrome")String browertype) {
		logger.info("**** Starting Test Cases ******");
		try {
			// Instantiating the WebDriver
			driver=BaseClass.driverInstantiate(browertype);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Instantiating page objects
		hp =new HomePage(driver);
		ec=new EthicsAndCompliance(driver);
	}
	
	// Test method to verify user information
	@Test(priority=1, groups = {"sanity","functional","master"})
	void testUserInfo() throws InterruptedException {
		logger.info("Verify the user Information");
		ui=new userInformation(driver);
		ui.userInfo();
		listnames=ui.values;
	}
	
	// Test method to get header and subheader information
	@Test(priority=2, groups = {"sanity", "master"})
	void testHeaderList() throws InterruptedException {
		logger.info("Get all Header and  all sub header information and capture each header information");
		hp =new HomePage(driver);
		hp.HeaderList();
		listnames1=hp.values;
	}
	
	// Test method to validate visibility of Ethics & Compliance
	@Test(priority=3, groups = {"functional", "master"})
	void testEthicsAndComplianceVisibility() throws InterruptedException {
		logger.info("Validate Ethics & Compliance is visible in Corporate Function ̶̶> Legal & Corporate affairs header");
		try {
		    if(ec.Status()) {
		        logger.info("Test Passed");
		        Assert.assertTrue(true, "Ethics & Compliance is visible");
		    } else {
		        logger.error("Test Failed");
		        Assert.fail("Ethics & Compliance is not visible");
		    }
		}
		catch(Exception e) {
		    logger.error("Test Failed");
		    Assert.fail("An exception occurred");
		}
	}
	
	// Test method to navigate to Ethics & Compliance page
	@Test(priority=4,dependsOnMethods= {"testEthicsAndComplianceVisibility"}, groups = {"functional", "master"})
	void testnavigation() throws InterruptedException {
		logger.info("Navigate to Ethics & Compliance page");
		ec=new EthicsAndCompliance(driver);
		ec.navigateEthic();
	}
	
	// Test method to validate Resources/Link in Ethics & Compliance page
	@Test(priority=5,dependsOnMethods= {"testnavigation"}, groups = {"functional", "master"})
	void testResourcesOrLinkValidity() {
		logger.info("Validate Resources/Link in Ethics & Compliance page");
		try {
			if(ec.validateResouceAndLink().equals("Resources / Links")) {
				logger.info("Test Passed");
				Assert.assertTrue(true, "Resources / Links is available");
			}else {
				logger.error("Test Failed");
				Assert.fail("Resources / Links is not available");
			}
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			Assert.fail();
		}
	}

	
	// Test method to print information of Resources/Link in Ethics & Compliance page
	@Test(priority=6,dependsOnMethods= {"testnavigation","testResourcesOrLinkValidity"}, groups = {"functional", "master"})
	void testprintInformation() throws InterruptedException {
		logger.info("Print all information of Resources/Link in Ethics & Compliance page");
		ec.printInformation();
		listnames2=ec.values;
	}
	
	// Test method to validate Ethics & Compliance page information
	@Test(priority=7,dependsOnMethods= {"testnavigation"},groups = {"functional", "master"})
	void testPageInformationValidity() {
		logger.info("Validate Ethics & Compliance page information");
			try {
			    if(ec.pageInform()) {
			        logger.info("Test Passed");
			        Assert.assertTrue(true, "Page Information is available");
			    } else {
			        logger.error("Test Failed");
			        Assert.fail("Page Information is not available");
			    }
			}
		catch(Exception e)
		{
			logger.error("Test Failed");
			Assert.fail();
		}
	}
	
	// Test method to print Ethics & Compliance page information
	@Test(priority=8,dependsOnMethods= {"testnavigation","testPageInformationValidity"}, groups = {"functional", "master"})
	void testPagetInformation(){
		logger.info("Print Ethics & Compliance page information");
		ec.pageInformation();
		listnames3=ec.values1;
		
	}
	
	// Method to tear down the WebDriver after tests
	@AfterClass(groups= {"sanity","functional","master"})
	void tearDown() {
		try {
			// Putting data into Excel
			utilities.ExcelUtility.putData(listnames,listnames1,listnames2,listnames3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Tearing down the WebDriver
		BaseClass.driverTearDown();
		logger.info("**** Ending Test Cases ******");
	}
}
