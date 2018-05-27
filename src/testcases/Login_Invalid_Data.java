package testcases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login_Invalid_Data {
	WebDriver driver;
  @Test (enabled = false)
  public void f() throws InterruptedException {
	  //To print Page Title to console
	  String Title = driver.getTitle(); 
		System.out.println(Title);
		driver.findElement(By.linkText("Sign in")).click();
		Title = driver.getTitle();
		System.out.println(Title);
		//Login
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("mymail@aol.com");
		driver.findElement(By.name("passwd")).clear();
		driver.findElement(By.name("passwd")).sendKeys("123456");
		driver.findElement(By.id("SubmitLogin")).click();
		String loginmsg=driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		System.out.println(loginmsg);
		System.out.println(driver.getCurrentUrl());
		//System.out.println(driver.getPageSource());
		assertEquals(loginmsg,"Authentication failed.");
		
		driver.get("https://www.rediff.com");
		driver.findElement(By.xpath("//*[@id=\"signin_info\"]/a[1]")).click();
		//Pop up class handling by alert:
		driver.findElement(By.name("proceed")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		System.out.println(alert);
		String alert_Text = alert.getText();
		alert.accept();//alert.dismiss();
		
		if (alert_Text.contentEquals("org.openqa.selenium.remote.RemoteWebDriver$RemoteAlert@ecfbe91")) {
		System.out.println("Right alert message");
		  } 
		else {
			System.out.println("Wrong alert message");
			}
		
		//File Upload Handling for type = file
		driver.get("http://html.com/input-type-file/");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"om-lightbox-postal-optin-wrap\"]/a")).click();
		//Alert alert1 = driver.switchTo().alert();
		Thread.sleep(4000);
		//alert1.dismiss();
		
		driver.findElement(By.xpath("//*[@id=\"fileupload\"]")).sendKeys("C:\\Users\\nwairagade\\Downloads\\fmw_12213_readme.html");//location of file path
		Thread.sleep(4000);
		
		}
  
  @Test (enabled = false)
  public void mousemovemnet() throws InterruptedException {
	  driver.get("http://www.spicejet.com/");
	  Actions action = new Actions(driver);//Actions class
	  action.moveToElement(driver.findElement(By.linkText("ADD-ONS"))).build().perform(); //build & perform are methods to perform the actions
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Hot Meals")).click();
	  
	  
  }
  
  @Test (enabled = false)
  public void dragdrop() {
	  driver.get("http://jqueryui.com/droppable");
	  //single click-move-release by single click
	  //Frame identification by right click - view pagesoruce, search for frame. If iFrame tag is available
	  //To switch to frame where operation to be performed
	  driver.switchTo().frame(0);//0 is for 1 frame if more frames then put respective number
	  Actions action = new Actions(driver);//Actions class
	  action.clickAndHold(driver.findElement(By.xpath("//*[@id=\"draggable\"]")))
	  	.moveToElement(driver.findElement(By.xpath("//*[@id=\"droppable\"]")))
	  	.release()
	  	.build()
	  	.perform();
	    
  }
  
  @Test (enabled = true)
  public void ImplicitWait() {
	  driver.navigate().to("http://www.half.ebay.com");
	  //TImeout for pageload....for heavier pages
	  driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	  //explicitwait - To avoid Thread Sleep instead put dynamic wait
	  //explicit wait is not generic method
	  
	  driver.navigate().to("http://amazon.co.uk");
	  ClickOn(driver,driver.findElement(By.xpath("//*[@id=\"nav-link-yourAccount\"]/span[2]")),20);
	  
  }
  
  //Method for Explicit Wait
  public static void ClickOn(WebDriver driver, WebElement locator, int timeout) {
	  //WebDriverWait is a class in selenium here we are creating an object of this class.  
	  new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
	  	.until(ExpectedConditions.elementToBeClickable(locator));
  }
	  
  
  @Test (enabled = false)
  public void DynamicXpath() {
	  //Don't use absolute xpath rather create own xpath
	  //1.Xpath starts with "//" 2. Find HTML tag of element i.e. after "<" 3. After tag followed by "[]" 4. Check different properties avlbl 
	  //for element e.g. for classname write "@class='' other property can be name or also can use function called "contains(@class,'actextbox')"
	  //contains is very powerful& can write any propoerty in key value form
	  //When id is dynamic, then use dynamic use contains 
	  driver.get("http://www.half.ebay.com");
	  driver.findElement(By.xpath("//input[@type='text']")).clear();
	  driver.findElement(By.xpath("//input[@class='gh-tb ui-autocomplete-input']")).sendKeys("Hi");
	  driver.findElement(By.xpath("//input[contains(@class,'gh-tb ui-autocomplete-input')]")).clear();
	  driver.findElement(By.xpath("//input[contains(@id,'gh-ac')]")).click(); // if id is dynamic then write common part & then put "_" for 
	  //dynamic part
	  
	  //Another e.g for dynamic element use starts-with or ends-with
	  /*driver.findElement(By.xpath("//input[starts-with(@id,'gh_')]")).click();
	  driver.findElement(By.xpath("//input[ends-with(@id,'_ac')]")).click();*/
	  
	  //Creating xpath for link:
	  //For links the html tag is always "a" tag. Here text is function
	  driver.findElement(By.xpath("//a[contains(text(),'Sell')]")).click();
	  //Customized xpath very powerful & fast
	  
  }
  
  @Test (enabled = false)
  public void CountLinks() {
	   //To count number of elements on page
	  driver.get("http://www.ebay.co.uk");
	  //All links have <a> html tag:
	  List <WebElement> linklist = driver.findElements(By.tagName("a"));
	  //To get size of List above
	  System.out.println(linklist.size());
	  //To get text of each link:
	  for (int i=0; i<linklist.size();i++) {
		  String linktext = linklist.get(i).getText();
		  System.out.println(linktext);
	  }		  
	  //For buttons use <button>	  
  }
  
  @Test (enabled = false)
  public void pagenavigation() throws InterruptedException {
	  driver.navigate().to("http://www.google.com"); //For external URL from current url then use navigate-to
	  Thread.sleep(2000);
	  driver.navigate().back();
	  Thread.sleep(2000);
	  driver.navigate().forward();
	  driver.navigate().refresh();
	  
  }
  
  @Test (enabled = false)
  public void screenshot() throws IOException {
	  driver.navigate().to("https://www.google.com");
	  //Take screenshot & Store as a file format
	  //getScreenshotAs is a method. This will give a file object but this is top casting into TakeScreenshot class
	  //Converting driver into takescreenshot interface 
	  File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
	  //Copy the screenshot to desired location using copyfile//method
	  //Copying file from src to said location of project folder
	  FileUtils.copyFile(src, new File ("C:\\Users\\nwairagade\\eclipse-workspace\\TestNG_AutomationP-master\\google.png"));
	  
  }
  
  
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com");
		driver.manage().deleteAllCookies();
		//For all elements - Global wait for all elements on page
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		driver.manage().window().maximize();
		
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
