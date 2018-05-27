package testcases;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
  
  @Test (enabled = true)
  public void mousemovemnet() throws InterruptedException {
	  driver.get("http://www.spicejet.com/");
	  Actions action = new Actions(driver);//Actions class
	  action.moveToElement(driver.findElement(By.linkText("ADD-ONS"))).build().perform(); //build & perform are methods to perform the actions
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Hot Meals")).click();
	  
	  
  }
  
  
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
  }

  @AfterMethod
  public void afterMethod() {
	  //driver.quit();
  }

}
