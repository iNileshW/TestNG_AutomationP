package testcases;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login_Invalid_Data {
	WebDriver driver;
  @Test
  public void f() {
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
		System.out.println(driver.getPageSource());
		assertEquals(loginmsg,"Authentication failed.");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
