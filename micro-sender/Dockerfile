FROM openjdk:11 as builder
RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN ./mvnw clean package

FROM openjdk:11
COPY --from=builder /app/source/target/*.jar /app/micro-sender.jar
ENTRYPOINT ["java","-jar","/app/micro-sender.jar"]
