package com.musala.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
            title = "Dev-Event Application Documentation",
            description = "Documentation for event booking application",
            version = "1.0"
    ),
        servers = {
            @Server(
                    description = "local",
                    url = "http://localhost:8080"
            )
        },
        security = {
            @SecurityRequirement(
                    name="bearerAuth"
            )
        }

)
@io.swagger.v3.oas.annotations.security.SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
