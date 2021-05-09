package com.example.mongoDbAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.mongoDbAPI.controller"))
                .build().apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo(){
         ApiInfo apiInfo = new ApiInfo(
                "REST API with MONGO DB",
                "This API was created to help practice Spring boot and Mongo DB connections",
                "API 0.1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                 "Samad Munthakim",
                 "License of API", "API license URL");
        return apiInfo;
    }
}
