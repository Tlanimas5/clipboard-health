package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class brandPage {

	WebDriver driver;

	WebDriverWait wait;
	//Constructor that will be automatically called as soon as the object of the class is created
	public brandPage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		PageFactory.initElements(driver, this);
	}

	//Locator for Elements on the brand_Page

	String newline = System.lineSeparator();
	//set Javascript executor
	//JavascriptExecutor js = (JavascriptExecutor) driver;


	//Verify user is on page with Televisions
	public String getCategoryTitle(String title) {
		String pageTitle = driver.getTitle();
		return pageTitle;
		//assert pageTitle.matches(title);

	}

	//Navigate to a brand on the filter options and select the option on the filter
	public void brandName(String nameOfBrand) {
		WebElement brand = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='a-size-base a-color-base'][normalize-space()='"+nameOfBrand+"']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", brand);
		brand.click();
		System.out.print("Validated "+nameOfBrand+" is clicked"+newline);

		//verify brand is checked
		
		WebElement brandChecked = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='a-size-base a-color-base a-text-bold'][normalize-space()='"+nameOfBrand+"']")));
		js.executeScript("arguments[0].scrollIntoView();", brandChecked);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-size-base a-color-base a-text-bold'][normalize-space()='"+nameOfBrand+"']")));
		assert brand.isSelected();
		System.out.print("validated "+nameOfBrand+" is checked"+newline);
	}




}
