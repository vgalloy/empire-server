FROM openjdk:11.0.1-oracle

WORKDIR server

EXPOSE 8080

COPY target/empire-server.jar empire-server.jar

ADD http://central.maven.org/maven2/co/elastic/apm/elastic-apm-agent/1.2.0/elastic-apm-agent-1.2.0.jar /server/elastic-apm-agent-1.2.0.jar

CMD ["java", \
    "-javaagent:elastic-apm-agent-1.2.0.jar", \
    "-Delastic.apm.service_name=empire-server", \
    "-Delastic.apm.application_packages=com.vgalloy", \
    "-Delastic.apm.server_urls=https://apm.vgalloy.com", \
    "-Delastic.apm.verify_server_cert=false", \
    "-jar", "empire-server.jar"]
