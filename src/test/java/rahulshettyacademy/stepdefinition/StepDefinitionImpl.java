package rahulshettyacademy.stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalouge;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalouge productcatalouge;
    public ConfirmationPage confirmationPage;
    public CartPage cartPage;
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String userName, String password) {
        productcatalouge = landingPage.lognApplicaton(userName, password);
    }

    @When("^I add product (.+) from Cart$")
    public void I_add_product_to_Cart(String productName) {
        List<WebElement> selectedProducts = productcatalouge.getProductList();
        productcatalouge.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void chechout_submit_order(String productName) {
        cartPage = productcatalouge.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string) {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }

    // ===== New Steps for Error Validation Scenarios ===== //

    @When("I login with invalid credentials")
    public void i_login_with_invalid_credentials(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> data = dataTable.asLists();
        String email = data.get(1).get(0);
        String password = data.get(1).get(1);
        landingPage.lognApplicaton(email, password);
    }

    @Then("I should see an error message {string}")
    public void i_should_see_error_message(String expectedMessage) {
        String actualError = landingPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedMessage);
    }

    @Then("I should not see product {string} in the cart")
    public void i_should_not_see_product_in_cart(String productName) {
        cartPage = productcatalouge.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertFalse(match);
    }
}
