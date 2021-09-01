package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Approve {
    private WebDriver driver;

    public void setUp(String url){
        System.setProperty("webdriver.chrome.driver",".\\src\\test\\java\\resources\\chromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        WebElement logginButton = driver.findElement(By.xpath("//*[@id=\"main\"]/section/div/div/div[1]/a"));
        logginButton.click();
        WebElement userName = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        userName.sendKeys("angie.giceth");
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("123456");
        WebElement log = driver.findElement(By.xpath("//*[@id=\"login_button\"]"));
        log.click();
        WebElement approveButton = driver.findElement(By.xpath("//*[@id=\"allow_authentication\"]"));
        approveButton.click();
        driver.quit();
    }

}
