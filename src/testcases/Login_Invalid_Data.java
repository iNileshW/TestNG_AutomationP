package testcases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
  
  @Test (enabled = false)
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
  
  @Test (enabled = false)
  public void popuphandling() {
	  driver.navigate().to("http://www.popuptest.com/goodpopups.html");
	  //When pop opens parent & child window are present after pop up opens
	  //Use switch to go to another pop up window do actions & close it & bring control back to main window by switch
	  //To switch use windowid. Use Window Handler API from Selenium.
	 driver.findElement(By.linkText("Good PopUp #1")).click();
	  Set <String> handler = driver.getWindowHandles();//1 set object of string type given. Set is collection in Java
	  //2 window ids are available in set object
	  //Set object are not stored on based of indexes
	  //So to get values use iterator
	  Iterator <String> it = handler.iterator();
	  String parentwindowid = it.next();
	  System.out.println("parentwindowid is: "+parentwindowid );
	  System.out.println("parent window title is "+driver.getTitle());
	  
	  String childwindowid = it.next();
	  System.out.println("childwindowid ID is: "+childwindowid);
	  driver.switchTo().window(parentwindowid);
	  System.out.println("Parent window title is "+driver.getTitle());
	  driver.switchTo().window(childwindowid);
	  driver.close();
  }
  
  @Test (enabled = false)
  public void config_properties_File_use() throws IOException {
	  //To read the properties file:
	  //Create object of properties class. This is done using properties class of javva Util package
	  Properties prop = new Properties();
	  //Use FileInputStream class to read the config.properties file. Give location of config properties file to tell which file to read
	  FileInputStream ip = new FileInputStream("C:\\Users\\nwairagade\\eclipse-workspace\\TestNG_AutomationP-master\\src\\testcases\\config.properties");
	  //Once the connection is established then load this file:
	  prop.load(ip);
	  //To read the properties:
	  System.out.println(prop.getProperty("name"));
	  String URL = prop.getProperty("url");
	  System.out.println(URL);
	  String browser_name = prop.getProperty("browser");
	  System.out.println(browser_name);
	  
	  if(browser_name.equals("chrome")) {
		  System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		  driver = new ChromeDriver();
	  }
	  
	  else if(browser_name.contentEquals("firefox")) {
		  System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\C:\\Drivers\\geckodriver-v0.20.1-win64\\geckodriver.exe");
		  driver = new FirefoxDriver();
			
	  }
	  
	  else {
		  System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\IEDriverServer_x64_3.12.0\\iedriverserver.exe");
		  driver = new InternetExplorerDriver();		  
	  }
	  driver.navigate().to(URL);
	  //Advantage of config.properties is just change the default data to run any type of browser to use
	  driver.findElement(By.xpath(prop.getProperty("register_xpath"))).click();
	  driver.findElement(By.xpath(prop.getProperty("firstname_xpath"))).sendKeys(prop.getProperty("firstname"));
	  driver.findElement(By.xpath(prop.getProperty("email_xpath"))).sendKeys(prop.getProperty("email"));
	  driver.close();
  }
  
  @Test (enabled = false)
  public void headless_browser() {
	  //HtmlUnitDriver is not of part of Selenium 3.x
	  //To Use download HtmlUnitDriver Jar File from google and htmlunitdriver jar download
	  //3 Advantagess: 1. Testing behind the scene 2. Testing is quick 3. Not suitable for actions class
	  //4. Also called ghost driver	  
	  
	  driver= new HtmlUnitDriver();
	  driver.get("http://wwww.skyscanner.com");
	  System.out.println(driver.getTitle());
	  driver.findElement(By.id("authentication-link")).click();
	  System.out.println(driver.getTitle());
	  driver.close();	  
  }
  
  @Test (enabled= false)
  public void js_executor() throws InterruptedException {
	  WebElement Signin = driver.findElement(By.linkText("Sign in"));
	  flash(Signin,driver);//flash signin
	  drawBorder(Signin,driver);//draw border on signin
	  generateAlert("This is a Test",driver); //Alert for page 
	  clickElementbyJS(Signin,driver); //Click signin
	  refresh_browser_byJS(driver); //Refresh Browser by JS
	  System.out.println(get_TitleBy_JS(driver)); //Get Title of page by JS
	  System.out.println(getPageInnerText_JS(driver)); //Get page inner Text by JS
	  scrollPageDown_JS(driver); // Scroll to bottom of page by JS
	  //WebElement element = driver.findElement(By.xpath(""));
	  driver.navigate().to("https://www.amazon.com");
	  WebElement element = driver.findElement(By.xpath("//*[@id=\"navBackToTop\"]/div/span"));
	  scrollintoView_JS(element, driver);//Scroll to the element. To scroll to a element 
	  
  }
  
  public static void flash(WebElement element, WebDriver driver) throws InterruptedException {
	  JavascriptExecutor js = ((JavascriptExecutor) driver);//Casting driver into javascriptExecutor. Left side has become object
	  //CSS value
	  String backgroundcolor = element.getCssValue("backgrooundcolor");
	  for (int i=0; i<100; i++) {
		  changeColor("rgb(0,200,0)",element, driver);
		  changeColor(backgroundcolor, element, driver);
		  /*generateAlert("There is Bug here", driver);
		  driver.switchTo().alert().dismiss();*/
	  }
	}
  
  public static void changeColor(String Color, WebElement element, WebDriver driver) {
	  JavascriptExecutor js = ((JavascriptExecutor) driver);
	  js.executeScript("arguments[0].style.backgroundColor = '"+Color+"'", element);
	  try {
		  Thread.sleep(20);
	  }
	  catch(InterruptedException e) {		  
		  System.out.println(e);
	  }
  }
  
  public static void generateAlert(String message, WebDriver driver) throws InterruptedException {
	  JavascriptExecutor js = ((JavascriptExecutor) driver);
	  js.executeScript("alert('"+message+"')");
	  Thread.sleep(5000);
	  Alert alert = driver.switchTo().alert();
		System.out.println(alert);
		alert.accept();//alert.dismiss();
  }
  
  public static void drawBorder(WebElement element, WebDriver driver) {
	  JavascriptExecutor js = ((JavascriptExecutor) driver);
	  js.executeScript("arguments[0].style.border = '3px solid red'", element);	  
  }
  
  public static void clickElementbyJS(WebElement element, WebDriver driver) {
	  JavascriptExecutor js = ((JavascriptExecutor) driver);
	  js.executeScript("arguments[0].click();", element);
  }
  
  public static void refresh_browser_byJS(WebDriver driver) {
	  JavascriptExecutor js = ((JavascriptExecutor) driver);
	  js.executeScript("history.go(0);");
  }
  
  public static String get_TitleBy_JS(WebDriver driver) {
	  JavascriptExecutor js = ((JavascriptExecutor) driver);
	  String Title = js.executeScript("return document.title").toString();
	  return Title;
  }
  
  public static String getPageInnerText_JS(WebDriver driver) {
	  JavascriptExecutor js = ((JavascriptExecutor) driver);
	  String PageText = js.executeScript("return document.documentElement.innerText").toString();
	  return PageText;
  }
  
  public static void scrollPageDown_JS(WebDriver driver) {
	  JavascriptExecutor js = ((JavascriptExecutor) driver);
	  js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
  }
  
  public static void scrollintoView_JS(WebElement element, WebDriver driver) {
	  JavascriptExecutor js = ((JavascriptExecutor) driver);
	  js.executeScript("arguments[0].scrollIntoView(true);",element);
  }
 
  @Test(enabled = false)
  public void BrokenLinks() throws MalformedURLException, IOException {
	  driver.navigate().to("https://huxley-uk-and-i.production.sthree-volcanic.com/");
	  //Find all image & links
	  List <WebElement> linklist = driver.findElements(By.tagName("a"));
	  //Add img tags in above list
	  linklist.addAll(driver.findElements(By.tagName("img")));
	  System.out.println("All link count is "+ linklist.size());
	  //Active links with href property
	  List<WebElement> activelinks = new ArrayList<WebElement>();
	  //Iterate LinkList to exclude link/images without href attribute
	  for (int i=0; i<linklist.size();i++) {
		  System.out.println(linklist.get(i).getAttribute("href"));
		  //Not counting null & javascript hrefs
		  if(linklist.get(i).getAttribute("href")!=null && (!linklist.get(i).getAttribute("href").contains("javascript"))) {
			  activelinks.add(linklist.get(i));			  
		  }
	  }
	  //Check to get size of activelink list
	  System.out.println("Active Links Size : " + activelinks.size());
	  
	  //Check the href url with httpconnection api
	  //200 - ok; 404 - Not Found; 500 - Internal Error; 400 - Bad Request
	  for (int i=0; i<activelinks.size();i++) {
		  HttpURLConnection connection = (HttpURLConnection) new URL(activelinks.get(i).getAttribute("href")).openConnection();
		  connection.connect();
		  String response = connection.getResponseMessage(); //return ok/error
		  connection.disconnect();
		  System.out.println(activelinks.get(i).getAttribute("href")+" ---> " + response);
		  
	  }
  }
  
  @Test (enabled = false)
  public void display_enable_selected() {
	  driver.navigate().to("https://www.ebay.co.uk/");
	  driver.findElement(By.xpath("//a[contains(text(),'My eBay')]")).click();
	  driver.findElement(By.id("register-text-block")).click();
	  WebElement element = driver.findElement(By.id("ppaFormSbtBtn"));
	  //isdisplayed method:
	  boolean b = element.isDisplayed();
	  System.out.printf("Condition of is displayed: %b\n",b);
	  //Enter Data
	  driver.findElement(By.name("firstname")).sendKeys("j");
	  driver.findElement(By.name("lastname")).sendKeys("j");
	  driver.findElement(By.name("email")).sendKeys("j@jf.com");
	  driver.findElement(By.name("PASSWORD")).sendKeys("sdlak@");
	  //isenabled method:
	  boolean c = element.isEnabled();
	  System.out.printf("Condition of is enabled: %b\n",c);
	  //isselected method : applicable for checkbox, dropdown, radiobutton
	  WebElement checkbox_element= driver.findElement(By.xpath("//*[@id=\"showPASSWORD\"]/ul/li/span[1]"));
	  boolean d = driver.findElement(By.xpath("//*[@id=\"showPASSWORD\"]/ul/li/span[1]")).isSelected();
	  System.out.printf("Condition of is selected: %b\n",d);
	  //checkbox_element.click();
	  driver.findElement(By.xpath("//*[@id=\"showPASSWORD\"]/ul/li/span[1]")).click();
	  boolean e = driver.findElement(By.xpath("//*[@id=\"showPASSWORD\"]/ul/li/span[1]")).isSelected();
	  System.out.printf("Condition of is selected: %b\n",e);
	  System.out.printf("Condition of is enabled is : %b\n",b);
  }

  @Test (enabled = false)
  //Not complete
  public void dynamictablehanlding() {
	  driver.navigate().to("http://13.59.25.55:8888/chs");
	  //driver.switchTo().frame(0);
	  //driver.findElement(By.xpath("//a[contains(text(),'Inpatients')]")).click();
	  //driver.switchTo().frame(1);
	  driver.findElement(By.name("username")).sendKeys("admin");
	  driver.findElement(By.name("password")).sendKeys("admin");
	  driver.findElement(By.xpath("//input[@type='submit']")).click();
	  driver.switchTo().frame("leftFrame").switchTo();
	  driver.findElement(By.xpath("//a[contains(text(),'Archived Patients')]")).click();
	  driver.findElement(By.xpath("//a[contains(text(),'texthere')]/parent::td//preceding-sibling::td//input[@name='contactid']")).click();
  }
  
  @Test (enabled = true)
  public void dynamicxpath() {
	  driver.navigate().to("http://www.google.com");
	  System.out.println(driver.getTitle());
	  driver.findElement(By.id("lst-ib")).sendKeys("testing");
	  List<WebElement> list = driver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='sbqs_c']"));
	  System.out.println("Total number of suggestions = "+list.size());
	  for(int i=0;i<list.size();i++) {
		  System.out.println(list.get(i).getText());
		  if (list.get(i).getText().contains("Testing circle")) {
			  list.get(i).click();
			  System.out.println(driver.getTitle());
			  break;
		  }
	  }	  
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
