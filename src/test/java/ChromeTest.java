import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

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
        driver.get("https://www.mts.by/");
        WebElement cookieaccept = driver.findElement(By.xpath("/html/body/div[6]/main/div/div[2]/div/div[2]/button[3]"));
        cookieaccept.click();

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void communicationServicesIframeTest() {
        WebElement enternumber = driver.findElement(By.xpath("//*[@id=\"connection-phone\"]"));
        enternumber.click();
        enternumber.sendKeys("297777777");
        WebElement entersum = driver.findElement(By.xpath("//*[@id=\"connection-sum\"]"));
        entersum.click();
        entersum.sendKeys("50");
        WebElement emailtosend = driver.findElement(By.xpath("//*[@id=\"connection-email\"]"));
        emailtosend.click();
        emailtosend.sendKeys("Ivanov@gmail.com");
        WebElement resumebutton = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        resumebutton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement fr = driver.findElement(By.xpath("//iframe[@allowpaymentrequest][@style='visibility: visible;']"));
        driver.switchTo().frame(fr);
        WebElement paymentamount = driver.findElement(By.xpath("/html/body/app-root/div/div/app-payment-container/app-header/header/div/div/div"));
        Assertions.assertEquals(paymentamount.getAttribute("textContent"), "50.00 BYN");
        WebElement phonenumber = driver.findElement(By.xpath("/html/body/app-root/div/div/app-payment-container/app-header/header/div/div/p"));
        Assertions.assertEquals(phonenumber.getAttribute("innerText"), "Оплата: Услуги связи Номер:375297777777");
        WebElement inputcardnumber = driver.findElement(By.xpath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/app-card-input/form/div[1]/div[1]/app-input/div/div/div[1]/label"));
        Assertions.assertEquals(inputcardnumber.getAttribute("innerText"), "Номер карты");
        WebElement paymentsystems = driver.findElement(By.xpath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/app-card-input/form/div[1]/div[1]/app-input/div/div/div[2]/div"));
        paymentsystems.getAttribute("children");
        WebElement validity = driver.findElement(By.xpath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/app-card-input/form/div[1]/div[2]/div[1]/app-input/div/div/div[1]/label"));
        Assertions.assertEquals(validity.getAttribute("innerText"), "Срок действия");
        WebElement cvc = driver.findElement(By.xpath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/app-card-input/form/div[1]/div[2]/div[3]/app-input/div/div/div[1]/label"));
        Assertions.assertEquals(cvc.getAttribute("innerText"), "CVC");
        WebElement holdername = driver.findElement(By.xpath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/app-card-input/form/div[1]/div[3]/app-input/div/div/div[1]/label"));
        Assertions.assertEquals(holdername.getAttribute("innerText"), "Имя держателя (как на карте)");
        WebElement patbutton = driver.findElement(By.xpath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/button"));
        Assertions.assertEquals(patbutton.getAttribute("innerText"), "Оплатить 50.00 BYN");
    }

    @Test
    void communicationServicesEmptyFieldsTest(){
        WebElement enternumber = driver.findElement(By.xpath("//*[@id='connection-phone']"));
        Assertions.assertEquals(enternumber.getAttribute("placeholder"), "Номер телефона");
        WebElement entersum = driver.findElement(By.xpath("//*[@id=\"connection-sum\"]"));
        Assertions.assertEquals(entersum.getAttribute("placeholder"), "Сумма");
        WebElement emailtosend = driver.findElement(By.xpath("//*[@id=\"connection-email\"]"));
        Assertions.assertEquals(emailtosend.getAttribute("placeholder"), "E-mail для отправки чека");
    }

    @Test
    void homeInternetEmptyFieldsTest(){
        WebElement selectlist = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button"));
        selectlist.click();
        WebElement homeinternet = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[2]/p"));
        homeinternet.click();
        WebElement subscribernumber = driver.findElement(By.xpath("//*[@id=\"internet-phone\"]"));
        Assertions.assertEquals(subscribernumber.getAttribute("placeholder"), "Номер абонента");
        WebElement entersum = driver.findElement(By.xpath("//*[@id=\"connection-sum\"]"));
        Assertions.assertEquals(entersum.getAttribute("placeholder"), "Сумма");
        WebElement emailtosend = driver.findElement(By.xpath("//*[@id=\"connection-email\"]"));
        Assertions.assertEquals(emailtosend.getAttribute("placeholder"), "E-mail для отправки чека");
    }

    @Test
    void installmentPlanTest() {
        WebElement selectlist = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button"));
        selectlist.click();
        WebElement installmentplan = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[3]/p"));
        installmentplan.click();
        WebElement accountnumber = driver.findElement(By.xpath("//*[@id=\"score-instalment\"]"));
        Assertions.assertEquals(accountnumber.getAttribute("placeholder"), "Номер счета на 44");
        WebElement entersum = driver.findElement(By.xpath("//*[@id=\"connection-sum\"]"));
        Assertions.assertEquals(entersum.getAttribute("placeholder"), "Сумма");
        WebElement emailtosend = driver.findElement(By.xpath("//*[@id=\"connection-email\"]"));
        Assertions.assertEquals(emailtosend.getAttribute("placeholder"), "E-mail для отправки чека");
    }

    @Test
    void debtTest() {
        WebElement selectlist = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button"));
        selectlist.click();
        WebElement debt = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[4]/p"));
        debt.click();
        WebElement accountnumber = driver.findElement(By.xpath("//*[@id=\"score-arrears\"]"));
        Assertions.assertEquals(accountnumber.getAttribute("placeholder"), "Номер счета на 2073");
        WebElement entersum = driver.findElement(By.xpath("//*[@id=\"connection-sum\"]"));
        Assertions.assertEquals(entersum.getAttribute("placeholder"), "Сумма");
        WebElement emailtosend = driver.findElement(By.xpath("//*[@id=\"connection-email\"]"));
        Assertions.assertEquals(emailtosend.getAttribute("placeholder"), "E-mail для отправки чека");
    }
}
