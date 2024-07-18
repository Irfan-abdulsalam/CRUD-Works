package com.My_projects.CRUD_works_application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("CRUD Works Application API Documentation")
                            .version("1.0.0")
                            .description("API documentation for the CRUD Works Application")
                            .contact(new Contact()
                                    .name("Irfan abdulsalam")
                                    .url("https://www.linkedin.com/in/irfan-abdulsalam/")
                                    .email("irfan.abdulsalam.dev@gmail.com")));
        }
    }
