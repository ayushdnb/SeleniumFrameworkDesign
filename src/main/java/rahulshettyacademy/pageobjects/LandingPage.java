package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	//initialize variables
	PageFactory.initElements(driver, this);
    }
    //Page Factory
    @FindBy(id="userEmail")
    WebElement userEmail;
    @FindBy(id="userPassword")
    WebElement userPassword;
    @FindBy(id="login")
    WebElement submit;
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;
    
    public ProductCatalouge lognApplicaton(String email,String password) {
	userEmail.sendKeys(email);
	userPassword.sendKeys(password);
	submit.click();
	ProductCatalouge productcatalouge=new ProductCatalouge(driver);
	return productcatalouge;
    }
    
    public String getErrorMessage() {
	waitForWebElementToAppear(errorMessage);
	return errorMessage.getText();
    }
    
    public void goTo() {
	driver.get("https://rahulshettyacademy.com/client");
    }
}
