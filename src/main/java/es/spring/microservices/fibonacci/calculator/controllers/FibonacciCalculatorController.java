package es.spring.microservices.fibonacci.calculator.controllers;

import static es.spring.microservices.fibonacci.calculator.controllers.FibonacciCalculatorController.FIBONACCI_CALCULATOR_PATH;
import static es.spring.microservices.fibonacci.calculator.controllers.FibonacciCalculatorController.FIBONACCI_CALCULATOR_TAG;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.spring.microservices.fibonacci.calculator.configurations.SwaggerConfiguration;
import es.spring.microservices.fibonacci.calculator.model.api.requests.FibonacciCalculationRequest;
import es.spring.microservices.fibonacci.calculator.model.api.responses.FibonacciCalculationResponse;
import es.spring.microservices.fibonacci.calculator.model.api.responses.FibonacciSequenceResponse;
import es.spring.microservices.fibonacci.calculator.model.enums.FibonacciAlgorithm;
import es.spring.microservices.fibonacci.calculator.services.FibonacciService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = SwaggerConfiguration.API_BASE_PATH + FIBONACCI_CALCULATOR_PATH)
@Api(tags = { FIBONACCI_CALCULATOR_TAG })
@Validated
public class FibonacciCalculatorController {
	
	public static final String FIBONACCI_CALCULATOR_PATH = "/";
	public static final String FIBONACCI_CALCULATOR_TAG = "Fibonacci Calculator";	

	private final FibonacciService fibonacciService;
	
	@Autowired
	public FibonacciCalculatorController(FibonacciService transactionService) {
		this.fibonacciService = transactionService;
	}

	/**
	 * Gets the Fibonacci sequence
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/sequence")
	@ApiOperation(value = "Gets the Fibonacci sequence")
	@ApiResponses({
		@ApiResponse(code = HttpServletResponse.SC_OK, message = "Algorithm calculation ok"),	
		@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Algorithm calculation validation error")
	})
	public ResponseEntity<FibonacciSequenceResponse> calculateFibonacciSequence(
			@ApiParam(value = "n (the fibonacci iterations)", required = true) @RequestParam(required = false) Integer n
			) {
		FibonacciSequenceResponse fibonacciSequenceResponse = fibonacciService.calculateFibonacciSequence(n);
		return ResponseEntity.ok(fibonacciSequenceResponse);
	}
	
	/**
	 * Gets the Fibonacci number after N iterations
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/number")
	@ApiOperation(value = "Gets the Fibonacci number after N iterations")
	@ApiResponses({
		@ApiResponse(code = HttpServletResponse.SC_OK, message = "Algorithm calculation ok"),	
		@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Algorithm calculation validation error")
	})
	public ResponseEntity<FibonacciCalculationResponse> calculateFibonacciNumber(
			@ApiParam(value = "n (the fibonacci iterations)", required = true) @RequestParam(required = false) Integer n,
			@ApiParam(value = "The algorithm to use", required = true) @RequestParam(required = false) FibonacciAlgorithm fibonacciAlgorithm,
			@ApiParam(value = "Include execution times or not", required = false) @RequestParam(required = false) Boolean includeExecutionTimes
			) {
		FibonacciCalculationResponse fibonacciCalculationResponse = fibonacciService.calculateFibonacciNumber(new FibonacciCalculationRequest(n, fibonacciAlgorithm, includeExecutionTimes));
		return ResponseEntity.ok(fibonacciCalculationResponse);
	}
		
}