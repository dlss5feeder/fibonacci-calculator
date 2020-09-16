package es.spring.microservices.fibonacci.calculator.services.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import es.spring.microservices.fibonacci.calculator.model.api.requests.FibonacciCalculationRequest;
import es.spring.microservices.fibonacci.calculator.model.api.responses.FibonacciAlgorithmResponse;
import es.spring.microservices.fibonacci.calculator.model.api.responses.FibonacciCalculationResponse;
import es.spring.microservices.fibonacci.calculator.model.api.responses.FibonacciSequenceResponse;
import es.spring.microservices.fibonacci.calculator.model.enums.FibonacciAlgorithm;
import es.spring.microservices.fibonacci.calculator.services.FibonacciService;

@Service
public class FibonacciServiceImpl implements FibonacciService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciServiceImpl.class);
	
	/**
	 * Calcula el número de Fibonacci por un algoritmo fijo (en este caso iterativo) 
	 * @param n número de iteraciones
	 * @return FibonacciSequenceResponse - respuesta con información como el algoritmo utilizado, la secuencia completa de Fibonacci tras n iteraciones, el tiempo de ejecucion, etc
	 */
	public FibonacciSequenceResponse calculateFibonacciSequence(Integer n) {
		Instant initTime = Instant.now();
		List<Long> sequence = fibonacciSequenceIterations(n);
		return new FibonacciSequenceResponse(FibonacciAlgorithm.ITERATIVE.getCode(), n, sequence, ChronoUnit.MILLIS.between(initTime, Instant.now()));
	}

	/**
	 * Calcula el número de Fibonacci por un algoritmo fijo (en este caso iterativo) 
	 * @param fibonacciCalculationRequest datos de la petición
	 * @return FibonacciCalculationResponse - respuesta con la ejecución de el/los algoritmos
	 */	
	@Override
	public FibonacciCalculationResponse calculateFibonacciNumber(FibonacciCalculationRequest fibonacciCalculationRequest) {
		
		FibonacciAlgorithmResponse fibonacciAlgoritmResponse = null;
		FibonacciCalculationResponse fibonacciCalculationResponse = new FibonacciCalculationResponse();
		
		switch (fibonacciCalculationRequest.getFibonacciAlgorithm()) {
		case RECURSIVE:
			fibonacciAlgoritmResponse = fibonacciNumberRecursive(fibonacciCalculationRequest.getN(), fibonacciCalculationRequest.getIncludeExecutionTimes());
			fibonacciCalculationResponse.getFibonacciAlgorithmResponses().add(fibonacciAlgoritmResponse);
			break;

		case ITERATIVE:
			fibonacciAlgoritmResponse = fibonacciNumberIterations(fibonacciCalculationRequest.getN(), fibonacciCalculationRequest.getIncludeExecutionTimes());
			fibonacciCalculationResponse.getFibonacciAlgorithmResponses().add(fibonacciAlgoritmResponse);
			break;

		case MATRIX:
			fibonacciAlgoritmResponse = fibonacciNumberMatrix(fibonacciCalculationRequest.getN(), fibonacciCalculationRequest.getIncludeExecutionTimes());
			fibonacciCalculationResponse.getFibonacciAlgorithmResponses().add(fibonacciAlgoritmResponse);
			break;

		case ALL:
			List<FibonacciAlgorithmResponse> fibonacciAlgorithmResponsesList = fibonacciNumberAll(fibonacciCalculationRequest.getN(), fibonacciCalculationRequest.getIncludeExecutionTimes());
			fibonacciCalculationResponse.getFibonacciAlgorithmResponses().addAll(fibonacciAlgorithmResponsesList);
			break;

		default:
			break;
		}
		
		return fibonacciCalculationResponse;
	}


	/*********************/
	/** PRIVATE METHODS **/
	/*********************/

	/**
	 * Algoritmo recursivo.
	 * Complejidad O(log2(n))
	 * Por ejempo, realiza 73.147.844.013.817.084.100 sumas para n=100 
	 * 
	 * @param n número de iteraciones
	 * @param includeExecutionTime flag para incluir o no el tiempo de ejecucion
	 * @return FibonacciAlgorithmResponse - respuesta con información como el número de Fibonacci tras n iteraciones, el tiempo de ejecucion, etc
	 */
	private FibonacciAlgorithmResponse fibonacciNumberRecursive(Integer n, Boolean includeExecutionTime) {
		Instant initTime = Instant.now();
		FibonacciAlgorithmResponse fibonacciAlgoritmResponse = fibonacciNumberRecursive_Aux(n, 1);
		if (Boolean.TRUE.equals(includeExecutionTime)) {
			fibonacciAlgoritmResponse.setExecutionTime(ChronoUnit.MILLIS.between(initTime, Instant.now()));
		}
		return fibonacciAlgoritmResponse;
	}
	
	private FibonacciAlgorithmResponse fibonacciNumberRecursive_Aux(Integer n, long iterations) {
		if (n < 2) {
			return new FibonacciAlgorithmResponse(FibonacciAlgorithm.RECURSIVE.getCode(), n, new Long(n), iterations, null);
		} else {
			FibonacciAlgorithmResponse n1 = fibonacciNumberRecursive_Aux(n-1, iterations);
			FibonacciAlgorithmResponse n2 = fibonacciNumberRecursive_Aux(n-2, iterations);
			return new FibonacciAlgorithmResponse(FibonacciAlgorithm.RECURSIVE.getCode(),
											n, 
											n1.getFibonacci() + n2.getFibonacci(), 
											n1.getIterations() + n2.getIterations(), 
											null);
		}
	}

	/**
	 * Algoritmo iterativo.
	 * Complejidad O(n)
	 * Por ejempo, realiza 100 sumas para n=100 
	 * 
	 * @param n número de iteraciones
	 * @param includeExecutionTime flag para incluir o no el tiempo de ejecucion
	 * @return FibonacciAlgorithmResponse - respuesta con información como el número de Fibonacci tras n iteraciones, el tiempo de ejecucion, etc
	 */
	private FibonacciAlgorithmResponse fibonacciNumberIterations(Integer n, Boolean includeExecutionTime) {
		
		Instant initTime = Instant.now();
		Long iterations = 0l;
		
		Long a = 0l;
		Long b = 1l;
		
		for (int i = 0; i < n; i++) {
			b = b + a;
			a = b - a;
			iterations++;
		}
		
		FibonacciAlgorithmResponse fibonacciAlgoritmResponse = new FibonacciAlgorithmResponse(FibonacciAlgorithm.ITERATIVE.getCode(), n, a, iterations, null);
		if (Boolean.TRUE.equals(includeExecutionTime)) {
			fibonacciAlgoritmResponse.setExecutionTime(ChronoUnit.MILLIS.between(initTime, Instant.now()));
		}
		return fibonacciAlgoritmResponse;
	}

	/**
	 * Algoritmo matricial.
	 * Complejidad log2(n)
	 * Por ejempo, realiza 9 multiplicaciones matriciales para n=100 
	 * 
	 * @param n número de iteraciones
	 * @param includeExecutionTime flag para incluir o no el tiempo de ejecucion
	 * @return FibonacciAlgorithmResponse - respuesta con información como el número de Fibonacci tras n iteraciones, el tiempo de ejecucion, etc
	 */
	private FibonacciAlgorithmResponse fibonacciNumberMatrix(Integer n, Boolean includeExecutionTime) {

		Instant initTime = Instant.now();
		Long iterations = 0l;
		
		if (n <= 0) {
			return new FibonacciAlgorithmResponse(FibonacciAlgorithm.MATRIX.getCode(), n, 0l, iterations, null);
		}
		
		long i = n-1;
		long auxOne = 0l;
		long auxTwo = 1l;
		
		long a = auxTwo;
		long b = auxOne;
		long c = auxOne;
		long d = auxTwo;
		
		while (i > 0) {
			if (isOdd(i)) {
				auxOne = (d*b + c*a);
				auxTwo = (d*(b+a)+c*b);
				a = auxOne;
				b = auxTwo;
			}
			auxOne = (c*c + d*d);
			auxTwo = (d*(2*c+d));
			c = auxOne;
			d = auxTwo;
			i = i / 2;
			iterations++;
		}
		
		FibonacciAlgorithmResponse fibonacciAlgoritmResponse = new FibonacciAlgorithmResponse(FibonacciAlgorithm.MATRIX.getCode(), n, a + b, iterations, null);
		if (Boolean.TRUE.equals(includeExecutionTime)) {
			fibonacciAlgoritmResponse.setExecutionTime(ChronoUnit.MILLIS.between(initTime, Instant.now()));
		}
		return fibonacciAlgoritmResponse;
		
	}
	
	private boolean isOdd(long number) {
		  return (number %2 != 0);
	}

	/**
	 * Implementa en paralelo todos los algoritmos.
	 * 
	 * @param n número de iteraciones
	 * @param includeExecutionTime flag para incluir o no el tiempo de ejecucion
	 * @return List<FibonacciAlgorithmResponse> - respuestas de los diferentes algoritmos ejecutados
	 */
	private List<FibonacciAlgorithmResponse> fibonacciNumberAll(Integer n, Boolean includeExecutionTime) {
		
		List<FibonacciAlgorithmResponse> fibonacciAlgorithmResponsesList = new ArrayList<>(); 
		
		try {
			// En este caso lanzaremos en paralelo la ejecucion de todos los algoritmos y recogeremos el resultado
			CompletableFuture<FibonacciAlgorithmResponse> algoritmRecursiveFuture = CompletableFuture.supplyAsync(() -> fibonacciNumberRecursive(n, includeExecutionTime));
			CompletableFuture<FibonacciAlgorithmResponse> algoritmIterationsFuture = CompletableFuture.supplyAsync(() -> fibonacciNumberIterations(n, includeExecutionTime));
			CompletableFuture<FibonacciAlgorithmResponse> algoritmMatrixFuture = CompletableFuture.supplyAsync(() -> fibonacciNumberMatrix(n, includeExecutionTime));
			
			fibonacciAlgorithmResponsesList.add(algoritmRecursiveFuture.get());
			fibonacciAlgorithmResponsesList.add(algoritmIterationsFuture.get());
			fibonacciAlgorithmResponsesList.add(algoritmMatrixFuture.get());
			
		} catch (Exception ex) {
			LOGGER.error("There was an error in any algorithm: {}", ex);
		}
		
		return fibonacciAlgorithmResponsesList;
	}

	/**
	 * Algoritmo iterativo.
	 * Complejidad O(n)
	 * Por ejempo, realiza 100 sumas para n=100 
	 * 
	 * @param n número de iteraciones
	 * @return List - secuencia de Fibonacci tras n iteraciones
	 */
	private List<Long> fibonacciSequenceIterations(Integer n) {
		
		List<Long> sequence = new ArrayList<>();
		
		Long a = 0l;
		Long b = 1l;
		sequence.add(a);
		
		for (int i = 0; i < n; i++) {
			b = b + a;
			a = b - a;
			sequence.add(a);
		}
		return sequence;
	}
}