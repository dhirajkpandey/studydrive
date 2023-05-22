package POM.onboardingPOM;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.github.sukgu.Shadow;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;


import generic.WaitStatement;
import org.testng.annotations.Test;
import testscripts.BaseTest;
import testscripts.applicationcontext.ScenarioContext;

public class RegistrationPagePOM extends BaseTest{

	@FindBy(xpath="//button[contains(text(),'Sign up')]")
	private WebElement signUp_button;
	@FindBy(xpath="//button[contains(text(),'Accept all')]")
	private WebElement homePageCookiesAcceptAll_button;
	@FindBy(xpath="//div[@class = 'flex space-x-4 w-full']/button")
	private WebElement signUpWithGoogle_button;
	@FindBy(xpath="//input[@type= 'email']")
	private WebElement signUpemailAddres_inputTextBox;
	@FindBy(xpath="//input[@type= 'password']")
	private WebElement signUpPassword_inputTextBox;
	@FindBy(xpath="//div[text() = 'There is an account using this email address already']")
	private WebElement signUpEmailAlreadyExistsValidation_label;
	@FindBy(xpath="//span[text() = 'Sign up for free']")
	private WebElement registerSignUp_button;

	@FindBy(id = "identifierId")
	private WebElement googleSignUpemailAddres_inputTextBox;
	@FindBy(xpath="//input[@type= 'password']")
	private WebElement googleSignUpPassword_inputTextBox;
	@FindBy(xpath="//span[text() = 'Next']")
	private WebElement googleSignUpNext_button;

	//-------------------Welcome page web elements ------------------------------
	@FindBy(id = "username")
	private WebElement welcomePageUsername_inputTextBox;
	@FindBy(xpath="//div[contains(text(), 'Select gender')]")
	private WebElement welcomePageGender_dropdown;
	@FindBy(xpath="//div[contains(text(), 'Male')]")
	private WebElement welcomePageGender_value;
	@FindBy(xpath="//span[text() = 'Continue']")
	private WebElement welcomePageContinue_button;

	@FindBy(xpath="//input[@placeholder = 'Enter university name']")
	private WebElement selectUniversityName_dropdown;
	@FindBy(xpath="//input[@placeholder = 'Select field of study']")
	private WebElement selectFieldOfStudy_dropdown;
	@FindBy(xpath="//input[@placeholder = 'Select study program']")
	private WebElement selectStudyProgram_dropdown;
	@FindBy(xpath="//input[@placeholder = 'Select your starting semester']")
	private WebElement selectStartingSemester_dropdown;


	public RegistrationPagePOM(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void signUp_acceptCookie(WebDriver driver) throws InterruptedException {

		signUp_button.click();
		Thread.sleep(8000);
		Shadow shadow = new Shadow(driver);
		WebElement acceptAllCookie = shadow.findElementByXPath("//div[@id='usercentrics-root']//button[contains(text(),'Accept all')]");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", acceptAllCookie);
		WaitStatement.fnExplicitWait(driver, Duration.ofSeconds(30), signUpWithGoogle_button);
		signUpWithGoogle_button.click();
		Thread.sleep(2000);
	}
	public void signUpValidation_If_Email_Exists(WebDriver driver) throws InterruptedException {
		String expectedValidation = "There is an account using this email address already";
		signUpemailAddres_inputTextBox.sendKeys(ScenarioContext.ALREADY_REGISTERED_USER_EMAIL);
		signUpPassword_inputTextBox.sendKeys(ScenarioContext.USER_PASS);
		registerSignUp_button.click();
		Thread.sleep(2000);
		WaitStatement.fnExplicitWait(driver, Duration.ofSeconds(30), signUpEmailAlreadyExistsValidation_label);
		String actualvalidation = signUpEmailAlreadyExistsValidation_label.getText();
		Assert.assertEquals(actualvalidation, expectedValidation);
	}

	public void signUpWithGoogleAccount(WebDriver driver) throws InterruptedException{

		Set<String> windows =driver.getWindowHandles();
		Iterator <String>  itr = windows.iterator();
		String strudydriveWindow = itr.next();
		String googleSignUpWindow = itr.next();
		driver.switchTo().window(googleSignUpWindow);
		Thread.sleep(3000);
		googleSignUpemailAddres_inputTextBox.sendKeys(ScenarioContext.USER_EMAIL);
		googleSignUpNext_button.click();
		Thread.sleep(3000);
		googleSignUpPassword_inputTextBox.sendKeys(ScenarioContext.USER_PASS);
		googleSignUpNext_button.click();

		driver.switchTo().window(strudydriveWindow);
		Thread.sleep(20000);
		WaitStatement.fnExplicitWait(driver, Duration.ofSeconds(30), welcomePageUsername_inputTextBox);
		welcomePageUsername_inputTextBox.clear();
		welcomePageUsername_inputTextBox.sendKeys(ScenarioContext.USER_EMAIL);
		welcomePageGender_dropdown.click();
		Thread.sleep(3000);
		welcomePageGender_value.click();
		welcomePageContinue_button.click();
		Thread.sleep(3000);
		WaitStatement.fnExplicitWait(driver, Duration.ofSeconds(10), selectUniversityName_dropdown);
		selectUniversityName_dropdown.sendKeys(ScenarioContext.UNIVERSITY_NAME);
		Thread.sleep(3000);
		selectUniversityName_dropdown.sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		selectFieldOfStudy_dropdown.sendKeys(ScenarioContext.SELECT_FIELD_OF_STUDY);
		selectFieldOfStudy_dropdown.sendKeys((Keys.ENTER));
		selectStudyProgram_dropdown.sendKeys(ScenarioContext.STUDY_PROGRAM);
		selectStudyProgram_dropdown.sendKeys(Keys.ENTER);
		selectStartingSemester_dropdown.sendKeys(ScenarioContext.STARTING_SEMESTER);
		WaitStatement.fnImplicitWait(driver, 10);
		selectStartingSemester_dropdown.sendKeys(Keys.ENTER);
		//welcomePageContinue_button.click();

	}

}
