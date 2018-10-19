FROM openjdk:11.0.1-oracle

WORKDIR server

EXPOSE 8080

COPY target/empire-server.jar empire-server.jar

CMD ["java", "-jar", "empire-server.jar"]
