package utility;

import methods.RegistrationTest;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DataUtils {

    public static String generateRandomString() {

        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        // String allLetters = upperCaseLetters + lowerCaseLetters;
        int MIN_LENGTH = 3;
        int MAX_LENGTH = 15;

        Random random = new Random();
        int length = random.nextInt(MAX_LENGTH - MIN_LENGTH + 1) + MIN_LENGTH; // Random length between 3 and 15
        StringBuilder randomString = new StringBuilder();

        // Generate the first capital letter
        randomString.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));

        // Generate the rest of the string
        for (int i = 1; i < length; i++) {
            randomString.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        }

        return randomString.toString();
    }


    public static String generateRandomNumberString() {

        String digits = "0123456789";
        int MIN_LENGTH_TEL = 10;
        int MAX_LENGTH_TEL = 15;

        Random random = new Random();
        int length = random.nextInt(MAX_LENGTH_TEL - MIN_LENGTH_TEL + 1) + MIN_LENGTH_TEL; // Random length between 10 and 15
        StringBuilder randomNumberString = new StringBuilder();

        // Generate the random digits
        for (int i = 0; i < length; i++) {
            randomNumberString.append(digits.charAt(random.nextInt(digits.length())));
        }

        return randomNumberString.toString();
    }

    /*public int calculateI(List<WebElement> childElements){
        return childElements.size();
    }


    private int clickCount = 1;
    public void buttonClicked() {
        clickCount++; // Increment the counter when the button is clicked
    }

    public int getNumberOfPagesVisualized() {
        return clickCount + 1; // Return the counter value plus 1 for the starting page
    }*/

    /*public List<List<List<String>>> generateData() {


        List<List<List<String>>> data = new ArrayList<>();

        int pLimit = getNumberOfPagesVisualized();
        int iLimit = calculateI(childElements); //TODO: sistemare utilizzo di variabili calcolate in un'altra classe
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
    public List<List<List<String>>> getLastRegisteredUser(WebDriverWait wait, WebDriver driver){

        List<List<List<String>>> dataAggregation = new ArrayList<>();

        int indexEPL = 1;
        int p = 1;
        List<Integer> secondElements = new ArrayList<>();
        WebElement lastElement = null;
        boolean hasNextPage = true;
        while (hasNextPage) {

            WebElement fullList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]")));
            // WebElement fullList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));


            String commonXPathEPL = "/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]";

            WebElement parentElement = fullList.findElement(By.xpath(commonXPathEPL)); // controllare come fare un ciclo sugli elementi della paginated list basato sull'xpath
            List<WebElement> childElements = parentElement.findElements(By.xpath("./div"));

            WebElement nextPageButton = driver.findElement(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[3]/mat-paginator/div/div/div/button[3]/span[4]"));

            List<List<String>> data = new ArrayList<>();

            String completeXPath = commonXPathEPL + "/div[" + indexEPL + "]";
            // Find all elements on the current page using the common XPath
            List<WebElement> elements = driver.findElements(By.xpath(completeXPath)); // controllare come fare un ciclo sugli elementi della paginated list basato sull'xpath



            //TODO: extract page number from Paginator range label (absolute xpath below) and compare with sum of child elements in page 1 & 2

            WebElement paginatorLabel = driver.findElement(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[3]/mat-paginator/div/div/div/div"));

            String labelText = paginatorLabel.getText();
            System.out.println(labelText);

            String[] numericParts = labelText.split("di");
            System.out.println(numericParts);
            String[] refinementNumericPart1 = numericParts[0].split("[ - ]+");
            System.out.println(refinementNumericPart1);
            int[] intArray1 = new int[refinementNumericPart1.length];

            for (int i = 0; i < refinementNumericPart1.length; i++) {
                try {
                    // Convert the element at index 'i' to an int
                    intArray1[i] = Integer.parseInt(refinementNumericPart1[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Failed to convert element at index " + i + " to an int.");
                }
            }

            for (int i = 0; i < intArray1.length; i++) {
                System.out.println("Extracted int at index " + i + ": " + intArray1[i]);
            }

            String refinementNumericPart2 = numericParts[1];
            System.out.println(refinementNumericPart2);
            String trimmedValue = refinementNumericPart2.trim();
            try {
                int number = Integer.parseInt(trimmedValue);
                System.out.println("Converted integer: " + number);
            } catch (NumberFormatException e) {
                System.out.println("Failed to convert the string to an integer.");
            }

            if (p == 1) {
                intArray1[1] = intArray1[1] - 3;
                System.out.println(intArray1[1]);
            }

            if (p==2) {
                intArray1[0] = intArray1[0] - 3;
                System.out.println(intArray1[0]);
            }



            for (int i = 0; i < intArray1.length; i++) {
                if (i % 2 == 1) { // Check if it's the second element (arrays are 0-based)
                    secondElements.add(intArray1[i]);
                }
            }

            // Print the collected second elements
            for (Integer element : secondElements) {
                System.out.println("Second element: " + element);
            }








            /*int displayedStart = Integer.parseInt(rangeParts[0].trim());
            System.out.println(displayedStart);
            int displayedEnd = Integer.parseInt(rangeParts[1].trim());
            System.out.println(displayedEnd);
            int hyphenIndex = rangeParts[0].indexOf("-");

            if (hyphenIndex != -1) {

                String numberText = labelText.substring(hyphenIndex + 1).trim();
                System.out.println(numberText);

            }*/
            /*/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[3]/mat-paginator/div/div/div/div
                    /html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[3]/mat-paginator/div/div/div/div*/

            if (!elements.isEmpty()) {

                int numberOfChildElements = childElements.size();
                System.out.println("The number of child elements is " + numberOfChildElements);



                for (int i = 1; i <= numberOfChildElements; i++) {
                    List<List<String>> pageData = new ArrayList<>();

                    List<String> rowData = new ArrayList<>();
                    for (int k = 1; k <= 5; k++) {


                        String fieldXPath = "/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]/div[" + i + "]/td[" + k + "]/app-generic-body-cell/span";
                        WebElement fieldElement = driver.findElement(By.xpath(fieldXPath));
                        String fieldValue = fieldElement.getText();

                        //List<String> rowData = new ArrayList<>();
                        rowData.add(fieldValue);
                        //pageData.add(rowData);



                        // Concatenate and print the field value
                        System.out.println("Page " + p + ", Record " + i + ", Field " + k + ": " + fieldValue);
                        System.out.println("Printing each field by record and page" + rowData);
                        System.out.println("Printing each element of pageData: " + pageData);
                        System.out.println("Printing each element of data: " + data);
                    }
                    data.add(rowData);
                    System.out.println("Printing each element of data: " + data);

//                    List<List<List<String>>> maxResult2 = dataAggregation.get(dataAggregation.size()-1);
//
//                    List<List<String>> maxResult3 = maxResult2.get(maxResult2.size()-1);
//
//                    List<List<String>> data2 = data.get(data.size()-1);
//
//                    for(List<String> e : maxResult3 ){
//                        String email1 = e.get(2).toString();
//                        for(List<String> x : data2){
//                            String email2 = x.get(2).toString();
//                            if(email1.equals(email2)){
//
//                            }
//                        }
//                    }
               }




                int seconds1 = 10;
                Duration duration1 = Duration.ofSeconds(seconds1);
                int customWaitTimeSeconds = 20;
                Duration duration2 = Duration.ofSeconds(customWaitTimeSeconds);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                WebDriverWait waitOverlay = new WebDriverWait(driver, duration1);
                waitOverlay.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cdk-overlay-backdrop")));
                if (nextPageButton.isEnabled()) {
                    try {
                        WebDriverWait waitNPB = new WebDriverWait(driver, duration2);
                        waitNPB.until(ExpectedConditions.elementToBeClickable(nextPageButton));
                        nextPageButton.click();

                        /*WebElement fullList2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]")));
                        commonXPathEPL = "/html/body/app-root/app-app-layout/div/div[2]/mat-sidenav-container/mat-sidenav-content/div[2]/app-lista-responsabili/div/app-generic-table/section/div[2]/table/div[2]";
                        WebElement parentElement2 = fullList2.findElement(By.xpath(commonXPathEPL)); // controllare come fare un ciclo sugli elementi della paginated list basato sull'xpath
                        childElements = parentElement2.findElements(By.xpath("./div"));*/

                        // buttonClicked();

                        p++;
                    } catch (ElementClickInterceptedException e) {
                        System.out.println("ElementClickInterceptedException occurred. Stopping the click on the element.");
                        hasNextPage = false;  // Exit the loop if clicking is intercepted

                    }
                }
                else {
                    hasNextPage = false;


                    dataAggregation.add(data);
                }

            }
            else {
                hasNextPage = false;
                dataAggregation.add(data);
            }

            dataAggregation.add(data);

        }


        return dataAggregation;
    }

//    public Boolean comparisonRegistrationData(){
//
//    }

}
