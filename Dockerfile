FROM openjdk:17-oracle
ARG JAR_FILE=TicTacToeGame-0.0.1-SNAPSHOT.jar
WORKDIR /app
ADD TicTacToeGame /app
RUN mvn install

COPY target/*.jar app.jar
EXPOSE 8080
CMD [ "java", "-jar",  "/TicTacToeGame-0.0.1-SNAPSHOT.jar"]
