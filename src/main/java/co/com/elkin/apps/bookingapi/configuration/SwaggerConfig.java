package co.com.elkin.apps.bookingapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration for swagger documentation
 *
 * @author egiraldo
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String MAINTAINER_NAME = "Elkin Giovanni Giraldo Pinedo";
    private static final String MAINTAINER_EMAIL = "elkin.giraldo.pinedo@gmail.com";
    private static final String MAINTAINER_GITHUB = "https://github.com/elkingiraldo";

    private static final Set<String> DEFAULT_PRODUCES = new HashSet<>(Collections.singletonList("application/json"));
    private static final Set<String> DEFAULT_CONSUMES = new HashSet<>(Collections.singletonList("application/json"));

    private static final String TITLE = "Api Documentation for Booking API";
    private static final String DESCRIPTION = "This module is in charge of managing all request to Booking API";
    private static final String VERSION = "1.0";
    private static final String TOS_URI = "urn:tos";
    private static final String LICENSE = "All rights reserved";

    public static final Contact DEFAULT_CONTACT = new Contact(MAINTAINER_NAME, MAINTAINER_GITHUB, MAINTAINER_EMAIL);
    public static final ApiInfo DEFAULT = new ApiInfo(TITLE, DESCRIPTION, VERSION, TOS_URI, DEFAULT_CONTACT, LICENSE,
            MAINTAINER_EMAIL, Collections.emptyList());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT)
                .produces(DEFAULT_PRODUCES)
                .consumes(DEFAULT_CONSUMES);
    }

}
