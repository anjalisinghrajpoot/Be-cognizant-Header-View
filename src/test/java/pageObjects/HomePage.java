package pageObjects;
 
import java.util.ArrayList;
import java.util.List;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
 
public class HomePage extends BasePage {
 
    public WebDriver driver;
    public String headername;
    public String submenu;
    public String submenu1;
    public List<String> values = new ArrayList<String>();
    public Actions d;
 
    // Constructor
    public HomePage(WebDriver driver) {
        super(driver); // Call the constructor of BasePage
        d = new Actions(driver); // Initialize an Actions class instance
    }
 
    // Locators
    @FindBy(css = "span.ms-Button-label")
    List<WebElement> headerOptions; // List of header elements
 
    @FindBy(css = "span.ms-ContextualMenu-itemText")
    List<WebElement> subheaderOptions; // List of sub-header elements (first level)
 
    @FindBy(xpath = "//*[starts-with(@id,'ContextualMenu')]//ul/li//span")
    List<WebElement> subheader1Options; // List of sub-header elements (second level)
 
    // Actions Method
    public List<String> HeaderList() throws InterruptedException {
        Thread.sleep(700); // Wait for 700 milliseconds
 
        for (WebElement element : headerOptions) {
            try {
                headername = element.getText();
                System.out.println("\n" + headername); // Print the header name
                values.add(headername); // Add header name to the list
            } catch (Exception e) {
                e.getMessage(); // Catch exception but don't print a message here
            }
 
            Thread.sleep(300); // Wait for 300 milliseconds
 
            // Click on the header element (except for "be.cognizant")
            if (!headername.equals("be.cognizant")) {
                element.click();
                Thread.sleep(300); // Wait for 300 milliseconds
 
                for (WebElement elements : subheaderOptions) {
                    try {
                        submenu = elements.getText();
                        System.out.println("  " + submenu); // Print the first-level sub-header name
                        values.add(submenu); // Add sub-header name to the list
                    } catch (Exception e) {
                        e.getMessage(); // Catch exception but don't print a message here
                    }
 
                    d.moveToElement(elements).build().perform(); // Hover over the sub-header element
                    Thread.sleep(700); // Wait for 700 milliseconds
 
                    for (WebElement sub : subheader1Options) {
                        try {
                            submenu1 = sub.getText();
                            System.out.println("    " + submenu1); // Print the second-level sub-header name
                            values.add(submenu1); // Add sub-header name to the list
                        } catch (Exception e) {
                            e.getMessage(); // Catch exception but don't print a message here
                        }
                        d.moveToElement(sub).build().perform(); // Hover over the second-level sub-header element
                        Thread.sleep(1000); // Wait for 1000 milliseconds
 
                       
                    }
                }
            }
        }
        return values;
    }
}