FROM openjdk:17-oracle
ARG JAR_FILE=TicTacToeGame-0.0.1-SNAPSHOT.jar
WORKDIR = /app
COPY TicTacToeGame/target/${JAR_FILE} /app
EXPOSE 8080
CMD [ "java", "-jar",  "/TicTacToeGame-0.0.1-SNAPSHOT.jar"]
