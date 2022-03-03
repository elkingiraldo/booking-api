package co.com.elkin.apps.bookingapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for swagger documentation
 *
 * @author egiraldo
 */
@Configuration
public class SwaggerConfig {

    private static final String MAINTAINER_NAME = "Elkin Giovanni Giraldo Pinedo";
    private static final String MAINTAINER_EMAIL = "elkin.giraldo.pinedo@gmail.com";
    private static final String MAINTAINER_GITHUB = "https://github.com/elkingiraldo";

    private static final String TITLE = "Api Documentation for Booking API";
    private static final String DESCRIPTION = "This module is in charge of managing all request to Booking API";
    private static final String VERSION = "v0.0.1";
    private static final String LICENSE = "All rights reserved";
    private static final String LICENSE_URI = "https://springdoc.org";

    private static final String GITHUB_README = "GitHub Booking API Documentation";
    private static final String GITHUB_README_URI = "https://github.com/elkingiraldo/booking-api/blob/main/README.md";

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(TITLE)
                        .description(DESCRIPTION)
                        .version(VERSION)
                        .license(new License().name(LICENSE).url(LICENSE_URI))
                        .contact(new Contact().name(MAINTAINER_NAME).email(MAINTAINER_EMAIL).url(MAINTAINER_GITHUB)))
                .externalDocs(new ExternalDocumentation()
                        .description(GITHUB_README)
                        .url(GITHUB_README_URI));
    }

}
