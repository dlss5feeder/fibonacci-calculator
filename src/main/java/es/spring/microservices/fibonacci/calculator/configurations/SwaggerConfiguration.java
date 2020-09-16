package es.spring.microservices.fibonacci.calculator.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {                                    
    
	private static final String API_TITLE = "Fibonacci Calculator Microservice";
	private static final String API_DESCRIPTION = "Fibonacci Calculator Microservice API";
	public static final String API_BASE_PATH = "/api";
	
	@Bean
	@Primary
	public Docket apiV1() {
		return new Docket(DocumentationType.SWAGGER_2) //
								.useDefaultResponseMessages(false) //
								.apiInfo(apiInfo()) //
								.useDefaultResponseMessages(false).select() //
								.paths(PathSelectors.regex(".*" + API_BASE_PATH + "/.*"))                          
								.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder() //
								.title(API_TITLE) //
								.description(API_DESCRIPTION) //
								.build();
	}
}