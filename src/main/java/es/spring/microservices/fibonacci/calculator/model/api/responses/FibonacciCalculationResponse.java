package es.spring.microservices.fibonacci.calculator.model.api.responses;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FibonacciCalculationResponse {

	private List<FibonacciAlgorithmResponse> fibonacciAlgorithmResponses = new ArrayList<>();
	
}