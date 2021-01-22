package Project15;

import JavaTesting.MyConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

public class TechnoStudyTesting {
    private WebDriverWait wait;
    private WebDriver driver;
    private String sendKeyUserName = "daulet2030@gmail.com";
    private String sendKeyPassword = "TechnoStudy123@";
    private String sendKeySchoolDepartmentName = "High School";
    private String codeInput = "HS-1";
    private String sectionName = "Junior Classes";
    private String sectionShortName = "Juniors";
    private String saveButtonCss = "ms-save-button[class='ng-star-inserted']";
    private String setupMenu = " .group-items > :nth-child(1)";
    private String schoolSetupSecondLevel = " fuse-nav-vertical-collapsable:nth-child(2)";
    private String departmentButtonThirdLevel = " > div > fuse-nav-vertical-item:nth-child(6) > a";
    private String plusButtonToAdd = "ms-table-toolbar > div ms-add-button";
    private String nameSchoolDepartmentElement = "[placeholder='GENERAL.FIELD.NAME']>input";
    private String codeInputElement = "[placeholder='GENERAL.FIELD.CODE'] > input";
    private String sectionElement = "//div[text()='Section']";
    private String nameElementLocation = "ms-text-field[placeholder='GENERAL.FIELD.NAME']>input";
    private String shortNameElementLocation = "ms-text-field[placeholder='GENERAL.FIELD.SHORTNAME']>input";
    private String secondPersonName = "Senior Classes";
    private String secondPersonShortName = "Senior Classes";
    private String trashIcon = "tbody > tr:first-child td:nth-child(5) ms-delete-button";

