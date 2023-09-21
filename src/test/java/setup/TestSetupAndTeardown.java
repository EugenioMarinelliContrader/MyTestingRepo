package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestSetupAndTeardown {
    protected WebDriver driver;
    protected String landingPageURL;

    protected WebDriver tenMinuteEmailDriver;

    @BeforeMethod
    public void setUp() {
        // Set up the WebDriver before each test method

        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\contrader\\Desktop\\Chrome\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setBrowserVersion("116.0.5845.96");
        // Create a new instance of ChromeDriver
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        System.out.println("Test environment set up.");
    }

    /* @BeforeMethod
    public void setUpEmail() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\contrader\\Desktop\\Chrome\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // Create a new instance of ChromeDriver
        tenMinuteEmailDriver = new ChromeDriver(options);
        // connecting to the page
        tenMinuteEmailDriver.get("https://tempmailo.com/");
        // retrieving the 10-minute email address

        tenMinuteEmailDriver.manage().window().maximize();
        WebElement consentButton = tenMinuteEmailDriver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[2]/button[1]"));
        consentButton.click();
        WebElement refreshButton = tenMinuteEmailDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[1]/div[2]/div[3]/button"));
        refreshButton.click();
        WebElement refreshModalButton = tenMinuteEmailDriver.findElement(By.xpath("/html/body/main/div[2]/div[2]/div/div[3]/button[1]"));
        refreshModalButton.click();
    }

     */

    @AfterMethod
    public void tearDown() {
        // Add a wait time before calling driver.quit()
        try {
            Thread.sleep(8000); // Wait for 3 seconds (adjust the time as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
        // tenMinuteEmailDriver.quit(); TODO: scommentare quando provider posta ritorna funziomante
    }
    public WebDriver getDriver() {
        return driver;
    }
    // Other utility methods can be added here if needed

   // public WebDriver getTenMinuteEmailDriver() { return tenMinuteEmailDriver;}
}


