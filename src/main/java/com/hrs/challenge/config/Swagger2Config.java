package com.hrs.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    //I created a Docket bean in a Spring Boot configuration to configure Swagger2 for the application.
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.hrs.challenge.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());
    }

    // I added an apiEndPointsInfo() method that returns ApiInfo object initialized with information about our API.
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Invisible Pay Challenge Spring Boot REST API")
                .description("Invisible Pay Challenge")
                .contact(new Contact("Cumhur Cagatay", "http://turkyazilimcilar.com", "cumhur.cagatay@gmail.com"))
                .version("1.0.0")
                .build();
    }
}