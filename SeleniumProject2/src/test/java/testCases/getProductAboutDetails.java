package testCases;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//import Pages.Dashboard;
//import Pages.HomePage;
import Pages.LandingPage;
import Pages.brandPage;
import Pages.itemSelected;
import Pages.resultsPage;
//import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class getProductAboutDetails {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		//page loading strategy setup
		ChromeOptions chrome = new ChromeOptions();
		chrome.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		//chrome driver setup
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(chrome);
		
		//Set data for input and verification
		String pgTitle = "Amazon.in";
		String custName = "Hello, Sign in";
		String menuCategory = "TV, Appliances, Electronics";
		String subCategory = "Televisions";
		String brandPageTitle = "Buy the latest LED TVs, 4K TVs and Android TVs online at Best Prices in India-Amazon.in | Shop by size, price, features and more";
		String brandName = "Samsung";
		String filterValue = "Price: High to Low";
		Integer productLocation = 2; // e.g. 1 is for the first product on the result, 2 is for the second product etc.
		Integer tabID = 1; //switch to a particular tab on the browser. 
		
		
		//maximize the browser window
		driver.manage().window().maximize();
		
		driver.get("https://www.amazon.in/");
		
		//Creating object of Landing page
		LandingPage home = new LandingPage(driver);
		
		//Creating object of Brand page
		brandPage brand = new brandPage(driver);
		
		//Creating object of the result and filterBy page
		resultsPage result = new resultsPage(driver);
		
		//Creating object of the products page to get "About this Item" details
		itemSelected product = new itemSelected(driver);
		
		//verify page title on landing Page
		home.getPageTitle(pgTitle);
		
		//verify hamburger icon is present, click and open menu
		home.hambugerMenu();
		
		//verify menu is open by vetting name is displayed
		home.customerName(custName);
		
		//navigate to the brand section and select category on menu using link text
		home.selectCategoryOnMenu(menuCategory);
		
		//navigate to subCategory and click on subCategory on Main Menu 
		home.selectCategoryOnMainMenu(subCategory);
		
		//verify page title on the category/brand Page
		brand.getCategoryTitle(brandPageTitle);
		
		
		//selects and verifies brand to filter product category by using the displayed text value on of the brand
		brand.brandName(brandName);
		
		//Filter result e.g. Price High to Low
		result.sortByFilter(filterValue);
		assertTrue(filterValue.contains("Price: High to Low"));
		
		//Locate product to be clicked i.e. productLocation is the position of the item as displayed on the page.
		result.selectResultItem(productLocation);
		
		//switch to the active tab
		result.switchWindow(tabID);
		
		//print "About Item" details of the product selected
		product.printProductDetails();
		
		
		driver.quit();
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
	}

}