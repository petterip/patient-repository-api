package org.repository.patient.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDescription,
                                 @Value("${application-version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Patient Repository API")
                        .version(appVersion)
                        .description(appDescription));
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
