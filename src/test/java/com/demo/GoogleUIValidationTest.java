package com.demo;

import com.demo.utils.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.demo.utils.ScreenshotUtil;

import java.time.Duration;

public class GoogleUIValidationTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "uiData")
    public Object[][] getData() {
        return ExcelReader.getUIValidationData("Sheet1");
    }

    @Test(dataProvider = "uiData")
    public void validateGoogleSearchUI(String testCaseName, String element, String action, String xpath, String report) {
	
		driver.get("https://www.google.com");
        try {
			WebElement webElement = null;

			// Only try to locate the element if action is not "takeshot"
			if (!action.equalsIgnoreCase("takeshot")) {
				webElement = driver.findElement(By.xpath(xpath));
			}

            switch (action.toLowerCase()) {
		
					
                case "displayed":
                    Assert.assertTrue(webElement.isDisplayed(), report + " - element is not displayed.");
                    System.out.println(report + ": verified");
                    break;

                case "enabled":
                    Assert.assertTrue(webElement.isEnabled(), report + " - element is not enabled.");
                    System.out.println(report + ": verified");
                    break;

                case "click":
                    webElement.click();
                    System.out.println(report + ": Clicked");
                    break;

                case "sendkeys":
                    webElement.sendKeys("Testing is in progress - typing ...... : Please wait");
                    System.out.println(report + ": Input sent");
					try {
						Thread.sleep(2000); // wait before screenshot
						ScreenshotUtil.takeScreenshot(driver, testCaseName); // Take screenshot on success
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                    break;
					
				
				case "takeshot":
				
					try {
						Thread.sleep(2000); // wait before screenshot
						ScreenshotUtil.takeScreenshot(driver, testCaseName); // Take screenshot on success
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;

                default:
                    System.out.println("Unknown action for element: " + element + " | Action: " + action);
            }
			

        } catch (Exception e) {
            System.out.println("Test Failed for: " + testCaseName);
            e.printStackTrace();
            Assert.fail("UI validation failed due to: " + e.getMessage());
        }
		
		
    }
}
