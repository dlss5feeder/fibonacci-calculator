package es.spring.microservices.fibonacci.calculator.controllers;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/fibonacci-calculator-controller.feature",
		plugin = {"pretty", "json:target/cucumber-report.json"}
		)
public class FibonacciCalculatorControllerTests {

}