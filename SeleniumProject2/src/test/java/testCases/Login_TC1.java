package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import Pages.Dashboard;
//import Pages.HomePage;
import Pages.LandingPage;
//import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_TC1 {
	
	public static void main(String[] args) throws InterruptedException {

		//chrome driver setup
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//String menuCategory = By.linkText("TV, Appliances, Electronics");
		//By subMenuCategory = By.linkText("Televisions");
		
		//maximize the browser window
		driver.manage().window().maximize();
		
		driver.get("https://www.amazon.in/");
		
		//Creating object of home page
		LandingPage home = new LandingPage(driver);
		
		//Creating object of Login page
		//LoginPage login = new LoginPage(driver);
		
		//Creating object of Dashboard
		//Dashboard dashboard = new Dashboard(driver);
		
		//Click on Login button
		home.getPageTitle("Amazon.in");
		
		//verify hamburger icon is present and open menu
		home.hambugerMenu();
		
		//verify menu is open and navigate to the brand section
		home.customerName("Hello, Sign in");
		
		//select category on menu
		home.selectCategoryOnMenu("TV, Appliances, Electronics");
		
		//select subCategory on Main Menu
		home.selectCategoryOnMainMenu("Televisions");
	
		Thread.sleep(3000);
	
		driver.quit();
	}

}