package com.joao.AgendaDeAtividades.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
            .info(new Info()
                .title("Task Manager API with Java,Spring Boot and Docker")
                .version("1.0")
                .description("Project created for study and portfolio purposes.")
                .contact(new Contact()
                    .name("João Pedro")
                    .email("joao.pedro.a@live.com")
                    .url("https://github.com/Joao-Pedro-SA")
                )
                .license(new License()
                .name("MIT License"))
            );
    }
}
