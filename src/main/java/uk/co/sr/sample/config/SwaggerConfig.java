package uk.co.sr.sample.config;

import io.swagger.v3.oas.models.media.StringSchema;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SwaggerConfig {

  @Bean
  public OpenApiCustomiser microTypeOpenApiCustomiser() {
    return openApi -> openApi.getComponents()
        .addSchemas("Currency", new StringSchema());
  }
}
