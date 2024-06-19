package pageObjects;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class userInformation extends BasePage{
	public WebDriver driver;
	public String name;
	public String email;
	public List<String> values= new ArrayList<String>();
	
	
	//Constructor
	public userInformation(WebDriver driver){
		super(driver);
	}
	
	
	//Locators
		@FindBy(xpath="//div[contains(@id,'O365_MainLink_Me')]//ancestor::button")
		WebElement userbutton;
		@FindBy(id="mectrl_currentAccount_primary")
		WebElement username;
		@FindBy(xpath="//div[@id='mectrl_currentAccount_secondary']")
		WebElement useremail;
		
		
	
	//Action methods
		public void userInfo() throws InterruptedException {
			Thread.sleep(20000);
			userbutton.click();
			Thread.sleep(2000);
			try {
			name = username.getText();
			email = useremail.getText();
			}catch(Exception e) {
				e.getMessage();
			}
			values.add(name);
			values.add(email);
			System.out.println("User name: " + name);
			System.out.println("User email: " + email);
		}
		
		
		
}
