package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 
        driver = new ChromeDriver(options);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
        driver.manage().window().maximize();
    }

    @FindBy(xpath = "//input[@placeholder='Search for Products, Brands and More']")
    WebElement SearchBox1;

    @FindBy(className = "zDPmFV")
    WebElement SearchBox2;

    @FindBy(xpath = "//div[@class='FPdoLc lJ9FBc']//input[@role='button']")
    WebElement GoogleSearchBtn;

    @FindBy(xpath = "//button[@type='submit' and @class='MJG8Up']")
    WebElement SearchBtn;

    @FindBy(xpath = "//div[text()='Popularity']")
    WebElement SortByPopularity;

    @FindBy(xpath = "//span[@class='Y1HWO0']/div")
    List<WebElement> RatingsElements;

    @FindBy(xpath = "//div[@class='cN1yYO']//div[@class='UkUFwK']")
    List<WebElement> DiscountEle;

    @FindBy(xpath = "//button[@title='Search for Products, Brands and More']")
    WebElement submitBtn;

    @FindBy(xpath = "//div[@class='col col-7-12']//div[@class='KzDlHZ']")
    List<WebElement> Titles;

    By Allitems = By.xpath("//div[@class='DOjaWF YJG4Cf']//div[@class='DOjaWF gdgoEp']");
    By Ele1 = By.xpath("//div[@class='YcSYyC']//div[@class='_6tw8ju']");

    @FindBy(xpath = "//section[@class='-5qqlC _2OLUF3']//div[@class='FtQCb2 _3Owiq+']/div[text()='Customer Ratings']/parent::div//parent::*//div[2]//div//div[1]//div[1]//label//div[1]")
    WebElement RatingCheckbox;

    @FindBy(xpath = "//div[@class='_5OesEi afFzxY']//span[2]")
    List<WebElement> TotalReviewsCount;

    @FindBy(xpath = "//div[@class='slAVV4']//div[@class='xgS27m']//following::a[@class='wjcEIp']")
    List<WebElement> TestCase03AllTitles;

    @Test(enabled = true , priority = 1)
    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        Wrappers MethodsWrap = new Wrappers();
        MethodsWrap.Wrapper_GoToUrl(driver,"https://www.flipkart.com/");
        SearchBox1.click();
        MethodsWrap.WaitUrlMatchesToUrl(driver,"https://www.flipkart.com/",30);
        MethodsWrap.Wrapper_sendkeys(SearchBox1,"Washing Machine");
        submitBtn.click();
        SortByPopularity.click();
        Thread.sleep(3000);
        MethodsWrap.waitForUrl(driver,"popularity",30);
        int RatingCount = 0;
        for(WebElement e : RatingsElements){
            float n = Float.parseFloat(e.getText());
            if(n <= 4.0){
                RatingCount++;
            }
        }
        System.out.println("Total items having ratings less than or equal to 4 is : " +RatingCount);
        System.out.println("End Test case: testCase01");
    }
    
    @FindBy(className = "_30XB9F")
    WebElement PopupCloseBtn;

    @Test(enabled = true , priority = 2)
    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        Wrappers MethodsWrap = new Wrappers();
        MethodsWrap.Wrapper_GoToUrl(driver,"https://www.flipkart.com/");
        MethodsWrap.waitForElementToBeClickable(driver,By.className("_1E5Hij"),30);
        PopupCloseBtn.click();
        SearchBox1.click();
        MethodsWrap.WaitUrlMatchesToUrl(driver,"https://www.flipkart.com/",30);
        MethodsWrap.Wrapper_sendkeys(SearchBox1,"iPhone");
        submitBtn.click();
        for(int j=1;j< DiscountEle.size();j++){
            WebElement a = DiscountEle.get(j);
            String[] nn = a.getText().split("%");
            int DisEle = Integer.parseInt(nn[0]);
            if(DisEle > 17){
                System.out.println(Titles.get(j).getText() + " : " + DiscountEle.get(j).getText());
            }
        }
            System.out.println("End Test case: testCase02");
    }

    @Test(enabled = true , priority = 3)
    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        Wrappers MethodsWrap = new Wrappers();
        MethodsWrap.Wrapper_GoToUrl(driver,"https://www.flipkart.com/");
        SearchBox1.click();
        MethodsWrap.WaitUrlMatchesToUrl(driver,"https://www.flipkart.com/",30);
        MethodsWrap.Wrapper_sendkeys(SearchBox1,"Coffee Mug");
        submitBtn.click();
        Thread.sleep(5000);
        // Create an instance of JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down by a certain amount (e.g., 1000 pixels)
        js.executeScript("window.scrollBy(0, 300);");
        Actions actions = new Actions(driver);
        actions.moveToElement(RatingCheckbox).click().build().perform();
        MethodsWrap.waitForElementToBeVisible(driver,Ele1,30);
        MethodsWrap.VisiBlityOfAllElements(driver,TestCase03AllTitles,30);
        LinkedHashMap<String,Integer> hashmap = new LinkedHashMap<>(100);
        for(int i=0;i<TestCase03AllTitles.size();i++)
        {
            int min = 0;
            String ss = TestCase03AllTitles.get(i).getText();
            for(int j=i;j<TotalReviewsCount.size();j++)
            {
                int n = TotalReviewsCount.get(j).getText().length() - 1;
                String s1 = TotalReviewsCount.get(j).getText().substring(1,n);
                if(s1.contains(","))
                {
                    String[] s2 = s1.split(",");
                    StringBuffer rn = new StringBuffer(s2[0]);
                    String s3 = rn.append(s2[1]).toString();
                    min = Integer.parseInt(s3);
                    hashmap.put(TestCase03AllTitles.get(i).getText(),min);
                    break;
                }else
                {
                    min =  Integer.parseInt(s1);
                    hashmap.put(TestCase03AllTitles.get(i).getText(),min);
                    break;
                }
            }
            hashmap.put(ss,min);
        }

        List<Map.Entry<String, Integer>> reviewList = new ArrayList<>(hashmap.entrySet());
        reviewList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        System.out.println("Top 5 Reviews:");
        for (int i = 0; i < 5 && i < reviewList.size(); i++) {
            Map.Entry<String, Integer> entry = reviewList.get(i);
            System.out.println("Title: " + entry.getKey() + ", Score: " + entry.getValue());
        }

        System.out.println("End Test case: testCase03");
    }

    @AfterTest(enabled = true)
    public void endTest()
    {
        driver.quit();
    }
}