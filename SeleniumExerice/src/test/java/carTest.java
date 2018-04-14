import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by marco on 14/04/2018.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class carTest {
    static WebDriver driver;
    @BeforeClass
    public static void before(){
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        //driver = new HtmlUnitDriver();
        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");

        System.setProperty("webdriver.chrome.driver","C:\\Users\\marco\\Documents\\skole\\sem2\\test\\test_SeleniumExerise\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/");
    }
/*
    @Test
    public void xxxxx(){

        driver.get("http://the-internet.herokuapp.com/login");
        WebElement un = driver.findElement(By.name("username"));
        WebElement pw = driver.findElement(By.name("password"));
        un.sendKeys("tomsmith");
        pw.sendKeys("SuperSecretPassword!");
        pw.submit();
        //System.out.println("Page title is: " + driver.getTitle());
        Assert.assertTrue("http://the-internet.herokuapp.com/secure".equals(driver.getCurrentUrl()));


        WebElement logout = driver.findElement(By.className("button"));
        logout.click();
        Assert.assertTrue("http://the-internet.herokuapp.com/login".equals(driver.getCurrentUrl()));
    }*/

    @Test
    public void test1(){
        (new WebDriverWait(driver, 4)).until((ExpectedCondition<Boolean>) (WebDriver d) -> {
            WebElement tableBody = driver.findElement(By.id("tbodycars"));
            List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
            Assert.assertThat(rows.size(), is(5));
            return true;
        });
    }

    @Test
    public void test2(){
        (new WebDriverWait(driver, 4)).until((ExpectedCondition<Boolean>) (WebDriver d) -> {
            WebElement filt = driver.findElement(By.id("filter"));
            filt.sendKeys("2002");
            WebElement tableBody = driver.findElement(By.id("tbodycars"));
            List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
            Assert.assertThat(rows.size(), is(2));
            filt.clear();
            filt.sendKeys(" ");
            rows = tableBody.findElements(By.tagName("tr"));
            Assert.assertThat(rows.size(), is(5));
            return true;
        });
    }

    @Test
    public void test3(){
        (new WebDriverWait(driver, 4)).until((ExpectedCondition<Boolean>) (WebDriver d) -> {
            WebElement filt = driver.findElement(By.id("h_year"));
            filt.click();
            WebElement tableBody = driver.findElement(By.id("tbodycars"));
            List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
            Assert.assertThat(rows.get(0).findElements(By.tagName("td")).get(0).getText(), is("938"));
            Assert.assertThat(rows.get(4).findElements(By.tagName("td")).get(0).getText(), is("940"));
            return true;
        });
    }
    @Test
    public void test4(){

        (new WebDriverWait(driver, 4)).until((ExpectedCondition<Boolean>) (WebDriver d) -> {
            WebElement tableBody = driver.findElement(By.id("tbodycars"));
            List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
            rows.get(1).findElements(By.tagName("a")).get(0).click();
            WebElement desc = driver.findElement(By.id("description"));
            desc.clear();
            desc.sendKeys("cool car");
            driver.findElement(By.id("save")).click();
            Assert.assertThat(rows.get(1).findElements(By.tagName("td")).get(5).getText(),is("cool car"));
            return true;
        });
    }
    @Test
    public void test5(){
        (new WebDriverWait(driver, 4)).until((ExpectedCondition<Boolean>) (WebDriver d) -> {
            driver.findElement(By.id("new")).click();
            driver.findElement(By.id("save")).click();
            Assert.assertThat(driver.findElement(By.id("submiterr")).getText(),is("All fields are required"));
            return true;
        });
    }
    @Test
    public void test6(){
        (new WebDriverWait(driver, 4)).until((ExpectedCondition<Boolean>) (WebDriver d) -> {
            driver.findElement(By.id("new")).click();
            driver.findElement(By.id("year")).sendKeys("2008");
            driver.findElement(By.id("registered")).sendKeys("2002-5-5");
            driver.findElement(By.id("make")).sendKeys("Kia");
            driver.findElement(By.id("model")).sendKeys("Rio");
            driver.findElement(By.id("description")).sendKeys("As new");
            driver.findElement(By.id("price")).sendKeys("31000");
            driver.findElement(By.id("save")).click();

            Assert.assertThat(driver.findElement(By.id("tbodycars")).findElements(By.tagName("tr")).get(5).getText(),is("942 2008 5/5/2002 Kia Rio As new 31.000,00 kr. Edit | Delete"));
            return true;
        });
    }

    @AfterClass
    public static void after(){
        //Close the browser
         driver.quit();
    }
}