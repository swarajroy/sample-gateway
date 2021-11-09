package uk.co.sr.sample;

import static uk.co.sr.sample.service.DependencyConstants.SAMPLE_SERVICE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TestOverrideConfiguration {

  public TestOverrideConfiguration(final SampleGatewayProperties properties,
      final StubFinder stubFinder) {
    var stubUrl = stubFinder.findStubUrl(SAMPLE_SERVICE);
    var url = stubUrl.toString().concat("/").concat(SAMPLE_SERVICE);
    log.info("url = {}", url);
    properties.setSampleServiceBaseUrl(url);
  }
}
