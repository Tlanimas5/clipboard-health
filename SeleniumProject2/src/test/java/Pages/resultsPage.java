package Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class resultsPage{
	
WebDriver driver;
	
	WebDriverWait wait;
	//Constructor that will be automatically called as soon as the object of the class is created
	public resultsPage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		PageFactory.initElements(driver, this);
	}
	//set up one lineseparator
	String newline = System.lineSeparator();
	//set Javascript executor
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	//Locator for Elements on the Landing_Page
	By sortByFilter = By.name("s");
	By sortByValue = By.className("a-dropdown-prompt");
	By resultsLabel = By.xpath("//span[@class='a-size-medium-plus a-color-base a-text-normal']");
	
	
	//filter results by the sortBy value provided, verify sortby value is displayed on the dropdown menu
	
	public void resultsDisplayed() {
		//Verify Results are displayed and selecting second item from result
		WebElement results = driver.findElement(resultsLabel);
		wait.until(ExpectedConditions.presenceOfElementLocated(resultsLabel));
	}
	
	public String sortByFilter(String filterValue) {
		Select sortBy = new Select(driver.findElement(sortByFilter));
		sortBy.selectByVisibleText(filterValue);
		WebElement value = wait.until(ExpectedConditions.presenceOfElementLocated(sortByValue));
		String sortByText = value.getText();
		return sortByText;
		
		//assertTrue(value.contains("Price: High to Low"));
	}
	
	//Navigate to a brand on the filter options and select the option on the filter
	public void selectResultItem(Integer location) {
		Integer actualLocation = location + 1;
		String itemLocation = String.valueOf(actualLocation);
		WebElement item = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div["+itemLocation+"]/div/div/div/div/div[2]/div[1]/h2/a")));
		item.click();
	}
	
	public void switchWindow(Integer tabIndex) {
		//Storing the number of windows opened
		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		//switch to the second tab
	    driver.switchTo().window(newTb.get(tabIndex));
	}
	
	
}
