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
import java.util.Random;
import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder; 
import org.junit.jupiter.api.MethodOrderer; 
import static org.junit.jupiter.api.Assertions.*; 


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestFour {
	private static ChromeDriver driver;
	private static Wait<WebDriver> wait;
	private static String email;
	
	@BeforeAll
	public static void generateEmail() {
		Random rand = new Random();
		int randEmailNum = rand.nextInt(10000);
		email = randEmailNum + "someemail" + randEmailNum + "@email.com";
	}
	
	@BeforeEach
    public void createDriver() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));	
    }
	
	@AfterEach
    public void quit() {
		driver.quit();
    }
	
	private static void baseTest(String dataPath) throws InterruptedException {
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/login']"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys(email);
		
		driver.findElement(By.id("Password")).sendKeys("password1");
		
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/digital-downloads']"))).click();
		

		
		try {
		      File myObj = new File(dataPath);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine().toLowerCase().replaceAll(" ", "-");        
		        
		        if(data.contentEquals("3rd-album")) {
		        	data = "album-3";
		        }
		        
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/" + data +"']"))).click();
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 add-to-cart-button']"))).click();
		        
		        Thread.sleep(1000);
		        driver.navigate().back();
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='cart-label']"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("termsofservice"))).click();
		
		driver.findElement(By.xpath("//button[@class='button-1 checkout-button']")).click();

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("BillingNewAddress.City"))).sendKeys("ASDF");
			
			driver.findElement(By.name("BillingNewAddress.Address1")).sendKeys("ASDF");
			
			driver.findElement(By.name("BillingNewAddress.ZipPostalCode")).sendKeys("1234");
			
			driver.findElement(By.name("BillingNewAddress.PhoneNumber")).sendKeys("1234");
			
			WebElement selectElement = driver.findElement(By.id("BillingNewAddress_CountryId"));
	        Select select = new Select(selectElement);
	        select.selectByValue("1");
		}
		catch(Exception e) {
			
		}
		
		 
        driver.findElement(By.xpath("//input[@class='button-1 new-address-next-step-button']")).click();
		
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 payment-method-next-step-button']"))).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 payment-info-next-step-button']"))).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 confirm-order-next-step-button']"))).click();
        
        
        
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='title']")));
        String result = title.getText();
        
        
        assertTrue(result.contentEquals("Your order has been successfully processed!"));
	}

	@Test
	@Order(1)
	public void createAccount() throws InterruptedException {
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/login']"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 register-button']"))).click();
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gender-male"))).click();
		
		driver.findElement(By.id("FirstName")).sendKeys("firstname");
		
		driver.findElement(By.id("LastName")).sendKeys("lastname");
		
		driver.findElement(By.id("Email")).sendKeys(email);
		
		driver.findElement(By.id("Password")).sendKeys("password1");
		
		driver.findElement(By.id("ConfirmPassword")).sendKeys("password1");
		
		driver.findElement(By.id("register-button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 register-continue-button']"))).click();
	}
	
	@Test
	@Order(2)
	public void test() throws InterruptedException {
		baseTest("C:\\Users\\viliu\\eclipse-workspace\\TestOne\\src\\main\\java\\firstTest\\data1.txt");
	}
	
	@Test
	@Order(3)
	public void testSec2() throws InterruptedException {
		baseTest("C:\\Users\\viliu\\eclipse-workspace\\TestOne\\src\\main\\java\\firstTest\\data2.txt");
		
	}
}
