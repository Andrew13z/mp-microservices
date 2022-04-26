FROM openjdk:11
VOLUME /tmp
COPY target/*.jar micro-recipient.jar
ENTRYPOINT ["java","-jar","/micro-recipient.jar"]
