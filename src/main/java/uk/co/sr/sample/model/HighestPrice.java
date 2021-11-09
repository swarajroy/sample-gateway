package uk.co.sr.sample.model;

import java.math.BigDecimal;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class HighestPrice {

  BigDecimal price;
}
