package generic;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementStatusCheck {
	
	public static boolean isElementPresent(WebDriver driver, WebElement element)
	{
		try 
		{
			element.isDisplayed();
			//driver.findElement(By.id(element));
			return true;	
		}
		catch(NoSuchElementException e)
		{
		return false;
		}
	}
	
	public static boolean isElementEnabled(WebDriver driver, WebElement element)
	{
		try 
		{
			element.isEnabled();
			//driver.findElement(By.id(element));
			return true;	
		}
		catch(Exception e)
		{
		return false;
		}
		
		
	}
	
	

}
