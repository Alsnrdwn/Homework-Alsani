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

public class stepFilter {
    WebDriver driver;
    String baseUrl = "https://saucedemo.com/";

    @Given("Halaman produk SauceDemo without filter")
    public void halaman_produk_sauce_demo_without_filter(){
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

    @When("Click tombol filter")
    public void click_tombol_filter() {
        driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select")).click();
    }

    @And("Click pilihan dari low to high price")
    public void click_pilihan_dari_low_to_high_Price() {
        driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[3]")).click();
    }

    @Then("User is on product page with filtered product")
    public void user_is_on_product_page_with_filtered_product() {
        //Assert filter pilihan
        String harga = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")).getText();
        Assert.assertEquals(harga, "$7.99");

        driver.quit();
    }
}
