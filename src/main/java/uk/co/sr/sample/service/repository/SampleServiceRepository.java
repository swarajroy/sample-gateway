package uk.co.sr.sample.service.repository;

import static uk.co.sr.sample.service.DependencyConstants.SAMPLE_SERVICE;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uk.co.sr.sample.model.Currency;
import uk.co.sr.sample.model.HighestPrice;
import uk.co.sr.sample.service.SampleService;

@Slf4j
@AllArgsConstructor
public class SampleServiceRepository implements SampleService {

  protected static final String HIGHEST_PRICE_API_ENDPOINT = "/api/1/highestprice?currency={currency}";
  private final WebClient webClient;

  @Override
  @CircuitBreaker(name = SAMPLE_SERVICE)
  @Bulkhead(name = SAMPLE_SERVICE)
  public Mono<HighestPrice> getHighestPrice(final Currency currency) {
    log.info("enter getHighestPrice currency = {}", currency);
    return webClient.get()
        .uri(HIGHEST_PRICE_API_ENDPOINT, currency.getValue())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(HighestPrice.class)
        .doOnSuccess(
            highestPrice -> log.info("exit getHighestPrice highestPrice = {}", highestPrice));
  }
}
