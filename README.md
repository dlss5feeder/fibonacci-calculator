# Microservicio Fibonacci Calculator

### Consideraciones Generales
  * Este microservicio expone un API para calcular la secuencia de Fibonacci
  * Se exponen 2 endpoints:
    * Calcular secuencia de Fibonacci: método GET para calcular la secuencia de Fibonacci tras N iteraciones
    * Calcular numero de Fibonacci: método GET para calcular el número Fibonacci tras N iteraciones usando diferentes algoritmos

### Consideraciones Tecnicas
  * Se han modelado 2 endPoints para ilustrar alguna funcionalidad extra, manejo de beans, errores, etc
  * El segundo endPoint, permite calcular el número de Fibonacci con diferentes algoritmos, o ejecutanto todos en paralelo.
  * El primer endPoint (algoritmo iterativo) se ha limitado a n=90; el segundo se ha limitado más (n=45) ya que al ejecutar todos los algoritmos, en concreto el recursivo tardaría mucho
  * En ambos endPoints, se ha decidido "dejar pasar" la validación hasta la capa de service
  * Se han incluido tests de Cucumber para las dos principales clases (Controller y Service). Siendo puristas, el test sobre el controlador debería mockear el service y solo comprobar por ejemplo los códigos HTTP de respuesta
  * El MS se encuentra dockerizado y listo para su uso 

### URL GIT
https://github.com/ignacio-herrero-glago/fibonacci-calculator.git

### DOCKER
docker pull ignacioherrero/fibonacci-calculator:latest

### URL Swagger
http://localhost:8081/fibonacci-calculator/swagger-ui.html


### Arranque del microservicio
  * Para ver el código fuente: clonar el repositorio indicado arriba (ver URL GIT)
  * Para ejecutar el código fuente:
    * A). Bien en la ruta donde se ha clonado, ejecutar el comando "docker build -t ignacio-herrero-glago/fibonacci-calculator ."
    * B). Descargar directamente la imagen docker (ver DOCKER)
  * A continuación, arrancar el microservicio con "docker run --rm -p 8081:8081 --name fibonacci-calculator ignacio-herrero-glago/fibonacci-calculator"
  * Acceder a la URL del Swagger indicada arriba