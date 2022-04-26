FROM openjdk:11
VOLUME /tmp
COPY target/*.jar eureka.jar
ENTRYPOINT ["java","-jar","/eureka.jar"]
