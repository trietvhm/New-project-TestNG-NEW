package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    WebDriver driver;

    // khoi tao trinh duyet san
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_() {
        Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());
        driver.quit();
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
