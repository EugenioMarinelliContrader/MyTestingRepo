package methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.TestSetupAndTeardown;

import java.time.Duration;

public class LogoutTest extends TestSetupAndTeardown {

    @Test (priority = 2, dependsOnMethods = "methods.LoginTest.login", alwaysRun = true)

    public void logout() {
        driver.get("http://164.92.113.35:4200/login");
        //WebDriver driver = testSetupAndTeardown.getDriver();
        // Perform your test actions using the driver
        // Navigate to the login page
        // Find the username and password input fields
        WebElement usernameField = getDriver().findElement(By.id("mat-input-0"));
        WebElement passwordField = getDriver().findElement(By.id("mat-input-1"));

        // Enter the login credentials
        usernameField.sendKeys("e.marinelli@contrader.it");
        passwordField.sendKeys("NtnMrn52!");

        WebElement loginButton = getDriver().findElement(By.xpath("/html/body/app-root/app-login-page/div/div[3]/div/app-login/div/div/div/form/div[4]"));
        loginButton.click();

        int seconds = 10;
        Duration duration = Duration.ofSeconds(seconds);
        WebDriverWait waitForLogin = new WebDriverWait(driver, duration);
        WebElement landingPageElement = waitForLogin.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/app-header/mat-toolbar/div[2]/div")));
        Assert.assertTrue(landingPageElement.isDisplayed());

        WebElement dropdownMenu = getDriver().findElement(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/app-header/mat-toolbar/div[2]/button[2]"));
        dropdownMenu.click();
        WebElement logoutOption = getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/button[3]"));
        logoutOption.click();

        WebDriverWait waitForExitModal = new WebDriverWait(driver, duration);
        WebElement exitModal = waitForExitModal.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div")));

        WebElement logoutConfirmButton = exitModal.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-generic-confirm-modal/div/div/div[2]/div/button[2]"));
        logoutConfirmButton.click();

        WebDriverWait waitForExitCompletion = new WebDriverWait(driver, duration);
        WebElement usernameFieldPostLogout = waitForExitCompletion.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-login-page/div/div[3]/div/app-login/div/div/div/form/div[1]/mat-form-field/div[1]")));
        WebElement passwordFieldPostLogout = waitForExitCompletion.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-login-page/div/div[3]/div/app-login/div/div/div/form/div[2]/mat-form-field/div[1]")));

        Assert.assertTrue(usernameFieldPostLogout.isDisplayed(), "Element 1 is visible.");
        Assert.assertTrue(passwordFieldPostLogout.isDisplayed(), "Element 2 is visible.");

        // Assuming successful logout, verify the logout message or URL
        String actualUrl= "http://164.92.113.35:4200/login";
        String expectedUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(expectedUrl, actualUrl);
        Assert.assertEquals(actualUrl, expectedUrl, "Logout successful.");
    }
}
