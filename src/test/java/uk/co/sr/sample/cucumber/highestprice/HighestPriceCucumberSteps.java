package uk.co.sr.sample.cucumber.highestprice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.sr.sample.SampleGatewayApplication;
import uk.co.sr.sample.TestOverrideConfiguration;

@Slf4j
@ExtendWith(SpringExtension.class)
@CucumberContextConfiguration
@SpringBootTest(classes = {
    SampleGatewayApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestOverrideConfiguration.class)
@AutoConfigureStubRunner(stubsMode = StubsMode.LOCAL, ids = {"uk.co.sr:sample-service:+:stubs"})
@ActiveProfiles("cucumber-test")
public class HighestPriceCucumberSteps {

  private static final String HIGHEST_PRICE_API_ENDPOINT = "/api/1/highestprice?currency={currency}";

  @Autowired
  private TestRestTemplate testRestTemplate;

  private ResponseEntity<String> responseEntity;

  @When("I call the highest price API endpoint with currency {string}")
  public void iCallTheHighestPriceAPIEndpointWithCurrency(final String currency) {
    responseEntity = testRestTemplate.exchange(HIGHEST_PRICE_API_ENDPOINT, GET, null, String.class,
        currency);
  }

  @Then("I get a response of {int}")
  public void iGetAResponseOf(int responseCode) {
    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(responseCode);
  }

  @SneakyThrows
  @And("the response matches {string}")
  public void theResponseMatches(String fileName) {
    final String expected = Files.readString(Paths.get(
        "src/test/resources/uk/co/sr/sample/cucumber/highestprice/response/".concat(fileName)
            .concat(".response.json")), Charset.defaultCharset());
    final String actual = responseEntity.getBody();
    JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT);
  }
}
