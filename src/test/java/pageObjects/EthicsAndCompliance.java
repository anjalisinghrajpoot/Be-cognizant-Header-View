package pageObjects;
 
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
import userDefinedLibraries.BaseClass;
 
public class EthicsAndCompliance extends BaseClass {
 
    public WebDriver driver;
    public Actions action;
    public String name1;
    public String name2;
    public boolean status, status1;
    HomePage hp;
    public List<String> values = new ArrayList<String>();
    public List<String> values1 = new ArrayList<String>();
 
    // Constructor
    public EthicsAndCompliance(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
 
    // Locators
    @FindBy(name = "Corporate Functions")
    WebElement click1;
 
    @FindBy(xpath = "//span[normalize-space()='Legal & Corporate Administration']")
    WebElement option1;
 
    @FindBy(xpath = "//span[normalize-space()='Ethics and Compliance']")
    WebElement click2;
 
    @FindBy(id = "QuicklinksItemTitle")
    List<WebElement> Options;
 
    @FindBy(css = "div.ck-content")
    WebElement pageInfo;
 
    @FindBy(xpath = "//span[normalize-space()='Resources / Links']")
    WebElement resource;
 
    @FindBy(name = "Hide header and navigation")
    WebElement hideHeader;
 
    // Action Methods
    public boolean Status() throws InterruptedException {
        // Click on "Corporate Functions" and wait
        click1.click();
        Thread.sleep(1000);
 
        // Create an Actions class instance
        action = new Actions(driver);
 
        // Hover over "Legal & Corporate Administration" sub-header
        action.moveToElement(option1).build().perform();
        Thread.sleep(1000);
 
        // Check if "Ethics and Compliance" is visible and return status
        status = click2.isDisplayed();
        return status;
    }
 
    public void navigateEthic() throws InterruptedException {
        // Click on "Ethics and Compliance" and wait
        click2.click();
        Thread.sleep(1000);
    }
 
    public String validateResouceAndLink() {
        // Scroll to the "Resources / Links" element
        scrollToElement(resource);
 
        // Click on "Hide header and navigation" (optional)
        hideHeader.click();
 
        try {
            // Wait for 2 seconds and capture text of "Resources / Links"
            Thread.sleep(2000);
            name2 = resource.getText();
            return name2;
        } catch (Exception e) {
            // If exception occurs, return the exception message
            return e.getMessage();
        }
    }
 
    public void printInformation() throws InterruptedException {
        // Print the number of search options displayed
        System.out.println("\n" + "Number of search options displayed: " + Options.size());
 
        try {
            // Iterate through each search option and print its text
            for (WebElement option : Options) {
                System.out.println(option.getText());
                values.add(option.getText());
            }
        } catch (Exception e) {
            // Catch exception but don't print a message here
            e.getMessage();
        }
    }
 
    public boolean pageInform() {
        // Check if "pageInfo" element is displayed and return status
        status1 = pageInfo.isDisplayed();
        return status1;
    }
 
    public String pageInformation() {
        try {
            // Capture text of "pageInfo" element
            values1.add(pageInfo.getText());
            System.out.println("\n" + pageInfo.getText());
            return pageInfo.getText();
        } catch (Exception e) {
            // If exception occurs, return the exception message
            return e.getMessage();
        }
    }
}