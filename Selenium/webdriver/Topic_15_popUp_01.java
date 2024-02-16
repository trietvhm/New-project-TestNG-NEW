package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_popUp_01 {
    WebDriver driver;

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Fixed_popUp_IN_DOM_01() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSecond(2);

        By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");

        //kiem tra login popup dang hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input"))
                .sendKeys("automationfc");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input"))
                .sendKeys("automationfc");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1.buttonLoading"))
                .click();


        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel"))
                .getText(), "Tài khoản không tồn tại!");

        // close popup
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();

        //kiem tra login popup khong hien thi
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }

    @Test
    public void TC_02_01_Fixed_popUp_IN_DOM_02() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSecond(2);

        By loginPopup = By.cssSelector("div#k-popup-account-login-mb div.modal-content");

        //kiem tra login popup dang hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login"))
                .sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password"))
                .sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login"))
                .click();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message"))
                .getText(), "Sai tên đăng nhập hoặc mật khẩu");

        // close popup
        driver.findElement(By.cssSelector("button.k-popup-account-close")).click();

        //kiem tra login popup khong con hien thi (invisible /hidden)
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());


    }

    @Test
    public void TC_023_Fixed_popUp_NOT_IN_DOM_01() {
        driver.get("https://tiki.vn/");

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        sleepInSecond(2);

        By loginPopup = By.cssSelector("div.ReactModal__Content");

        //kiem tra login popup dang hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        //login with email
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepInSecond(2);

        driver.findElement(By.cssSelector("input[type='email']")).clear();
        driver.findElement(By.cssSelector("input[type='password']")).clear();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']"))
                .click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]"))
                .getText(), "Email không được để trống");

        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[2]"))
                .getText(), "Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("button.btn-close")).click();
        sleepInSecond(2);

        //khi popup dong lai thi HTML khong con trong DOM(document object model) nua
    //    Assert.assertFalse(driver.findElement(loginPopup).isDisplayed()); // dung cach nay kiem tra se bi sai
        // neu nhu khong co trong DOM thi dung isDisplayed khong được
        // do findelement khong tim thay


        // findElement should not be used to look for non-present elements,
        // use findElements(By) and assert zero length response instead
        // khong tim thay khong danh fail\
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(loginPopup).size(),0);

    }

    @Test
    public void TC_024_Fixed_popUp_NOT_IN_DOM_02() {
        driver.get("https://www.facebook.com//");

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        By loginPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");

        //kiem tra login popup dang hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/child::img")).click();
        sleepInSecond(2);

        //khi popup dong lai thi HTML khong con trong DOM(document object model) nua
        //    Assert.assertFalse(driver.findElement(loginPopup).isDisplayed()); // dung cach nay kiem tra se bi sai
        // neu nhu khong co trong DOM thi dung isDisplayed khong được
        // do findelement khong tim thay


        // findElement should not be used to look for non-present elements,
        // use findElements(By) and assert zero length response instead
        // khong tim thay khong danh fail\
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(loginPopup).size(),0);

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
