package seleniumgluecode;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.cucumber.listener.Reporter;

public class test {
	private final WebDriver driver;

    {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");

        ///Map<String, Object> prefs = new HashMap<String, Object>();

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addArguments("--disable-infobars");
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("start-maximized");
        options.addArguments("--disable-extensions");
        driver = new FirefoxDriver(options);
        //driver.get("https://outlook.live.com/owa/");
    }
    
    @Given("^user is  on homepage$")
    public void user_is_on_homepage() throws Throwable {     
        	//System.setProperty("webdriver.gecko.driver","/Users/Documents/geckodriver");
            //driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("http://automationpractice.com/index.php");
        }
        
        @When("^user navigates to Login Page$")
        public void user_navigates_to_Login_Page() throws Throwable {
            driver.findElement(By.linkText("Sign in")).click();
        }
        
        @When("^user enters username and Password$")
        public void user_enters_username_and_Password() throws Throwable {
        	driver.findElement(By.id("email")).sendKeys("blog.cucumber@gmail.com");
            driver.findElement(By.id("passwd")).sendKeys("Cucumber@blog");
            driver.findElement(By.id("SubmitLogin")).click();   
        }
        
        @Then("^success message is displayed$")
        public void success_message_is_displayed() throws Throwable {
        	String exp_message = "Welcome to your account. Here you can manage all   of your personal information and orders.";
        	String actual = driver.findElement(By.cssSelector(".info-account")).getText();
            Assert.assertEquals(exp_message, actual);
            driver.quit();  
        }
        
    	@AfterClass
        public static void writeExtentReport() {
            Reporter.loadXMLConfig(new File("config/report.xml"));
        
        }
   
}