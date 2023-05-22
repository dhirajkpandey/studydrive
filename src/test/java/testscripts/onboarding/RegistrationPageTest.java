package testscripts.onboarding;
import POM.onboardingPOM.RegistrationPagePOM;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import testscripts.BaseTest;
//import org.apache.log4j

public class RegistrationPageTest extends BaseTest {

    public RegistrationPagePOM registrationPagePOM;

    @Test(priority = 1)
    public void registerUserTest_acceptCookie () throws InterruptedException{

        extentTest = reports.createTest("Registration, registerUserTest_acceptCookie","This test will accept cookie on sign up page");

        registrationPagePOM = new RegistrationPagePOM(driver);
        registrationPagePOM.signUp_acceptCookie(driver);

        extentTest.log(Status.INFO, "Existing email validation checked");

    }
    @Test(priority = 2)
    public void existingEmailValidation() throws InterruptedException{

        extentTest = reports.createTest("Registration, existingEmailValidation","This test will check if email already exists");

        registrationPagePOM = new RegistrationPagePOM(driver);
        registrationPagePOM.signUpValidation_If_Email_Exists(driver);

        extentTest.log(Status.INFO, "Accepted cookie on signup page");

    }
    @Test(priority = 3)
    public void signUpWithGoogleAccount() throws InterruptedException{

        extentTest = reports.createTest("Registration, signUpWithGoogleAccount","This test will sign up user with google account");

        registrationPagePOM = new RegistrationPagePOM(driver);
        registrationPagePOM.signUpWithGoogleAccount(driver);

        extentTest.log(Status.INFO, "Signed up successfully with google account");

    }

}
