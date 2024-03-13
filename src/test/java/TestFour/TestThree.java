package TestFour;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*; 



public class TestThree {
	public static void main(String[] args) throws InterruptedException {
		//testOne();

		testTwo();
	}
	
	private static void testOne() {
		ChromeDriver driver = new ChromeDriver();
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.get("https://demoqa.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Widgets')]"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Progress Bar')]"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startStopButton"))).click();
	
		wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[@class='progress-bar bg-success']"), "aria-valuenow", "100"));
		
		driver.findElement(By.xpath("//button[@class='mt-3 btn btn-primary']")).click();
		
		WebElement progressBar = driver.findElement(By.xpath("//div[@role='progressbar']"));
        String progress = progressBar.getText();
        
        try {
        	assertTrue(progress.contentEquals("0%"));
        } finally {
        	driver.quit();
        }
	}
	
	private static void testTwo() throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.get("https://demoqa.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Elements')]"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Web Tables')]"))).click();

		WebElement nextButton = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
		
		while(!nextButton.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addNewRecordButton"))).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys("name");
	
			driver.findElement(By.id("lastName")).sendKeys("lastName");
			
			driver.findElement(By.id("userEmail")).sendKeys("a@a.com");
			
			driver.findElement(By.id("age")).sendKeys("1");
			
			driver.findElement(By.id("salary")).sendKeys("0");
			
			driver.findElement(By.id("department")).sendKeys("department");
			
			driver.findElement(By.id("submit")).click();
		}
				
		try {
			Thread.sleep(2000);
			
			nextButton.click();

			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-record-11"))).click();
			
			WebElement currPage =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='jump to page']")));
			WebElement totalPages =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='-totalPages']")));
			
			System.out.println(currPage.getAttribute("value"));
			System.out.println(currPage.getAttribute("value").contentEquals("1"));
			System.out.println(totalPages.getText());
			System.out.println(totalPages.getText().contentEquals("1"));
			
			assertTrue(currPage.getAttribute("value").contentEquals("1"));
			assertTrue(totalPages.getText().contentEquals("1"));
        	
        } finally {
        	driver.quit();
        }
	}
}
