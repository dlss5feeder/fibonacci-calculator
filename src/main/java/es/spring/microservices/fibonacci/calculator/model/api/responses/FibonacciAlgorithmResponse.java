package es.spring.microservices.fibonacci.calculator.model.api.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FibonacciAlgorithmResponse {

	private String algorithm;
	
	private Integer n;
	
	private Long fibonacci;
	
	private Long iterations;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long executionTime;
	
}