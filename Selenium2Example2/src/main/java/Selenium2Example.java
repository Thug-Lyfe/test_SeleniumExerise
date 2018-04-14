import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium2Example {
    WebDriver driver;


    @Before
    public void before(){
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        driver = new HtmlUnitDriver();

        System.setProperty("webdriver.chrome.driver","C:\\Users\\marco\\Desktop\\Newfolder\\skole\\sem2\\test\\chromedriver.exe");
        //driver = new ChromeDriver();
    }

    @Test
    public void test1(){


        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        //System.out.println("Page title is: " + driver.getTitle());
        Assert.assertTrue("Cheese! - Google-søgning".equals(driver.getTitle()));


        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        /*(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });*/

        // Should see: "cheese! - Google Search"
        //System.out.println("Page title is: " + driver.getTitle());


    }

    @Test
    public void test2(){

        driver.get("http://www.pornhub.com");
        WebElement element = driver.findElement(By.id("searchInput"));
        element.sendKeys("Cheese!");
        element.submit();
        //System.out.println("Page title is: " + driver.getTitle());
        Assert.assertTrue("Cheese! Porn Videos | Pornhub.com".equals(driver.getTitle()));

    }
    @Test
    public void loginTest(){

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
    }




    @After
    public void after(){
        //Close the browser
        driver.quit();
    }
}
