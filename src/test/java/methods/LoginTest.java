package methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import setup.TestSetupAndTeardown;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginTest extends TestSetupAndTeardown {
    //TestSetupAndTeardown testSetupAndTeardown = new TestSetupAndTeardown();

    @Test(priority = 1)
    public void login() {
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

        // Submit the login form

        WebElement loginButton = getDriver().findElement(By.xpath("/html/body/app-root/app-login-page/div/div[3]/div/app-login/div/div/div/form/div[4]"));
        Assert.assertTrue(loginButton.isDisplayed());
        Assert.assertTrue(loginButton.isEnabled());
        loginButton.click();

        int seconds = 10;
        Duration duration = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        WebElement landingPageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/app-header/mat-toolbar/div[2]/div")));
        Assert.assertTrue(landingPageElement.isDisplayed());
        String landingPageURL = driver.getCurrentUrl();
        System.out.println(landingPageURL);
        String actualUrl = "http://164.92.113.35:4200/bo/dashboard";
        Assert.assertEquals(landingPageURL, actualUrl);

        WebElement sideNav = getDriver().findElement(By.xpath("/html/body/app-root/app-app-layout/div/app-sidebar/div"));
        WebElement edificioTree = sideNav.findElement(By.xpath("/html/body/app-root/app-app-layout/div/app-sidebar/div/div[2]/div[2]/mat-icon"));
        WebElement utentiTree = sideNav.findElement(By.xpath("/html/body/app-root/app-app-layout/div/app-sidebar/div/div[2]/div[3]/mat-icon"));
        WebElement magazzinoTree = sideNav.findElement(By.xpath("/html/body/app-root/app-app-layout/div/app-sidebar/div/div[2]/div[4]/mat-icon"));

        boolean cond_1 = sideNav.isDisplayed();
        boolean cond_2 = edificioTree.isDisplayed();
        boolean cond_3 = utentiTree.isDisplayed();
        boolean cond_4 = magazzinoTree.isDisplayed();
        Assert.assertTrue(cond_1 && cond_2 && cond_3 & cond_4);
    }
}