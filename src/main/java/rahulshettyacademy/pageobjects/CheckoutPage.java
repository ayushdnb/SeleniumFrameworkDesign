package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver,this);
	// TODO Auto-generated constructor stub
    }

    @FindBy(css="[placeholder='Select Country']")
    WebElement country;
    @FindBy(css=".action__submit")
    WebElement submit;
    @FindBy(css="body > app-root:nth-child(1) > app-order:nth-child(2) > section:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > section:nth-child(2) > button:nth-child(3) > span:nth-child(1)")
    WebElement selectCountry;
    
    public void selectCountry(String countryName) {
	if (driver == null) {
	        System.out.println("Driver is null at selectCountry");
	    }
	Actions a=new Actions(driver);
	a.sendKeys(country,countryName).build().perform();
	selectCountry.click();
    }
    
    public ConfirmationPage submitOrder() {
	submit.click();
	return new ConfirmationPage(driver);
    }
}
