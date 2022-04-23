package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class itemSelected {
	
	WebDriver driver;
	
	WebDriverWait wait;
	//Constructor that will be automatically called as soon as the object of the class is created
	public itemSelected(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
	}
	
	//set up Line seperator
	String newline = System.lineSeparator();
	//set Javascript executor
	//JavascriptExecutor js = (JavascriptExecutor) driver;
	
	By aboutItemLabel = By.xpath("//*[@id='feature-bullets']/h1");
	By detailsAboutItem = By.xpath("//*[@id='feature-bullets']/ul");
	
	
	//scroll to TV, Appliances, Electronics into view and click
	public void printProductDetails() {
		WebElement aboutTheItem = wait.until(ExpectedConditions.presenceOfElementLocated(aboutItemLabel));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", aboutTheItem);
		String labelAboutItem = aboutTheItem.getText();
		
		List<WebElement> productDetails = driver.findElements(detailsAboutItem);
		System.out.print(labelAboutItem +newline);
		for (WebElement eachDetail : productDetails )
		{
			System.out.print(eachDetail.getText()+newline);
		}
		
	}
	

}
