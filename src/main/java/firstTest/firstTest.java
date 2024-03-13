package firstTest;

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

public class firstTest {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Libs\\chromedriver-win64\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));

		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		//gift cards
		driver.findElement(By.xpath("//a[@href='/gift-cards']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//span[text()>99]/following::input")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("giftcard_4_RecipientName"))).sendKeys("name one");
		driver.findElement(By.id("giftcard_4_SenderName")).sendKeys("name two");
		
		WebElement qty = driver.findElement(By.id("addtocart_4_EnteredQuantity"));
		qty.clear();
		qty.sendKeys("5000");
		
		driver.findElement(By.id("add-to-cart-button-4")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("add-to-wishlist-button-4")).click();
		
		//jewelry
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//a[@href='/jewelry']")).click();
		driver.findElement(By.xpath("//a[@href='/create-it-yourself-jewelry']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product_attribute_71_9_15")));
		
		Select material = new Select(driver.findElement(By.id("product_attribute_71_9_15")));
		material.selectByValue("47");
		
		driver.findElement(By.id("product_attribute_71_10_16")).sendKeys("80");
		driver.findElement(By.id("product_attribute_71_11_17_50")).click();
		
		WebElement qtyJewelry = driver.findElement(By.id("addtocart_71_EnteredQuantity"));
		qtyJewelry.clear();
		qtyJewelry.sendKeys("26");
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("add-to-cart-button-71")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("add-to-wishlist-button-71")).click();
		
		//wishlist
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//a[@href='/wishlist']")).click();
		
		Thread.sleep(1000);
		
		List<WebElement> addToCartButtons = driver.findElements(By.name("addtocart"));

		for (WebElement button : addToCartButtons) {
			button.click();
		}
		
		driver.findElement(By.name("addtocartbutton")).click();
		
		//subTotal price
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//a[@href='/cart']")).click();
		
		Thread.sleep(1000);
		
		WebElement subtotal = driver.findElement(By.className("product-price"));
		
		String subtotalText = subtotal.getText();

		double actualSubtotal = Double.parseDouble(subtotalText);

		double expectedSubtotal = 1002600.00;
		
		if(actualSubtotal == expectedSubtotal) {
			System.out.println("test passed successfully");
		} else {
			System.out.println("test failed\nexpectedSubtotal = " + expectedSubtotal + "\nactualSubtotal = " + actualSubtotal);
		}
		
		driver.close();
	}
}
