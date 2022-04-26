FROM openjdk:11
VOLUME /tmp
COPY target/*.jar micro-collector.jar
ENTRYPOINT ["java","-jar","/micro-collector.jar"]
