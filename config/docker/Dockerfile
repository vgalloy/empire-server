FROM openjdk:8u171

WORKDIR server

EXPOSE 8080

COPY target/empire-server.jar empire-server.jar

CMD ["java", "-jar", "empire-server.jar"]