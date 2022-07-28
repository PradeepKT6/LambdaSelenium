package com.lambdatest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGTodo3 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @Parameters({ "browser" })
	@BeforeMethod
	public WebDriver initializeDriver(String browser) throws IOException {
		System.out.println("Browser name is:" + browser);
		String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        
        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName("Windows 10")
        browserOptions.setBrowserVersion("102.0")
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "tpradeephyr2009");
        ltOptions.put("accessKey", "tyNlaFuRMbVz5ZdbbB9ZtTGt6L0Nkn5t7QBPVRr22aXZrYKdCj");
        ltOptions.put("geoLocation", "IN");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("timezone", "UTC+05:30");
        ltOptions.put("build", "SeleniumSamle");
        ltOptions.put("project", "Selenium101");
        ltOptions.put("console", "false");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5);
		driver.manage().window().maximize();
		return driver;

	}
    
    @Test(priority = 1)
 	public void simpleFormDemo() throws IOException, Throwable {
 		System.out.println(driver);
 		System.out.println("Hello");
 		String EnteredText = "Welcome to LambdaTest";

 		// driver=base.initializeDriver("browser");
 		driver.get("https://www.lambdatest.com/selenium-playground/");
 		try {
 			driver.findElement(By.xpath("//a[text()='Simple Form Demo']")).click();
 		} catch (Exception e) {
 			e.getMessage();
 		}
 		String actualUrl = driver.getCurrentUrl();
 		if (actualUrl.contains("simple-form-demo")) {
 			System.out.println("Contains Expected URL");

 		} else {
 			System.out.println("UnExected URL");
 		}

 		driver.findElement(By.id("user-message")).sendKeys(EnteredText);
 		driver.findElement(By.id("showInput")).click();
 		System.out.println(driver.findElement(By.id("message")).getText().equals(EnteredText));
 		System.out.println("Scenario1 working as Expected for");

 	}

 	@Test(priority = 2)
 	public void dragAndDropSliders1() throws IOException, Throwable {
 		System.out.println(driver);
 		String expectedValue = "95";
 		// driver=base.initializeDriver("browser");
 		driver.get("https://www.lambdatest.com/selenium-playground/");
 		driver.findElement(By.xpath("//a[text()='Drag & Drop Sliders']")).click();
 		WebElement slider = driver.findElement(By.xpath("(//input[@class='sp__range'])[3]"));
 		Actions action = new Actions(driver);
 		action.dragAndDropBy(slider, 102, 0).build().perform();
 		String actualValue = driver.findElement(By.xpath("//output[@id='rangeSuccess']")).getText();
 		SoftAssert Assert = new SoftAssert();
 		Assert.assertEquals(expectedValue, actualValue);
 		Thread.sleep(4000);
 		System.out.println("Scenario2 working as Expected");
 	}

 	@Test(priority = 3)
 	public void inputFormSubmit() throws Throwable {
 		// driver=base.initializeDriver("browser");
 		String originalTitle = "Please fill out this field";
 		String expectedTitle = "Please fill in this field";

 		driver.get("https://www.lambdatest.com/selenium-playground/");
 		driver.findElement(By.xpath("//a[text()='Input Form Submit']")).click();
 		driver.findElement(By.xpath("//button[@type='submit']")).click();
 		SoftAssert Assert = new SoftAssert();
 		Assert.assertEquals(originalTitle, expectedTitle);
 		System.out.println("Assert is working as Expected");
 		driver.findElement(By.id("name")).sendKeys("HEllo");
 		driver.findElement(By.id("inputEmail4")).sendKeys("HEllo@123.com");
 		driver.findElement(By.id("inputPassword4")).sendKeys("HEllo@123");
 		driver.findElement(By.id("company")).sendKeys("HEllo123");
 		driver.findElement(By.id("websitename")).sendKeys("HEllo123.com");
 		WebElement country = driver.findElement(By.name("country"));
 		country.click();
 		String expectedCountry = "United States";
 		Select scountry = new Select(country);
 		scountry.selectByVisibleText(expectedCountry);
 		driver.findElement(By.id("inputCity")).sendKeys("MyCity");
 		driver.findElement(By.id("inputAddress1")).sendKeys("MyAddress1");
 		driver.findElement(By.id("inputAddress2")).sendKeys("MyAddress2");
 		driver.findElement(By.id("inputState")).sendKeys("MyState");
 		driver.findElement(By.id("inputZip")).sendKeys("12345");
 		driver.findElement(By.xpath("//button[@type='submit']")).click();
 		Thread.sleep(3);
 		String expSuccessMsg = "Thanks for contacting us, we will get back to you shortly.";
 		String actSuccessMsg = driver.findElement(By.xpath("//p[@class='success-msg hidden']")).getText();
 		Assert.assertEquals(actSuccessMsg, expSuccessMsg);
 		System.out.println("Success Message is as Expected");
 		System.out.println("Scenario3 working as Expected");

 	}
    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}