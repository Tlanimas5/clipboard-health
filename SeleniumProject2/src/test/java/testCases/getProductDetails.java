package testCases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.Capabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class getProductDetails {
	public static void main(String[] args) throws InterruptedException {
		
		//chrome driver setup
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//maximize the browser window
		driver.manage().window().maximize();
		
		//navigate to the page
		driver.get("https://www.amazon.in/");
		
		//-------------------------------------POM 1----------------------------------
		//verify user is on the desired page and elements have loaded successfully
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-logo-sprites")));
		
		String pageTitle = driver.getTitle();
		assert pageTitle.matches("Amazon.in");
		
		//verify hamburger icon is present and open menu
		WebElement pageMenu = driver.findElement(By.id("nav-hamburger-menu"));
		wait.until(ExpectedConditions.elementToBeClickable(pageMenu));
		pageMenu.click();
		
		
		//verify menu is open and navigate to the brand section
		//WebElement CusomerName = driver.findElement(By.id("hmenu-customer-name"));
		WebElement customerName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hmenu-customer-name")));
		String name = customerName.getText();
		assert name == "Hello, Sign in";
		
		//scroll TV, Appliances, Electronics into view and click
		WebElement appliances = driver.findElement(By.linkText("TV, Appliances, Electronics"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", appliances);
		wait.until(ExpectedConditions.elementToBeClickable(appliances));
		appliances.click();
		
		//verify user can view main menu 
		WebElement mainMenu = driver.findElement(By.xpath("//*[text()='main menu']"));
		mainMenu.isDisplayed();
		String newline = System.lineSeparator();
		System.out.print("User is on Menu"+ newline);
		
		//use scroll to view function in case the location of "Television" is moved out of view
		WebElement televisions = driver.findElement(By.linkText("Televisions"));
		js.executeScript("arguments[0].scrollIntoView();", televisions);
		wait.until(ExpectedConditions.elementToBeClickable(televisions));
		televisions.click();
		
		//---------------------------------POM 2------------------------------------------
		//Verify user is on page with Televisions
		String televisionPageTitle = driver.getTitle();
		assert televisionPageTitle.matches("Buy the latest LED TVs, 4K TVs and Android TVs online at Best Prices in India-Amazon.in | Shop by size, price, features and more");
		
		//Navigate to a value and select one option on the filter
		String nameOfBrand= "Samsung";
		WebElement brand = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='a-size-base a-color-base'][normalize-space()='"+nameOfBrand+"']")));
		js.executeScript("arguments[0].scrollIntoView();", brand);
		brand.click();
		System.out.print("Validated "+nameOfBrand+" is clicked"+newline);
		
		
		WebElement brandChecked = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='a-size-base a-color-base a-text-bold'][normalize-space()='"+nameOfBrand+"']")));
		js.executeScript("arguments[0].scrollIntoView();", brandChecked);
		assert brandChecked.isSelected();
		System.out.print("validated "+nameOfBrand+" is checked"+newline);
		
		//select value from dropdown and verify dropdown value
		Select sortBy = new Select(driver.findElement(By.name("s")));
		sortBy.selectByVisibleText("Price: High to Low");
		
		WebElement sortByValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("a-dropdown-prompt")));
		String value = sortByValue.getText();
		assertTrue(value.contains("Price: High to Low"));
		
		
		//Verify Results are displayed and selecting second item from result
		WebElement results = driver.findElement(By.xpath("//span[@class='a-size-medium-plus a-color-base a-text-normal']"));
		String resultsLabel = results.getText();
		assert resultsLabel == "RESULTS";
		WebElement secondItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[3]/div/div/div/div/div[2]/div[1]/h2/a")));
		secondItem.click();
		
		//------------------------------------------POM 3---------------------------------------------

		//Storing the number of windows opened
		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		//switch to the second tab
	    driver.switchTo().window(newTb.get(1));
		
		//Get aboutDetails of the item selected
		WebElement aboutTheItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='feature-bullets']/h1")));
		js.executeScript("arguments[0].scrollIntoView();", aboutTheItem);
		assert aboutTheItem.isDisplayed();
		
		List<WebElement> productDetails = driver.findElements(By.xpath("//*[@id='feature-bullets']/ul"));
		for (WebElement eachDetail : productDetails )
		{
			System.out.print(eachDetail.getText());
		}
		//driver.close();
		driver.quit();
	}

	private static void assertTrue(boolean contains) {
		// TODO Auto-generated method stub
		
	}
}
