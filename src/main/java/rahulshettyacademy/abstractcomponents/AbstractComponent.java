package rahulshettyacademy.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
    WebDriver driver;
    WebDriverWait wait;
    public AbstractComponent(WebDriver driver) {
	this.driver=driver;
	this.wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	PageFactory.initElements(driver,this);
    }
    //driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
    WebElement cartHeader;
    @FindBy(css="[routerlink*='myorders']")
    WebElement orderHeader;

    public void waitForElementToAppear(By findBy) {
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForWebElementToAppear(WebElement findBy) {
	wait.until(ExpectedConditions.visibilityOf(findBy));
    }
    
    public void waitForElementToDisappear(WebElement webElement) {
	wait.until(ExpectedConditions.invisibilityOf(webElement));
    }
    
    public CartPage goToCartPage() {
	cartHeader.click();
	CartPage cartPage=new CartPage(driver);
	return cartPage;
    }
    public OrderPage goToOrdersPage() {
	orderHeader.click();
	OrderPage orderPage=new OrderPage(driver);
	return orderPage;
    }
}
