package SauceDemo.Java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class Checkout {
    @Test
    public void checkout(){
        WebDriver driver;
        String baseUrl = "https://saucedemo.com/";

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

        //Menambahkan ke keranjang
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

        //Assert berhasil menambahkan
        String cart = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(cart, "Your Cart");

        //Melanjutkan Checkout
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();

        //Assert berhasil ke halaman data pemesan
        String pemesan = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(pemesan, "Checkout: Your Information");

        //Mengisi form pemesan
        driver.findElement(By.id("first-name")).sendKeys("Alsani");
        driver.findElement(By.id("last-name")).sendKeys("Ridwana");
        driver.findElement(By.id("postal-code")).sendKeys("40526");
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();

        //Assert berhasil mengisi form
        String rangkuman = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(rangkuman, "Checkout: Overview");

        //Menyelesaikan Checkout
        driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();

        //Assert berhasil checkout
        String order = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).getText();
        Assert.assertEquals(order, "Thank you for your order!");

        driver.quit();
    }
}
