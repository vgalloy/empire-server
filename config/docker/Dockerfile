FROM java:8

WORKDIR server

EXPOSE 8080

COPY target/empire-server.jar empire-server.jar

CMD ["java", "-jar", "empire-server.jar"]