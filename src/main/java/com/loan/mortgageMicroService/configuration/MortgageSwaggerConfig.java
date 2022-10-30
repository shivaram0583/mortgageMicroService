package com.loan.mortgageMicroService.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class MortgageSwaggerConfig {

    @Bean
    public Docket productApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(regex("/mortgage/v1.0/loanService.*")).build().apiInfo(metaInfo());
    }

    private ApiInfo metaInfo()
    {
        ApiInfo apiInfo = new ApiInfo("Mortgage Loan MicroService", "Mortgage service performs all CURD operations", "1.0", "Terms of Service",
                new Contact("D Shivaram", "https://github.com/shivaram0583", "dvvshivaram@gmail.com"), null, null);
        return apiInfo;
    }
}
