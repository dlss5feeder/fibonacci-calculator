package es.spring.microservices.fibonacci.calculator.model.enums;

import lombok.Getter;

@Getter
public enum FibonacciAlgorithm {

	RECURSIVE("Recursivo", "Algoritmo basado en recursividad"),
	
	ITERATIVE("Iterativo", "Algoritmo basado en oteraciones"),
	
	MATRIX("Matricial", "Algoritmo basado en cálculo matricial"),
	
	ALL("Todos", "Ejecuta todos los algoritmos");

	private String code;
	private String description;
	
	private FibonacciAlgorithm(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
}