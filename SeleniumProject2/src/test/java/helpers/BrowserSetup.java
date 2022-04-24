package helpers;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup {

	// base test class extended by all tests and suites.
	public static WebDriver driver;

	public static WebDriverWait wait;
	public static String basePath = "https://www.amazon.in/";

	// initializes test environment and public variables, may be called in
	// individual test classes or suites
	// it may become inconvenient to use the same setup for all tests so this method
	// should be modified to be configured via params
	public void initialize_test() {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		BrowserSetup.driver = new ChromeDriver(chromeOptions);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.manage().window().maximize();
	}

}
