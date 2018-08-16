FROM openjdk:10.0.2-jre

WORKDIR server

EXPOSE 8080

COPY target/empire-server.jar empire-server.jar

CMD ["java", "-jar", "empire-server.jar"]
