FROM openjdk:11-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} superhero.jar
ENTRYPOINT ["java","-jar","/superhero.jar"]