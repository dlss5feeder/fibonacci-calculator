FROM openjdk:11.0.8-slim
COPY ./target/fibonacci-calculator-1.0.0.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8081
CMD java -jar fibonacci-calculator-1.0.0.jar
