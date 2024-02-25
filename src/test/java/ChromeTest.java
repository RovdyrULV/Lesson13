import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

class ChromeTest {

    WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mts.by/");
        WebElement cookieaccept = driver.findElement(By.xpath("/html/body/div[6]/main/div/div[2]/div/div[2]/button[3]"));
        cookieaccept.click();

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
    
    @Test
     void blockNameTest() {
        WebElement onlinereplenishmentwithoutcommission = driver.findElement(By.xpath("/html/body/div[6]/main/div/div[4]/div[1]/div/div/div[2]/section/div/h2"));
        String onlinereplenishmentwithoutcommissiontext = onlinereplenishmentwithoutcommission.getAttribute("textContent");
        Assertions.assertEquals(onlinereplenishmentwithoutcommissiontext, "Онлайн пополнение без комиссии");
        // Альтернатива?
        blockNameCheck(ExpectedConditions.textToBePresentInElement(onlinereplenishmentwithoutcommission, "Онлайн пополнение без комиссии"));
    }

    private void blockNameCheck(ExpectedCondition<Boolean> text) {
    }

    @Test
    void availabilityOfPaymentSystems(){
        WebElement paymentSystems = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul"));
        //WebElement paymentSystems = driver.findElement(By.xpath("/html/body/div[6]/main/div/div[3]/div[1]/div/div/div[2]/section/div/div[2]"));
        var paylist = paymentSystems.getAttribute("children");
        System.out.println(paylist);
    }


    @Test
    void checkTheLinkMoreAboutTheService() {
        WebElement linkAboutTheService = driver.findElement(By.xpath("/html/body/div[6]/main/div/div[4]/div[1]/div/div/div[2]/section/div/a"));
        linkAboutTheService.click();
        Assertions.assertEquals(driver.getCurrentUrl(),"https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");
    }

    @Test
    void communicationServicesTest(){
        WebElement enternumber = driver.findElement(By.xpath("//*[@id=\"connection-phone\"]"));
        //WebElement enternumber = driver.findElement(By.xpath("/html/body/div[6]/main/div/div[3]/div[1]/div/div/div[2]/section/div/div[1]/div[2]/form[1]/div[1]/input"));
        enternumber.click();
        enternumber.sendKeys("297777777");
        WebElement entersum = driver.findElement(By.xpath("//*[@id=\"connection-sum\"]"));
        //WebElement entersum = driver.findElement(By.xpath("/html/body/div[6]/main/div/div[3]/div[1]/div/div/div[2]/section/div/div[1]/div[2]/form[1]/div[2]/input"));
        entersum.click();
        entersum.sendKeys("50");
        //WebElement resumebutton = driver.findElement(By.xpath("/html/body/div[6]/main/div/div[3]/div[1]/div/div/div[2]/section/div/div[1]/div[2]/form[1]/button"));
        WebElement resumebutton = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        resumebutton.click();
    }
    //Xpath почему-то полный путь не работал, не знаю стоит ли еще добавлять Assertion.
}
