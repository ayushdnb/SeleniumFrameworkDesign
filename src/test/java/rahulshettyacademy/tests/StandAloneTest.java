package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.j2objc.annotations.UsedByNative;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {
    
    String userEmail="a13bc@gmail.com";
    private String password="Rahulshetty@1";
    String productName="ZARA COAT 3";
    public static void main(String[] args) {
	StandAloneTest sat=new StandAloneTest();
	WebDriverManager.chromedriver().setup();
	WebDriver driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	driver.manage().window().maximize();
	System.out.println("1st Page");
	driver.get("https://rahulshettyacademy.com/client");
	LandingPage landingPage=new LandingPage(driver);
	driver.findElement(By.id("userEmail")).sendKeys(sat.userEmail);
	driver.findElement(By.id("userPassword")).sendKeys(sat.getPassword());
	driver.findElement(By.id("login")).click();
	System.out.println("2nd Page");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	List<WebElement> productCards=driver.findElements(By.cssSelector(".mb-3"));
	WebElement selectedProduct=productCards.stream().filter(s->s.findElement(By.cssSelector("b"))
					.getText().equals(sat.productName)).findFirst().orElse(null);
	selectedProduct.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cart h3"));
	Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct
					.getText().equalsIgnoreCase(sat.productName));
	Assert.assertTrue(match);
	driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='left mt-1'] p")));
	Actions a=new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	driver.findElement(By.cssSelector(".action__submit")).click();
	String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
