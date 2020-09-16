Feature: Fibonacci calculator scenarios

# Positivos

	Scenario: Calculamos la secuencia de Fibonacci con exito
		When Calculo la secuencia de Fibonacci para <n>
		Then Obtengo como respuesta la secuencia '<fibonacci>'
		Examples:
		  | n  | fibonacci                             |
		  | 1  | [0, 1]                                |
		  | 5  | [0, 1, 1, 2, 3, 5]                    |
		  | 10 | [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55] |

	Scenario: Calculamos el numero de Fibonacci con exito
		When Calculo el numero de Fibonacci para <n> con el algoritmo '<fibonacciAlgorithm>'
		Then Obtengo como respuesta el numero <fibonacci>
		Examples:
		  | n  | fibonacciAlgorithm | fibonacci |
		  | 10 | RECURSIVE          | 55        |
		  | 10 | ITERATIVE          | 55        |
		  | 10 | MATRIX             | 55        |


# Negativos

	Scenario: Calculamos la secuencia de Fibonacci con error
		When Calculo la secuencia de Fibonacci para <n>
		Then Obtengo un error de validacion '<validationError>'
		Examples:
		  | n    | validationError                                             |
		  | -1   | calculateFibonacciSequence.n: debe ser mayor o igual que 0  |
		  | 110  | calculateFibonacciSequence.n: debe ser menor o igual que 90 |

	Scenario: Calculamos el numero de Fibonacci con error
		When Calculo el numero de Fibonacci para <n> con el algoritmo 'ITERATIVE'
		Then Obtengo un error de validacion '<validationError>'
		Examples:
		  | n    | validationError                                                                       |
		  | -1   | calculateFibonacciNumber.fibonacciCalculationRequest.n: debe ser mayor o igual que 0  |
		  | 110  | calculateFibonacciNumber.fibonacciCalculationRequest.n: debe ser menor o igual que 45 |