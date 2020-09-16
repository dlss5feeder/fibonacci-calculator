package es.spring.microservices.fibonacci.calculator.model.api.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FibonacciSequenceResponse {

	private String algorithm;
	
	private Integer n;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<Long> fibonacciSequence;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long executionTime;
	
}