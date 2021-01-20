package JavaTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TechnoStudySite {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", MyConstants.DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get("https://test.campus.techno.study/");
        driver.manage().window().maximize();
        WebElement cookieButton = driver.findElement(By.cssSelector("a[aria-label='dismiss cookie message']"));
        cookieButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);


        String sendKeyUserName = "daulet2030@gmail.com";
        String sendKeyPassword = "TechnoStudy123@";
        String sendKeySchoolDepartmentName = "High School";
        String codeInput = "HS-1";
        String sectionName = "Junior Classes";
        String sectionShortName = "Juniors";
        String saveButtonCss = "ms-save-button[class='ng-star-inserted']";
        String setupMenu = " .group-items > :nth-child(1)";
        String schoolSetupSecondLevel = " fuse-nav-vertical-collapsable:nth-child(2)";
        String departmentButtonThirdLevel = " > div > fuse-nav-vertical-item:nth-child(6) > a";
        String plusButtonToAdd = "ms-table-toolbar > div ms-add-button";
        String nameSchoolDepartmentElement = "[placeholder='GENERAL.FIELD.NAME']>input";
        String codeInputElement = "[placeholder='GENERAL.FIELD.CODE'] > input";
        String sectionElement = "//div[text()='Section']";
        String nameElementLocation = "ms-text-field[placeholder='GENERAL.FIELD.NAME']>input";
        String shortNameElementLocation = "ms-text-field[placeholder='GENERAL.FIELD.SHORTNAME']>input";
        String secondPersonName = "Senior Classes";
        String secondPersonShortName = "Senior Classes";
        String trashIcon = "tbody > tr:first-child td:nth-child(5) ms-delete-button";


        WebElement userName = driver.findElement(By.cssSelector("input[formcontrolname='username']"));
        userName.sendKeys(sendKeyUserName);
        WebElement password = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
        password.sendKeys(sendKeyPassword);
        driver.findElement(By.cssSelector("button[aria-label='LOGIN']")).click();

        ////////////////////////////////////////////////////    CLICKING SETUP MENU    /////////////////////////////////////////////////
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(setupMenu)));
        driver.findElement(By.cssSelector(setupMenu)).click();

        ////////////////////////////////////////////////////    CLICKING SCHOOL SETUP MENU    /////////////////////////////////////////////////
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(setupMenu + schoolSetupSecondLevel)));
        driver.findElement(By.cssSelector(setupMenu+schoolSetupSecondLevel)).click();

        ////////////////////////////////////////////////////    CLICKING DEPARTMENT    /////////////////////////////////////////////////
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(setupMenu+schoolSetupSecondLevel+departmentButtonThirdLevel)));
        driver.findElement(By.cssSelector(setupMenu+schoolSetupSecondLevel+departmentButtonThirdLevel)).click();

        ////////////////////////////////////////////////////    CLICKING ADD BUTTON    /////////////////////////////////////////////////
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(plusButtonToAdd)));
        driver.findElement(By.cssSelector(plusButtonToAdd)).click();

        //////////////////////////////////////////    SENDING THE KEY "High School" AND CODE "HS-1"   /////////////////////////////////////////////////
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(nameSchoolDepartmentElement)));
        driver.findElement(By.cssSelector(nameSchoolDepartmentElement)).sendKeys(sendKeySchoolDepartmentName);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(codeInputElement)));
        driver.findElement(By.cssSelector(codeInputElement)).sendKeys(codeInput);

        driver.findElement(By.xpath(sectionElement)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cdk-overlay-pane ms-table-toolbar > div ms-add-button fa-icon")));
        driver.findElement(By.cssSelector("div.cdk-overlay-pane ms-table-toolbar > div ms-add-button fa-icon")).click();

        /////////////////////////////            SECTION  NAME AND SHORT NAME           /////////////////////////////////

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(nameElementLocation)));
        driver.findElement(By.cssSelector(nameElementLocation)).sendKeys(sectionName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(shortNameElementLocation)));
        driver.findElement(By.cssSelector(shortNameElementLocation)).sendKeys(sectionShortName);

        WebElement addButton = driver.findElement(By.cssSelector("div[fxflexalign='center']"));
        addButton.click();

        ///////////////           Creating another section with the name "Senior Classes" and the short name "Seniors" similarly        /////////////////////////

        driver.findElement(By.cssSelector(nameElementLocation)).sendKeys(secondPersonName);
        driver.findElement(By.cssSelector(shortNameElementLocation)).sendKeys(secondPersonShortName);
        driver.findElement(By.cssSelector(saveButtonCss)).click();

        /////////////////////////////////         Verify if the department with the name "High School" created  ///////////////////////////

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[role='alertdialog']"), "School Department successfully created"));
        WebElement verifyingCreate = driver.findElement(By.cssSelector("div[role='alertdialog']"));
        System.out.println(verifyingCreate.getText());

        /////////////////////////////////////           Clicking on "+" button AGAIN  AND type "High School" for the name of the department and "HS-1" for the code AGAIN      //////////////////////////////////////////

        driver.findElement(By.cssSelector(plusButtonToAdd)).click();
        driver.findElement(By.cssSelector(nameSchoolDepartmentElement)).sendKeys("High School");
        driver.findElement(By.cssSelector(codeInputElement)).sendKeys("HS-1");
        driver.findElement(By.cssSelector(saveButtonCss)).click();

        //wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[role='alertdialog']"), "There is already Department with 'High School' name"));
        // WebElement verifyingUpdate = driver.findElement(By.cssSelector("div[role='alertdialog']"));
        // System.out.println(driver.findElement(By.cssSelector("div[role='alertdialog']")).getText());
        driver.findElement(By.cssSelector("[aria-label='Close dialog']")).click();

        /////////////////////////////////////////////////////      EDIT BUTTON      /////////////////////////////////////////////////////
        driver.findElement(By.cssSelector("tbody[role='rowgroup']>tr:first-child ms-edit-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(nameSchoolDepartmentElement)));
        driver.findElement(By.cssSelector(nameSchoolDepartmentElement)).click();
        driver.findElement(By.cssSelector(nameSchoolDepartmentElement)).clear();
        driver.findElement(By.cssSelector(nameSchoolDepartmentElement)).sendKeys("High School Classes");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(codeInputElement)));
        driver.findElement(By.cssSelector(codeInputElement)).clear();
        driver.findElement(By.cssSelector(codeInputElement)).sendKeys("HSC-1");
        driver.findElement(By.cssSelector(saveButtonCss)).click();

        /////////////////////////////////////////////////////      VERIFYING  School Department successfully WAS    updated     /////////////////////////////////////////////////////

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[role='alertdialog']"), "School Department successfully updated"));
        // WebElement verifyingUpdate = driver.findElement(By.cssSelector("div[role='alertdialog']"));
        System.out.println(driver.findElement(By.cssSelector("div[role='alertdialog']")).getText());

//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tbody[role='rowgroup']>tr:first-child div[fxlayout=row] ms-delete-button path")));
//        driver.findElement(By.cssSelector("tbody[role='rowgroup']>tr:first-child div[fxlayout=row] ms-delete-button path")).click();

        /////////////////////////////////////////////////////      cliking trash ICON     /////////////////////////////////////////////////////

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(trashIcon)));
        driver.findElement(By.cssSelector(trashIcon)).click();

        String confirmDeleting = "button[type='submit']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(confirmDeleting)));
        driver.findElement(By.cssSelector(confirmDeleting)).click();

        //wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//td[text()='High School Classes'"), "High School Classes"));
        //System.out.println(driver.findElement(By.xpath("//td[text()='High School Classes'")).getText());


        //driver.quit();
    }
}

