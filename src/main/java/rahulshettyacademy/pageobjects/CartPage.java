package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(xpath="//li[@class='totalRow']/button")
    WebElement checkoutEle;
    @FindBy(css=".cart h3")
    private List<WebElement> cartProducts;
    public CartPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	//initialize variables
	PageFactory.initElements(driver, this);
    }
    
    public Boolean verifyProductDisplay(String productName) {
	return cartProducts.stream().anyMatch(cartProduct->cartProduct
		.getText().equalsIgnoreCase(productName));
    }
    
    public CheckoutPage goToCheckout() {
	checkoutEle.click();
	return new CheckoutPage(driver);
    }

    
}
