package rahulshettyacademy.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalouge;

public class ErrorValidationsTest extends BaseTest {
    String userEmail="a13bc@gmail.com";
    private String password="Rahulshetty@1";
    
    @Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
    public  void loginErrorValidation() throws IOException {
	System.out.println("Landing Page");
	landingPage.lognApplicaton("a113bc@gmail.com","Rahu2lshetty@1"); 
	System.out.println(landingPage.getErrorMessage());
	Assert.assertEquals("Incorrect email password.",landingPage.getErrorMessage());	
    }
    
    @Test
    public  void productErrorValidation() throws IOException {
	String productName="ZARA COAT 3";
	System.out.println("Landing Page");
	ProductCatalouge productcatalouge=landingPage.lognApplicaton("a13bc@gmail.com","Rahulshetty@1"); 
	System.out.println("Product Catalouge Page");	
	List<WebElement> selectedProducts=productcatalouge.getProductList();
	productcatalouge.addProductToCart(productName);
	CartPage cartPage=productcatalouge.goToCartPage();
	System.out.println("Cart Page");
	Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
	Assert.assertFalse(match);
    }

}
