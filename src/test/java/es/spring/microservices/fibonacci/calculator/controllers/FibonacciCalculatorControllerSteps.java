package es.spring.microservices.fibonacci.calculator.controllers;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import es.spring.microservices.fibonacci.calculator.handlers.ExceptionManager;
import es.spring.microservices.fibonacci.calculator.model.api.responses.FibonacciCalculationResponse;
import es.spring.microservices.fibonacci.calculator.model.api.responses.FibonacciSequenceResponse;
import es.spring.microservices.fibonacci.calculator.model.enums.FibonacciAlgorithm;
import es.spring.microservices.fibonacci.calculator.services.FibonacciService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FibonacciCalculatorControllerSteps {

	@Mock
	private FibonacciService fibonacciService;
	
	@Autowired
	private ExceptionManager exceptionManager;
	
	@Autowired
	private FibonacciCalculatorController fibonacciCalculatorController;

	private ResponseEntity<FibonacciSequenceResponse> fibonacciSequenceResponse;
	private ResponseEntity<FibonacciCalculationResponse> fibonacciCalculationResponse;
	private Exception exception;
	
    @Before
    public void setUp() {
    	exception = null;
    	fibonacciSequenceResponse = null;
    	fibonacciCalculationResponse = null;
    }
    
    /*****************************************/
	
    @When("Calculo la secuencia de Fibonacci para {int}")
    public void calculateFibonacciSequence(Integer n) {
    	try {
    		fibonacciSequenceResponse = fibonacciCalculatorController.calculateFibonacciSequence(n);
    	} catch (Exception ex) {
    		exception = ex;
    	}
    }

    @When("Calculo el numero de Fibonacci para {int} con el algoritmo {string}")
    public void calculateFibonacciNumber(Integer n, String fibonacciAlgorithm) {
    	try {
    		fibonacciCalculationResponse = fibonacciCalculatorController.calculateFibonacciNumber(n, FibonacciAlgorithm.valueOf(fibonacciAlgorithm), false);
    	} catch (Exception ex) {
    		exception = ex;
    	}
    }
    
    @Then("Obtengo como codigo de respuesta para la secuencia un {int}")
    public void obtainFibonacciSequenceResponseCode(Integer code) {
    	assertEquals(code.intValue(), fibonacciSequenceResponse.getStatusCode().value());
    }

    @Then("Obtengo como codigo de respuesta para el numero un {int}")
    public void obtainFibonacciCalculationResponseCode(Integer code) {
    	assertEquals(code.intValue(), fibonacciCalculationResponse.getStatusCode().value());
    }

    @Then("Obtengo el mensaje de error {string} - {string}")
    public void obtainFibonacciResponseError(String field, String message) {
    	Map<String, String> errors = exceptionManager.handleConstraintViolationException((ConstraintViolationException) exception);
    	assertEquals(message, errors.get(field));
    }
}