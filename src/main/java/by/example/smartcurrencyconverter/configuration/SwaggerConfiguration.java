package by.example.smartcurrencyconverter.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI twitterCloneOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Currency converter documentation")
                        .description("Smart currency converter")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Currency converter Wiki Documentation")
                        .url("https://currency_converter.wiki.github.org/docs"));
    }

            @Bean
            public RestTemplate restTemplate() {
                return new RestTemplate();
            }
}
