package org.repository.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication()
public class Application implements ErrorController {

    // Forward all non-API urls to Swagger documentation
    private static final String PATH_ERROR = "/error";
    private static final String PATH_SWAGGER = "forward:/swagger-ui.html";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value = PATH_ERROR)
    public String error() {
        return PATH_SWAGGER;
    }

    @Override
    public String getErrorPath() {
        return PATH_ERROR;
    }
}