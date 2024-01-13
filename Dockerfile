FROM maven:3.9-amazoncorretto-21 as before
WORKDIR /app
COPY /TicTacToeGame/pom.xml .
RUN mvn dependency:resolve-plugins dependency:resolve
ADD TicTacToeGame /app
RUN mvn install

FROM openjdk:17-oracle
ARG JAR_FILE=TicTacToeGame-0.0.1-SNAPSHOT.jar
COPY target/*.jar app.jar
EXPOSE 8080
CMD [ "java", "-jar",  "/TicTacToeGame-0.0.1-SNAPSHOT.jar"]
