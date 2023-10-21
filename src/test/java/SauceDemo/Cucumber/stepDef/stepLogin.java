package SauceDemo.Cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class stepLogin {
    WebDriver driver;
    String baseUrl = "https://saucedemo.com/";

    @Given("Halaman login SauceDemo")
    public void halaman_login_sauce_demo(){

        WebDriverManager.edgedriver().setup();

        //Membuka browser dan Assert URL
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
        String loginpageassert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginpageassert, "Swag Labs");

    }

    @When("Input username")
    public void input_username() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("Input password")
    public void input_password() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("Click login button")
    public void click_login_button() {
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }

    @Then("User is on product page")
    public void user_is_on_product_page() {
        //Assert berhasil login
        String produk = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(produk, "Products");

        driver.quit();
    }

    @And("Input invalid password")
    public void input_invalid_password() {
        driver.findElement(By.id("password")).sendKeys("sauce_secret");
    }

    @Then("User get error message")
    public void user_get_error_message() {
        //Assert gagal login
        String produk = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(produk, "Epic sadface: Username and password do not match any user in this service");

        driver.quit();
    }
}
