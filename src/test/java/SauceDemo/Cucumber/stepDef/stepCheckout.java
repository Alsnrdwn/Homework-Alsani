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

public class stepCheckout {
    WebDriver driver;
    String baseUrl = "https://saucedemo.com";

    @Given("Halaman awal produk SauceDemo")
    public void halaman_awal_produk_sauce_demo(){
        WebDriverManager.edgedriver().setup();

        //Membuka browser dan Assert URL
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
        String loginpageassert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginpageassert, "Swag Labs");

        //Mengisi Form Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

        //Assert berhasil login
        String produk = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(produk, "Products");
    }

    @When("Menambahkan produk ke keranjang")
    public void menambahkan_produk_ke_keranjang() {
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
    }

    @And("Click cart button")
    public void click_cart_button() {
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
    }

    @Then("User is on cart page")
    public void user_is_on_cart_page() {
        String cart = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(cart, "Your Cart");
    }

    @When("Click continue button")
    public void click_continue_button() {
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
    }

    @Then("User is on order data form page")
    public void user_is_on_order_data_form_page() {
        String pemesan = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(pemesan, "Checkout: Your Information");
    }

    @When("Input first name")
    public void inputFirstName() {
        driver.findElement(By.id("first-name")).sendKeys("Alsani");
    }

    @And("Input last name")
    public void input_last_name() {
        driver.findElement(By.id("last-name")).sendKeys("Ridwana");
    }

    @And("Input postal code")
    public void input_postal_code() {
        driver.findElement(By.id("postal-code")).sendKeys("40526");
    }

    @And("Click overview button")
    public void click_overview_button() {
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
    }

    @Then("User is on overview page")
    public void user_is_on_overview_page() {
        String rangkuman = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(rangkuman, "Checkout: Overview");
    }

    @When("Click finish button")
    public void click_finish_button() {
        driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();
    }

    @Then("User is on checkout complete page")
    public void user_is_on_checkout_complete_page() {
        String order = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).getText();
        Assert.assertEquals(order, "Thank you for your order!");

        driver.quit();
    }
}
