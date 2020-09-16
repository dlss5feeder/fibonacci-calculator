package es.spring.microservices.fibonacci.calculator.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintViolationException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import es.spring.microservices.fibonacci.calculator.model.api.requests.FibonacciCalculationRequest;
import es.spring.microservices.fibonacci.calculator.model.api.responses.FibonacciCalculationResponse;
import es.spring.microservices.fibonacci.calculator.model.api.responses.FibonacciSequenceResponse;
import es.spring.microservices.fibonacci.calculator.model.enums.FibonacciAlgorithm;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FibonacciServiceSteps {

	@Autowired
	private FibonacciService fibonacciService;

	private FibonacciSequenceResponse fibonacciSequenceResponse;
	private FibonacciCalculationResponse fibonacciCalculationResponse;
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
    		fibonacciSequenceResponse = fibonacciService.calculateFibonacciSequence(n);
    	} catch (Exception ex) {
    		exception = ex;
    	}
    }

    @SuppressWarnings("deprecation")
	@Then("Obtengo un error de validacion {string}")
    public void obtainValidationError(String validationError) {
    	
    	assertNotNull(exception);
    	assertThat(exception, instanceOf(ConstraintViolationException.class));
    	assertEquals(validationError, ((ConstraintViolationException) exception).getMessage());
    }

    @Then("Obtengo como respuesta el numero {int}")
    public void obtainFibonacciNumber(Integer fibonacci) {
    	assertNotNull(fibonacciCalculationResponse);
    	assertTrue(!CollectionUtils.isEmpty(fibonacciCalculationResponse.getFibonacciAlgorithmResponses()));
    	assertNotNull(fibonacciCalculationResponse.getFibonacciAlgorithmResponses().get(0));
    	assertEquals(new Long(fibonacci), fibonacciCalculationResponse.getFibonacciAlgorithmResponses().get(0).getFibonacci());
    }
 
    @Then("Obtengo como respuesta la secuencia {string}")
    public void obtainFibonacciSequence(String fibonacci) {
    	assertNotNull(fibonacciSequenceResponse);
    	assertTrue(!CollectionUtils.isEmpty(fibonacciSequenceResponse.getFibonacciSequence()));
    	assertEquals(fibonacci, fibonacciSequenceResponse.getFibonacciSequence().toString());
    }

    /*****************************************/
    
    @When("Calculo el numero de Fibonacci para {int} con el algoritmo {string}")
    public void calculateFibonacciNumber(Integer n, String fibonacciAlgorithm) {
    	try {
			FibonacciCalculationRequest fibonacciCalculationRequest = new FibonacciCalculationRequest(n, FibonacciAlgorithm.valueOf(fibonacciAlgorithm), false);
			fibonacciCalculationResponse = fibonacciService.calculateFibonacciNumber(fibonacciCalculationRequest);
    	} catch (Exception ex) {
    		exception = ex;
    	}
    }
}