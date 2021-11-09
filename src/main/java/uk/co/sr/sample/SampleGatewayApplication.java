package uk.co.sr.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {SampleGatewayProperties.class})
public class SampleGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(SampleGatewayApplication.class, args);
  }

}
