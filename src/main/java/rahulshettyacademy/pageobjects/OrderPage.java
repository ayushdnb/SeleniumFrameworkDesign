package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(xpath="//li[@class='totalRow']/button")
    WebElement checkoutEle;
    @FindBy(css="tr td:nth-child(3)")
    private List<WebElement> productNames;
    public OrderPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	//initialize variables
	PageFactory.initElements(driver, this);
    }
    public Boolean verifyOrderDisplay(String productName) {
	return productNames.stream().anyMatch(cartProduct->cartProduct
		.getText().equalsIgnoreCase(productName));
    }
}
