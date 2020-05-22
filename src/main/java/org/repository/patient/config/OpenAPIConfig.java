package org.repository.patient.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenAPIConfig {

    @Autowired
    private Environment environment;

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${application-name}") String appName,
            @Value("${application-description}") String appDescription,
            @Value("${application-version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .version(appVersion)
                        .description(appDescription));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                // Fetch the allowed domains from application.properties
                final String origins = environment.getProperty("allowed-origins");
                registry.addMapping("/api/**")
                        .allowedMethods("*")
                        .allowedOrigins(origins.split(","));
            }
        };
    }
}
