package es.spring.microservices.fibonacci.calculator.handlers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {                                    
    
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult()
	    	.getAllErrors()
	    	.forEach((error) -> {
	    		String fieldName = ((FieldError) error).getField();
	    		String errorMessage = error.getDefaultMessage();
	    		errors.put(fieldName, errorMessage);
	    	});
	    return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolationException(ConstraintViolationException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getConstraintViolations()
    	.forEach((constraint) -> {
    		Path fieldName = constraint.getPropertyPath();
    		String errorMessage = constraint.getMessage();
    		errors.put(fieldName.toString(), errorMessage);
    	});
	    return errors;	    
	}

}