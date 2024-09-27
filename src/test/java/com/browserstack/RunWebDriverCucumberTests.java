package com.browserstack;

import com.browserstack.pageobjects.HomePage;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@CucumberOptions(
        glue = "com.browserstack.stepdefs",
        features = "src/test/resources/features/test",
        plugin = {
                "pretty",
                "junit:reports/tests/cucumber/junit/cucumber.xml",
                "json:reports/tests/cucumber/json/cucumberTestReport.json"
        }
)

@RunWith(Cucumber.class)
public class RunWebDriverCucumberTests {
//        private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//        private static HomePage homePage;
/*        @BeforeClass
        public static void setUp() throws MalformedURLException {
                System.out.println("Hello..");
//                MutableCapabilities capabilities = new MutableCapabilities();
//                HashMap<String, String> bstackOptions = new HashMap<>();
//                userName = System.getenv("BROWSERSTACK_USERNAME") != null ? System.getenv("BROWSERSTACK_USERNAME") : (String) browserStackYamlMap.get("userName");
//                accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null ? System.getenv("BROWSERSTACK_ACCESS_KEY") : (String) browserStackYamlMap.get("accessKey");
//                bstackOptions.putIfAbsent("source", "cucumber-java:sample-cucumber-junit4:v1.0");
//                capabilities.setCapability("bstack:options", bstackOptions);
//                driver = new RemoteWebDriver(
//                    new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", userName , accessKey)), capabilities);
//                homePage = new HomePage();
//                homePage.webDriver = driver;
                driver.set(new ChromeDriver());
                homePage = new HomePage(driver.get());
//                homePage.webDriver = driver;
        }

        @AfterClass
        public static void teardown() throws Exception {
                Thread.sleep(2000);
                driver.get().quit();
        }*/


}
