package es.spring.microservices.fibonacci.calculator.constants;

import org.springframework.stereotype.Component;

@Component
public class FibonacciConstants {

	public static final int N_MIN_VALUE_FOR_NUMBER = 0;
	public static final int N_MAX_VALUE_FOR_NUMBER = 45;
	public static final int N_MIN_VALUE_FOR_SEQUENCE = 0;
	public static final int N_MAX_VALUE_FOR_SEQUENCE = 90;
	
	public static final String N_NOT_NULL_MESSAGE = "no puede ser nulo";
	public static final String N_MIN_VALUE_MESSAGE_FOR_NUMBER = "debe ser mayor o igual que " + N_MIN_VALUE_FOR_NUMBER;
	public static final String N_MAX_VALUE_MESSAGE_FOR_NUMBER = "debe ser menor o igual que " + N_MAX_VALUE_FOR_NUMBER;
	public static final String N_MIN_VALUE_MESSAGE_FOR_SEQUENCE = "debe ser mayor o igual que " + N_MIN_VALUE_FOR_SEQUENCE;
	public static final String N_MAX_VALUE_MESSAGE_FOR_SEQUENCE = "debe ser menor o igual que " + N_MAX_VALUE_FOR_SEQUENCE;

}