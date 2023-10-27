package com.example.rent.it.config.api;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Projeto Rent-It",
                description = "Projeto para locação de utensilios domesticos e festas",
                contact = @Contact(
                        name = "Reginado Junior",
                        url = "https://github.com/Reginaldo-Santos-Junior",
                        email = "reginaldo.santosjr@sptech.school"
                ),
                license = @License(name = "DAS BRABA"),
                version = "1.0.0"
        )
)
@SecurityScheme(
        name = "Bearer", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"
)
public class OpenApiConfig {

}
