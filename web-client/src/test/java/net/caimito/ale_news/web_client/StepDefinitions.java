package net.caimito.ale_news.web_client;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class StepDefinitions {
    private DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
    private PhantomJSDriver driver = new PhantomJSDriver(capabilities);

    @When("^I open ALE News$")
    public void i_open_ALE_News() throws Throwable {
        driver.get("http://localhost:8080/web-client");
    }

    @Then("^I see a list of articles$")
    public void i_see_a_list_of_articles() throws Throwable {
        assertThat(driver.findElement(By.id("articleList")), is(notNullValue())) ;
    }
}
