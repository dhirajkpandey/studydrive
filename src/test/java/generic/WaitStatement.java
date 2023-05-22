package generic;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitStatement {
	//Implicit Wait
		public static void fnImplicitWait(WebDriver driver, int time)
		{
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		}
		
		//Explicit Wait
		public static void fnExplicitWait(WebDriver driver, Duration time, WebElement element)
		{
			WebDriverWait wait=new WebDriverWait(driver, time);
			//wait.until(ExpectedConditions.visibilityOf(element));
			
			
		}
		
		//Explicit Wait with clickable condition
		public static void fnExplicitWaitIsClickable(WebDriver driver, Duration time, WebElement element)
		{
			WebDriverWait wait=new WebDriverWait(driver, time);
			//wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		
		//Explicit Wait for Alert
		public static boolean fnIsAlertPresent(WebDriver driver, Duration time)
		{
		    boolean foundAlert = false;
		    WebDriverWait wait = new WebDriverWait(driver, time );
		    try 
		    {
		       // wait.until(ExpectedConditions.alertIsPresent());
		        foundAlert = true;
		    } 
		    catch (Exception e)
		    {
		        foundAlert = false;
		    }
		    return foundAlert;
		}

}
