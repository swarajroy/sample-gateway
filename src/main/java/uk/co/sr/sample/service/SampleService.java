package uk.co.sr.sample.service;

import reactor.core.publisher.Mono;
import uk.co.sr.sample.model.Currency;
import uk.co.sr.sample.model.HighestPrice;

public interface SampleService {

  Mono<HighestPrice> getHighestPrice(final Currency currency);

}
