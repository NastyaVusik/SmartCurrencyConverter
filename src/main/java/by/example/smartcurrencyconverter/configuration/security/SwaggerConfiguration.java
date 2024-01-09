package by.example.smartcurrencyconverter.configuration.security;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI twitterCloneOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Twitter-clone API documentation")
                        .description("Blog application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Twitter-clone Wiki Documentation")
                        .url("https://twitter_clone.wiki.github.org/docs"));
    }
}
