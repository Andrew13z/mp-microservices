FROM openjdk:11
VOLUME /tmp
COPY target/*.jar micro-sender.jar
ENTRYPOINT ["java","-jar","/micro-sender.jar"]
