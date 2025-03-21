package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class ProductCatalouge extends AbstractComponent {
    WebDriver driver;
    public ProductCatalouge(WebDriver driver) {
	super(driver);
	this.driver=driver;
	//initialize variables
	PageFactory.initElements(driver, this);
    }
    //Page Factory
    @FindBy(css=".mb-3")
    List<WebElement> products;
    @FindBy(css=".ng-animating")
    WebElement spinner;
    
    By productsBy=By.cssSelector(".mb-3");
    By addTocart=By.cssSelector("button[class='btn w-10 rounded']");
    By toastMessage=By.cssSelector("#toast-container");	
    public List<WebElement> getProductList(){
	waitForElementToAppear(productsBy);
	return products;
    }
    public WebElement getProductByName(String productName) {
	WebElement selectedProduct=getProductList().stream().filter(s->s.findElement(By.cssSelector("b"))
		.getText().equals(productName)).findFirst().orElse(null);
	return selectedProduct;
    }
    public void addProductToCart(String productName) {
	
	WebElement prod=getProductByName(productName);
	prod.findElement(addTocart).click();
	
	waitForElementToAppear(toastMessage);
	
	waitForElementToDisappear(spinner);
	
    }
    
  
}
