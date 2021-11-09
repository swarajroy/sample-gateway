package uk.co.sr.sample.service.repository;

import static uk.co.sr.sample.service.repository.SampleServiceRepository.HIGHEST_PRICE_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import uk.co.sr.sample.SampleGatewayProperties;

@Configuration
@Slf4j
public class SampleServiceRepositoryConfig {

  @Bean
  public SampleServiceRepository sampleServiceRepository(final WebClient.Builder builder,
      final SampleGatewayProperties properties) {
    log.info("In sampleServiceRepository");
    var sampleServiceBaseUrl = properties.getSampleServiceBaseUrl();
    log.info("Sample service highest price api url = {}",
        sampleServiceBaseUrl.concat(HIGHEST_PRICE_API_ENDPOINT));
    // TODO build webclient with timeouts https://www.baeldung.com/spring-5-webclient section 4.2
    var webClient = builder.baseUrl(sampleServiceBaseUrl)
        .build();
    log.info("Out sampleServiceRepository");
    return new SampleServiceRepository(webClient);
  }

}
