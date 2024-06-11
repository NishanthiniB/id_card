package com.example.idcard.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "ID Card Generator API",
                description = "Performing CRUD operations",
                contact = @Contact(
                        name = "Nishanthini B",
                        email = "nishasuresh1305@gmail.com"
                ),
                license = @License(
                        name = "Not Available"
                ),
                version = "v1"
        ),
        servers = {
                @Server(
                        description = "Production",
                        url = "http://167.86.100.248:8080/"
                ),
                @Server(
                        description = "Testing",
                        url = "http://localhost:8080/"
                )
        }
)
public class OpenAPIConfig {

}