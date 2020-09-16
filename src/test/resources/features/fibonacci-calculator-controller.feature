Feature: Fibonacci calculator scenarios

# Positivos

	Scenario: Calculamos la secuencia de Fibonacci con exito
		When Calculo la secuencia de Fibonacci para <n>
		Then Obtengo como codigo de respuesta para la secuencia un <code>
		Examples:
		  | n  | code |
		  | 1  | 200  |
		  | 5  | 200  |
		  | 10 | 200  |

	Scenario: Calculamos el numero de Fibonacci con exito
		When Calculo el numero de Fibonacci para <n> con el algoritmo '<fibonacciAlgorithm>'
		Then Obtengo como codigo de respuesta para el numero un <code>
		Examples:
		  | n  | fibonacciAlgorithm | code |
		  | 10 | RECURSIVE          | 200  |
		  | 10 | ITERATIVE          | 200  |
		  | 10 | MATRIX             | 200  |
		  

# Negativos

	Scenario: Calculamos la secuencia de Fibonacci con error
		When Calculo la secuencia de Fibonacci para <n>
		Then Obtengo el mensaje de error '<field>' - '<message>'
		Examples:
		  | n   | field                        | message                       |
		  | -1  | calculateFibonacciSequence.n | debe ser mayor o igual que 0  |
		  | 110 | calculateFibonacciSequence.n | debe ser menor o igual que 90 |

	Scenario: Calculamos el numero de Fibonacci con error
		When Calculo el numero de Fibonacci para <n> con el algoritmo 'ITERATIVE'
		Then Obtengo el mensaje de error '<field>' - '<message>'
		Examples:
		  | n   | field                                                  | message                       |
		  | -1  | calculateFibonacciNumber.fibonacciCalculationRequest.n | debe ser mayor o igual que 0  |
		  | 110 | calculateFibonacciNumber.fibonacciCalculationRequest.n | debe ser menor o igual que 45 |