    @BeforeClass(groups={"functional"})
    public void checkingAllMethodAvailable(ITestContext context) {
        ITestNGMethod[] allTestMethods = context.getAllTestMethods();
        for (ITestNGMethod methods : allTestMethods) {
            System.out.println(methods.getMethodName());
            System.out.println("Before class Test");
        }
    }
    @BeforeMethod
    public void beforeMethod(XmlTest test){
        String testName = test.getName();
        System.out.println(testName);
    }
    @AfterMethod
    public void afterMethod(ITestResult result){
        System.out.println("Test status is " + result.getStatus());
    }
    @BeforeClass(groups={"functional"})
    public void setUpWebSite(ITestContext context){
        System.setProperty("webdriver.chrome.driver", MyConstants.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get( "https://test.campus.techno.study/" );

        wait = new WebDriverWait(driver, 5);
        WebElement cookieButton = driver.findElement(By.cssSelector("a[aria-label='dismiss cookie message']"));
        cookieButton.click();
//        System.out.println(context.getSuite().getXmlSuite().getFileName());
//        ITestNGMethod[] allTestMethods = context.getAllTestMethods();
//        System.out.println(allTestMethods);
        System.out.println("Smoke test working as expected");
    }
//    @AfterClass
//    public void closeWindow(){ driver.quit();
//    }

    @Parameters({"login", "password"})
    @Test(groups={"functional"})
    public void loginTestCase(@Optional("daulet2030@gmail.com") String login, @Optional("TechnoStudy123@") String password){
        driver.findElement(By.cssSelector("input[data-placeholder=\"Username\"]")).sendKeys(login);
        driver.findElement(By.cssSelector("input[data-placeholder=\"Password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();
        System.out.println("You successfully login !!!");
    }
    @Test(dependsOnMethods = {"loginTestCase"})
    public void clickingSetUpMenu(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(setupMenu)));
        driver.findElement(By.cssSelector(setupMenu)).click();
        System.out.println("You clicked on setup button !!!");
    }
    @Test(dependsOnMethods = {"clickingSetUpMenu"})
    public void clickingSchoolSetupMenu(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(setupMenu + schoolSetupSecondLevel)));
        driver.findElement(By.cssSelector(setupMenu+schoolSetupSecondLevel)).click();
        System.out.println("You clicked on school setup button !!!");
    }
    @Test(dependsOnMethods = {"clickingSchoolSetupMenu"})
    public void clickingOnDepartment(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(setupMenu+schoolSetupSecondLevel+departmentButtonThirdLevel)));
        driver.findElement(By.cssSelector(setupMenu+schoolSetupSecondLevel+departmentButtonThirdLevel)).click();
        System.out.println("You clicked on DEPARTMENT");
    }
    @Test(dependsOnMethods = {"clickingOnDepartment"})
    public void clickingAddButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(plusButtonToAdd)));
        driver.findElement(By.cssSelector(plusButtonToAdd)).click();
        System.out.println("You clicked Add button");
    }

    //////////////////////////////////////////    SENDING THE KEY "High School" AND CODE "HS-1"   /////////////////////////////////////////////////
    @Test(dependsOnMethods = {"clickingAddButton"})
    public void sendingAKey(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(nameSchoolDepartmentElement)));
        driver.findElement(By.cssSelector(nameSchoolDepartmentElement)).sendKeys(sendKeySchoolDepartmentName);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(codeInputElement)));
        driver.findElement(By.cssSelector(codeInputElement)).sendKeys(codeInput);
        driver.findElement(By.xpath(sectionElement)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cdk-overlay-pane ms-table-toolbar > div ms-add-button fa-icon")));
        driver.findElement(By.cssSelector("div.cdk-overlay-pane ms-table-toolbar > div ms-add-button fa-icon")).click();
        System.out.println("You fill up the input with High School and CODE HS-1 ");
    }
    @Test(dependsOnMethods = {"sendingAKey"})
    public void nameAndShortName(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(nameElementLocation)));
        driver.findElement(By.cssSelector(nameElementLocation)).sendKeys(sectionName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(shortNameElementLocation)));
        driver.findElement(By.cssSelector(shortNameElementLocation)).sendKeys(sectionShortName);
        WebElement addButton = driver.findElement(By.cssSelector("div[fxflexalign='center']"));
        addButton.click();
        System.out.println("You added name and short name");
    }
    @Test(dependsOnMethods = {"nameAndShortName"})
    public void createAnotherSection(){
        driver.findElement(By.cssSelector(nameElementLocation)).sendKeys(secondPersonName);
        driver.findElement(By.cssSelector(shortNameElementLocation)).sendKeys(secondPersonShortName);
        driver.findElement(By.cssSelector(saveButtonCss)).click();
    }
    @Test(dependsOnMethods = {"createAnotherSection"})
    public void verifying(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[role='alertdialog']"), "School Department successfully created"));
        WebElement verifyingCreate = driver.findElement(By.cssSelector("div[role='alertdialog']"));
        String verifyingText = verifyingCreate.getText();
        System.out.println(verifyingCreate.getText());
        Assert.assertEquals(verifyingText, "School Department successfully created");
    }
    @Test(dependsOnMethods = {"verifying"})
    public void clickingButtonAgain(){
        driver.findElement(By.cssSelector(plusButtonToAdd)).click();
        driver.findElement(By.cssSelector(nameSchoolDepartmentElement)).sendKeys("High School");
        driver.findElement(By.cssSelector(codeInputElement)).sendKeys("HS-1");
        driver.findElement(By.cssSelector(saveButtonCss)).click();
        driver.findElement(By.cssSelector("[aria-label='Close dialog']")).click();
    }
    @Test(dependsOnMethods = {"clickingButtonAgain"})
    public void editButton(){
        driver.findElement(By.cssSelector("tbody[role='rowgroup']>tr:first-child ms-edit-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(nameSchoolDepartmentElement)));
        driver.findElement(By.cssSelector(nameSchoolDepartmentElement)).click();
        driver.findElement(By.cssSelector(nameSchoolDepartmentElement)).clear();
        driver.findElement(By.cssSelector(nameSchoolDepartmentElement)).sendKeys("High School Classes");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(codeInputElement)));
        driver.findElement(By.cssSelector(codeInputElement)).clear();
        driver.findElement(By.cssSelector(codeInputElement)).sendKeys("HSC-1");
        driver.findElement(By.cssSelector(saveButtonCss)).click();
    }
    @Test(dependsOnMethods = {"editButton"})
    public void updatedVerifying(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[role='alertdialog']"), "School Department successfully updated"));
        // WebElement verifyingUpdate = driver.findElement(By.cssSelector("div[role='alertdialog']"));
        System.out.println(driver.findElement(By.cssSelector("div[role='alertdialog']")).getText());
    }
    @Test(dependsOnMethods = {"updatedVerifying"})
    public void clickingTrashIcon(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(trashIcon)));
        driver.findElement(By.cssSelector(trashIcon)).click();

        String confirmDeleting = "button[type='submit']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(confirmDeleting)));
        driver.findElement(By.cssSelector(confirmDeleting)).click();
        System.out.println("It was deleted successfully !!!");
    }
}
