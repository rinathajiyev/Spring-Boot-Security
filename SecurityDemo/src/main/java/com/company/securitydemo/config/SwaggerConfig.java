package com.company.securitydemo.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("jwt-security")
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("jwt security")
                .description("Spring Security with JWT")
                .build();
    }
}
