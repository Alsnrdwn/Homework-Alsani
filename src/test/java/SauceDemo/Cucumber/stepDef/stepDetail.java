package SauceDemo.Cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class stepDetail {
    WebDriver driver;
    String baseUrl = "https://saucedemo.com/";

    @Given("Halaman produk SauceDemo")
    public void halaman_produk_sauce_demo(){
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

    @When("Click product name")
    public void click_product_name() {
        //Membuka detail produk
        driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();
    }

    @Then("User is on Product Detail Page")
    public void user_is_on_product_detail_page() {
        //Assert berhasil membuka
        String back = driver.findElement(By.xpath("//*[@id=\"back-to-products\"]")).getText();
        Assert.assertEquals(back, "Back to products");

        driver.quit();
    }
}
