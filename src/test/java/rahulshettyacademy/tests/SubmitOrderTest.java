package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalouge;

public class SubmitOrderTest extends BaseTest {
    String userEmail="a13bc@gmail.com";
    private String password="Rahulshetty@1";
    String productName="ZARA COAT 3";
   
    @Test(dataProvider="getData",groups= {"Purchase"})
    public  void submitOrder(HashMap<String,String> input) throws IOException {
	ProductCatalouge productcatalouge=landingPage.lognApplicaton(input.get("email"),input.get("password")); 
	List<WebElement> selectedProducts=productcatalouge.getProductList();
	productcatalouge.addProductToCart(input.get("product"));
	CartPage cartPage=productcatalouge.goToCartPage();
	Boolean match=cartPage.verifyProductDisplay(input.get("product"));
	Assert.assertTrue(match);
	CheckoutPage checkoutPage=cartPage.goToCheckout();
	checkoutPage.selectCountry("india");
	ConfirmationPage confirmationPage=checkoutPage.submitOrder();
	String confirmMessage=confirmationPage.getConfirmationMessage();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
    @Test(dependsOnMethods= {"submitOrder"})
    public void orderHistoryTest() {
	ProductCatalouge productcatalouge=landingPage.lognApplicaton("a13bc@gmail.com","Rahulshetty@1");
	OrderPage orderPage=productcatalouge.goToOrdersPage();
	Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }
    @DataProvider
    public Object[][] getData() throws Exception{
//	HashMap<Object,Object> map1=new HashMap<>();
//	map1.put("email","a13bc@gmail.com");
//	map1.put("password","Rahulshetty@1");
//	map1.put("product","ZARA COAT 3");
//	HashMap<Object,Object> map2=new HashMap<>();
//	map2.put("email","a13bc@gmail.com");
//	map2.put("password","Rahulshetty@1");
//	map2.put("product","ADIDAS ORIGINAL");
	List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
    }
    
    public String getScreenshot(String testCaseName) throws IOException {
	TakesScreenshot ts=(TakesScreenshot)driver;
	File source =ts.getScreenshotAs(OutputType.FILE);
	File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
	FileUtils.copyFile(source,file);
	return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }
    

}
