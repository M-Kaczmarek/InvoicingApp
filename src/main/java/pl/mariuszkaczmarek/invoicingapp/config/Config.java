package pl.mariuszkaczmarek.invoicingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class Config {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .paths(PathSelectors.ant("/api/**"))
            .build().apiInfo(new ApiInfo("Invoicing API",
                "Api to save your invoice",
                "1.01",
                "http://localhost:8080/",
                new Contact("Mariusz Kaczmarek","http://localhost:8080/", "mariuszkaczmarek97@outlook.com"),
                "MIT",
                "http://localhost:8080/licenses",
                new ArrayList<>()
            ));
    }
}
