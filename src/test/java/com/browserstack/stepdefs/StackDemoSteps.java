package com.browserstack.stepdefs;

import com.browserstack.pageobjects.HomePage;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.yaml.snakeyaml.Yaml;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;

public class StackDemoSteps {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static HomePage homePage;

    public static String userName, accessKey;
    public static Map<String, Object> browserStackYamlMap;
    public static final String USER_DIR = "user.dir";

    public StackDemoSteps() {
        File file = new File(getUserDir() + "/browserstack.yml");
        this.browserStackYamlMap = convertYamlFileToMap(file, new HashMap<>());
    }

//    @BeforeAll
//    public static void setUp() throws MalformedURLException {
//        MutableCapabilities capabilities = new MutableCapabilities();
//        HashMap<String, String> bstackOptions = new HashMap<>();
//        userName = System.getenv("BROWSERSTACK_USERNAME") != null ? System.getenv("BROWSERSTACK_USERNAME") : (String) browserStackYamlMap.get("userName");
//        accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null ? System.getenv("BROWSERSTACK_ACCESS_KEY") : (String) browserStackYamlMap.get("accessKey");
//        bstackOptions.putIfAbsent("source", "cucumber-java:sample-cucumber-junit4:v1.0");
//        capabilities.setCapability("bstack:options", bstackOptions);
//        driver.set(new RemoteWebDriver(
//                new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", userName , accessKey)), capabilities));
//        homePage = new HomePage(driver.get());
////        homePage.webDriver = driver.get();
//
//    }

    @Given("^Driver is initiated")
    public void Driver_is_initiatied() throws Throwable {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
        userName = System.getenv("BROWSERSTACK_USERNAME") != null ? System.getenv("BROWSERSTACK_USERNAME") : (String) browserStackYamlMap.get("userName");
        accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null ? System.getenv("BROWSERSTACK_ACCESS_KEY") : (String) browserStackYamlMap.get("accessKey");
        bstackOptions.putIfAbsent("source", "cucumber-java:sample-cucumber-junit4:v1.0");
        capabilities.setCapability("bstack:options", bstackOptions);
        driver.set(new RemoteWebDriver(
            new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", userName , accessKey)), capabilities));
        homePage = new HomePage(driver.get());
    }

    @Given("^I am on the website '(.+)'$")
    public void I_am_on_the_website(String url) throws Throwable {
        driver.get().get(url);
        Thread.sleep(2000);
    }

//    @When("^I am on the websiteee '(.+)'$")
//    public void I_am_on_the_websiteee(String url) throws Throwable {
//        Thread.sleep(2000);
//    }

    @When("^I select a product and click on 'Add to cart' button")
    public void I_select_a_product_and_add_to_cart() throws Throwable {
        homePage.selectFirstProductName();
        homePage.clickAddToCartButton();
        Thread.sleep(2000);
    }

    @Then("the product should be added to cart")
    public void product_should_be_added_to_cart() {
        homePage.waitForCartToOpen();
        Assert.assertEquals(homePage.getSelectedProductName(), homePage.getProductCartText());
    }

    @Then("the page title should contain '(.+)'$")
    public void page_title_should_contain(String expectedTitle) {
        Assert.assertTrue(driver.get().getTitle().contains(expectedTitle));
    }

    @Then("quit the driver")
    public void quit_the_driver() {
        driver.get().quit();
    }

//    @AfterAll
//    public static void teardown() throws Exception {
//        Thread.sleep(2000);
//        driver.get().quit();
//    }
//@AfterClass
//public static void teardown() throws Exception {
//    Thread.sleep(2000);
//    driver.get().quit();
//}

    private String getUserDir() {
        return System.getProperty(USER_DIR);
    }

    private Map<String, Object> convertYamlFileToMap(File yamlFile, Map<String, Object> map) {
        try {
            InputStream inputStream = Files.newInputStream(yamlFile.toPath());
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(inputStream);
            map.putAll(config);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Malformed browserstack.yml file - %s.", e));
        }
        return map;
    }
}
