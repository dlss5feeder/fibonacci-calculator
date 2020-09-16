package es.spring.microservices.fibonacci.calculator.model.api.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import es.spring.microservices.fibonacci.calculator.constants.FibonacciConstants;
import es.spring.microservices.fibonacci.calculator.model.enums.FibonacciAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FibonacciCalculationRequest {

	@NotNull(message = FibonacciConstants.N_NOT_NULL_MESSAGE)
	@Min(value = FibonacciConstants.N_MIN_VALUE_FOR_NUMBER, message = FibonacciConstants.N_MIN_VALUE_MESSAGE_FOR_NUMBER) 
	@Max(value = FibonacciConstants.N_MAX_VALUE_FOR_NUMBER, message = FibonacciConstants.N_MAX_VALUE_MESSAGE_FOR_NUMBER)
	private Integer n;
	
	@NotNull(message = FibonacciConstants.N_NOT_NULL_MESSAGE)
	private FibonacciAlgorithm fibonacciAlgorithm;
	
	private Boolean includeExecutionTimes;

}