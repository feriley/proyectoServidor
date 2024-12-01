package com.example.proyectoServidor.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger/OpenAPI para documentar los endpoints de la
 * aplicación.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API del Proyecto Servidor")
                        .description("Documentación de la API para el proyecto Vedruna")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("tu.email@example.com")
                                .url("https://tu-portafolio.com"))
                        .license(new License()
                                .name("Licencia MIT")
                                .url("https://opensource.org/licenses/MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación adicional")
                        .url("https://github.com/tu-repositorio"));
    }
}
