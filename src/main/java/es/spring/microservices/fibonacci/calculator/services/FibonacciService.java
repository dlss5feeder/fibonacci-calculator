package es.spring.microservices.fibonacci.calculator.services;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import es.spring.microservices.fibonacci.calculator.constants.FibonacciConstants;
import es.spring.microservices.fibonacci.calculator.model.api.requests.FibonacciCalculationRequest;
import es.spring.microservices.fibonacci.calculator.model.api.responses.FibonacciCalculationResponse;
import es.spring.microservices.fibonacci.calculator.model.api.responses.FibonacciSequenceResponse;

@Validated
public interface FibonacciService {

	public FibonacciSequenceResponse calculateFibonacciSequence(@NotNull(message = FibonacciConstants.N_NOT_NULL_MESSAGE)
															  @Min(value = FibonacciConstants.N_MIN_VALUE_FOR_SEQUENCE, message = FibonacciConstants.N_MIN_VALUE_MESSAGE_FOR_SEQUENCE)
															  @Max(value = FibonacciConstants.N_MAX_VALUE_FOR_SEQUENCE, message = FibonacciConstants.N_MAX_VALUE_MESSAGE_FOR_SEQUENCE) Integer n);

	public FibonacciCalculationResponse calculateFibonacciNumber(@Valid FibonacciCalculationRequest fibonacciCalculationRequest);


}