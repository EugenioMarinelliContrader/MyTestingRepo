package methods;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.TestSetupAndTeardown;
import utility.DataUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class RegistrationTest  extends TestSetupAndTeardown {

    DataUtils dataUtils = new DataUtils();

    //TODO : define method to create and manipulate multi dimensional List



    /*public int calculateI(){
        return childElements.size();
    }*/

    /*public static int calculateP(){
        return childElements.size();
    }*/

    /*private int clickCount = 1;
    public void buttonClicked() {

        clickCount++; // Increment the counter when the button is clicked
    }*/

    /*public int getNumberOfPagesVisualized() {
        return clickCount + 1; // Return the counter value plus 1 for the starting page
    }*/


// TODO: method for obtaining and manipulating the multimensional data object

    /*public List<List<List<String>>> generateData() {
        List<List<List<String>>> data = new ArrayList<>();

        int pLimit = getNumberOfPagesVisualized();
        int iLimit = calculateI();
        int kLimit = 5;

        for (int p = 1; p <= pLimit; p++) {
            List<List<String>> pageData = new ArrayList<>();
            for (int i = 1; i <= iLimit; i++) {
                List<String> recordData = new ArrayList<>();
                for (int k = 1; k <= kLimit; k++) {
                    // Populate recordData based on p, i, k
                    recordData.add("Page " + p + ", Record " + i + ", Field " + k);
                }
                pageData.add(recordData);
            }
            data.add(pageData);
        }

        return data; // Return the obtained data
    }*/



    //TestSetupAndTeardown testSetupAndTeardown = new TestSetupAndTeardown();



    @Test(priority = 1)
    public void registration() {
        driver.get("http://164.92.113.35:4200/login");


        int seconds = 30;
        Duration duration = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, duration);


        //WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement img1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-login-page/div/div[2]/img[1]")));
        WebElement img2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-login-page/div/div[2]/img[2]")));
        WebElement img3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-login-page/div/div[2]/img[3]")));

        Assert.assertTrue(img1.isDisplayed() && img2.isDisplayed() && img3.isDisplayed());

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

        //WebDriverWait wait = new WebDriverWait(driver, 10);
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

        Actions actions = new Actions(driver);

// Perform the hover action on the tree element
        actions.moveToElement(sideNav);
        WebElement treeItem = driver.findElement(By.xpath("/html/body/app-root/app-app-layout/div/app-sidebar/div/div[2]/div[3]"));
        treeItem.click();

        int seconds1 = 10;
        Duration duration1 = Duration.ofSeconds(seconds1);
        WebDriverWait waitGestioneUtenti = new WebDriverWait(driver, duration1);
        WebElement creaUtenteButton = waitGestioneUtenti.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[1]/div/app-button-crea-utente/button/span[5]")));
        WebElement tipologiaField = waitGestioneUtenti.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[1]/app-generic-table-select/mat-form-field/div[1]")));
        WebElement ricercaField = waitGestioneUtenti.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[1]/app-generic-table-search/mat-form-field/div[1]")));


        Assert.assertTrue(creaUtenteButton.isDisplayed(), "Element 1 is visible.");
        Assert.assertTrue(tipologiaField.isDisplayed(), "Element 2 is visible.");
        Assert.assertTrue(ricercaField.isDisplayed(), "Element 3 is visible.");


        // Assuming successful logout, verify the logout message or URL
        String actualUrlGestioneUtenti = "http://164.92.113.35:4200/bo/responsabili";
        String expectedUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualUrlGestioneUtenti, expectedUrl, "Visualizzazione schermata Gestione Utenti");

        //WebElement areaOutsideSideNav = waitGestioneUtenti.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[1]/div/app-button-crea-utente/button")));
        Actions action1 = new Actions(driver);
        action1.moveByOffset(500, 0).perform();
        // ((JavascriptExecutor) driver).executeScript("document.elementFromPoint(100, 200).click();");
        //areaOutsideSideNav.click();


        WebDriverWait waitCreaUtenteButton = new WebDriverWait(driver, duration1);
        WebElement creaUtenteButtonNew = waitCreaUtenteButton.until(elementToBeClickable(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[1]/div/app-button-crea-utente/button")));

        creaUtenteButtonNew.click();

        WebElement creationModal = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/div/div"));
        Assert.assertTrue(creationModal.isDisplayed());

        WebElement fieldNome = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/div/div/app-crea-utente/div/div[2]/form/mat-form-field[1]/div[1]/div/div[2]/input"));
        WebElement fieldCognome = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/div/div/app-crea-utente/div/div[2]/form/mat-form-field[2]/div[1]/div/div[2]/input"));
        WebElement fieldRuolo = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/div/div/app-crea-utente/div/div[2]/form/mat-form-field[4]/div[1]/div/div[2]/input"));

        WebElement fieldTelefono = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/div/div/app-crea-utente/div/div[2]/form/mat-form-field[3]/div[1]/div/div[2]/input"));

        /*String randomNome = generateRandomString();
        String randomCognome = generateRandomString();
        String randomRuolo = generateRandomString();

        String randomTelNumber = generateRandomNumberString();
*/
        fieldNome.sendKeys(DataUtils.generateRandomString());
        fieldCognome.sendKeys(DataUtils.generateRandomString());
        fieldRuolo.sendKeys(DataUtils.generateRandomString());
        fieldTelefono.sendKeys(DataUtils.generateRandomNumberString());

        WebElement tipologiaDDM = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/div/div/app-crea-utente/div/div[2]/form/div[1]/mat-form-field/div[1]/div/div[2]/mat-select/div/div[1]"));

// Attempt to click the dropdown button multiple times until the dropdown menu becomes visible
        int maxAttempts = 2;
        int attempt = 1;

        while (attempt <= maxAttempts) {
            tipologiaDDM.click();
            attempt++;

        }

        WebElement listBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[4]/div/div")));
        Assert.assertTrue(listBox.isDisplayed());

        WebElement option1 = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div/mat-option[1]"));
        WebElement option2 = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div/mat-option[2]"));
        WebElement option3 = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div/mat-option[3]"));
        List<WebElement> optionsList = new ArrayList<>();

        // Add the WebElements to the list
        optionsList.add(option1);
        optionsList.add(option2);
        optionsList.add(option3);
        Random random = new Random();
        int index = random.nextInt(optionsList.size());

        WebElement randomElement = optionsList.get(index);

        // If the dropdown is still not open after the attempts, you can raise an exception or handle it accordingly.
        if (!option1.isDisplayed() && !option2.isDisplayed() && !option3.isDisplayed()) {
            System.out.println("Dropdown menu did not open after multiple attempts.");
        } else {
            // Locate and click the desired option (e.g., "Option 2")
            randomElement.click();

        }

        WebElement fieldEmail = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/div/div/app-crea-utente/div/div[2]/form/div[2]/mat-form-field/div[1]/div/div[2]/input"));


        // WebDriverWait waitCUE = new WebDriverWait(driver, 10);

        // Assert.assertTrue(creaUtente.isEnabled());

        // WebDriverWait waitModalClosed = new WebDriverWait(driver, 10);
        boolean modalStatus = creationModal.isDisplayed();
        //  elementToClick = null;

        boolean windowAppeared = wait.until(ExpectedConditions.visibilityOf(creationModal)).isDisplayed();
        WebElement elementToClick = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/div/div/app-crea-utente/div/div[3]/div[2]/button"));



        // WebElement tenMinuteEmailInput = tenMinuteEmailDriver.findElement(By.id("i-email")); // TODO: scommentare quando provider di posta ritorna funzionante
        // String emailAddress = tenMinuteEmailInput.getAttribute("value");

        // System.out.println("STO STAMPANDO L'EMAIL" + emailAddress);

        fieldEmail.sendKeys("mktrddrhdrxe888@gmail.com");

        while (elementToClick.isEnabled() && elementToClick.isDisplayed()) {
            try {
                elementToClick.click();
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException occurred. Stopping the click on the element.");
                break;
            }
        }

        WebElement finalPageButton = driver.findElement(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[3]/mat-paginator/div/div/div/button[4]"));



        driver.navigate().refresh();


        List<List<List<String>>> maxResult = dataUtils.getLastRegisteredUser(wait, driver);

        List<List<String>> maxResult2 = maxResult.get(maxResult.size()-1);

        List<String> maxResult3 = maxResult2.get(maxResult2.size()-1);

        System.out.println(maxResult3);







//        WebElement fullList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]")));
//        String commonXPathEPL = "/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]";
//
//        WebElement parentElement = fullList.findElement(By.xpath(commonXPathEPL)); // controllare come fare un ciclo sugli elementi della paginated list basato sull'xpath
//        List<WebElement> childElements = parentElement.findElements(By.xpath("./div"));
//
//        /*String commonXPathEPL = "/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]";
//
//        WebElement fullList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]")));
//
//        WebElement parentElement = fullList.findElement(By.xpath(commonXPathEPL)); // controllare come fare un ciclo sugli elementi della paginated list basato sull'xpath*/
//
//        WebElement nextPageButton = driver.findElement(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[3]/mat-paginator/div/div/div/button[3]/span[4]"));
//        WebElement previousPageButton = driver.findElement(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[3]/mat-paginator/div/div/div/button[2]/span[3]"));
//
//        int indexEPL = 1;
//        int p = 1;
//
//        // boolean keepRunning = true;
//        WebElement lastElement = null;
//        boolean hasNextPage = true;
//        while (hasNextPage) {
//
//            List<List<List<String>>> data = new ArrayList<>();
//
//
//         //   /html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]/div[1]
//
//
//
//            String completeXPath = commonXPathEPL + "/div[" + indexEPL + "]";
//            // Find all elements on the current page using the common XPath
//            List<WebElement> elements = driver.findElements(By.xpath(completeXPath)); // controllare come fare un ciclo sugli elementi della paginated list basato sull'xpath
//
//            if (!elements.isEmpty()) {
//
//
//                // boolean hasNextPage = true;
//
//                    // List<WebElement> childElements = parentElement.findElements(By.xpath("./div"));
//                    int numberOfChildElements = childElements.size();
//                System.out.println("The number of child elements is " + numberOfChildElements);
//
//
//
//                    for (int i = 1; i <= numberOfChildElements; i++) {
//                        List<List<String>> pageData = new ArrayList<>();
//
//
//                        for (int k = 1; k <= 5; k++) {
//
//                            String fieldXPath = "/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]/div[" + i + "]/td[" + k + "]/app-generic-body-cell/span";
//                            WebElement fieldElement = driver.findElement(By.xpath(fieldXPath));
//                            String fieldValue = fieldElement.getText();
//
//                            List<String> rowData = new ArrayList<>();
//                            // rowData.add("Page " + p);
//                            // rowData.add("Record " + i);
//                            // rowData.add("Field " + k);
//                            rowData.add(fieldValue);
//                            pageData.add(rowData);
//
//                            // Concatenate and print the field value
//                            System.out.println("Page " + p + ", Record " + i + ", Field " + k + ": " + fieldValue);
//                            System.out.println("Printing each field by record and page" + rowData);
//                            System.out.println("Printing each element of pageData: " + pageData);
//                            System.out.println("Printing each element of data: " + data);
//// data.get(p).get(i).get(k); applicare la ricerca per indici all'oggetto tridimensionale
//
//                            /**/
//
//                            // String[][][] dataArray = new String[p][i][k]; // TODO: definire struttura e meccanismo di riempimento dell'array multidimensionale
//                            // dataArray[p][i][k] = fieldValue;
//
//                            // /html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]/div[17]/td[3]/app-generic-body-cell/span
//
//                    }
//
//                        data.add(pageData);
//
//            }
//
//
//                // generateData();
//
//
//                int customWaitTimeSeconds = 8;
//                Duration duration2 = Duration.ofSeconds(customWaitTimeSeconds);
//                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                WebDriverWait waitOverlay = new WebDriverWait(driver, duration1);
//                waitOverlay.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cdk-overlay-backdrop")));
//                if (nextPageButton.isEnabled()) {
//                    try {
//                        WebDriverWait waitNPB = new WebDriverWait(driver, duration2);
//                        waitNPB.until(ExpectedConditions.elementToBeClickable(nextPageButton));
//                        nextPageButton.click();
//                        WebElement fullList2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]")));
//                        commonXPathEPL = "/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]";
//
//                        parentElement = fullList.findElement(By.xpath(commonXPathEPL)); // controllare come fare un ciclo sugli elementi della paginated list basato sull'xpath
//
//                        childElements = parentElement.findElements(By.xpath("./div"));
//
//                        // buttonClicked();
//                        p++;
//                    } catch (ElementClickInterceptedException e) {
//                        System.out.println("ElementClickInterceptedException occurred. Stopping the click on the element.");
//                        hasNextPage = false;  // Exit the loop if clicking is intercepted
//                    }
//                    }
//                else {
//                    hasNextPage = false;
//                }
//
//                }
//            else {
//                hasNextPage = false;
//            }
//
//            // System.out.println(data.get(p).get(i).get(k));
//
//
//        }
//

    }

}




