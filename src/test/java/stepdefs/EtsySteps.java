package stepdefs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.EtsyPage;

public class EtsySteps {

	private WebDriver driver;
	private EtsyPage etsy;
	private String keyword;

	@Given("User in on Etsy homepage")
	public void user_in_on_Etsy_homepage() throws MalformedURLException {
		WebDriverManager.chromedriver().setup();
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setPlatform(Platform.ANY);
		driver = new RemoteWebDriver(new URL("http://52.207.236.154:4444/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().fullscreen();
		driver.get("https://etsy.com");
		Assert.assertEquals("Etsy.com | Shop for anything from creative people everywhere", driver.getTitle());
	}

	@When("User searches for {string}")
	public void user_searches_for(String keyword) {
		this.keyword = keyword;
		etsy = new EtsyPage(driver);
		etsy.searchBox.sendKeys(keyword + Keys.ENTER);
		System.out.println("SEARCHing FOR "+keyword);
	}

	@Then("Search results should be displayed")
	public void search_results_should_be_displayed() {
		Assert.assertTrue(driver.getTitle().toLowerCase().startsWith(keyword));
		driver.quit();

	}

}
