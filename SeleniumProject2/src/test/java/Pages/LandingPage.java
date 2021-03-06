package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage{
	
	WebDriver driver;
	
	WebDriverWait wait;
	//Constructor that will be automatically called as soon as the object of the class is created
	public LandingPage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		PageFactory.initElements(driver, this);
		
	}
	
	//Locator for Elements on the Landing_Pages
	By navLogo = By.id("nav-logo-sprites");
	By pageMenu = By.id("nav-hamburger-menu");
	String customerName = "hmenu-customer-name";
	String mainMenuLabel = "//*[text()='main menu']";
	//JavascriptExecutor js = (JavascriptExecutor) driver;
	String newline = System.lineSeparator();
	
	//Method to capture the page title
	public String getPageTitle(String title) {
		String pageTitle = driver.getTitle();
		return pageTitle;
		//assert pageTitle.matches(title);
		
	}
	
	//verify hamburger icon is present and open menu
	public void hambugerMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(pageMenu));
		driver.findElement(pageMenu).click();
	}
	
	//verify menu is open and navigate to the brand section;
	public String customerName(String namme) {
		WebElement name = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(customerName)));
		String getName = name.getText();
		return getName;
		//ssert getName == namme;
	}
	
	
	//scroll to TV, Appliances, Electronics into view and click
	public void selectCategoryOnMenu(String category) {
		WebElement appliances = driver.findElement(By.linkText(category));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", appliances);
		wait.until(ExpectedConditions.elementToBeClickable(appliances));
		appliances.click();
	}

	//verify user is on Main Menu and select the subCategory on Main Menu
	public void selectCategoryOnMainMenu(String categoryOnMenu) {
		
		WebElement mainMenu = driver.findElement(By.xpath(mainMenuLabel));
		mainMenu.isDisplayed();
		System.out.print("User is on Menu"+ newline);
		
		WebElement subCat = driver.findElement(By.linkText(categoryOnMenu));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", subCat);
		wait.until(ExpectedConditions.elementToBeClickable(subCat));
		subCat.click();
	}

}
