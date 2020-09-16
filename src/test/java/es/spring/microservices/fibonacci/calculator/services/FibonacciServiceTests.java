package es.spring.microservices.fibonacci.calculator.services;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/fibonacci-service.feature",
		plugin = {"pretty", "json:target/cucumber-report.json"}
		)
public class FibonacciServiceTests {

}