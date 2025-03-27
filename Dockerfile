FROM bellsoft/liberica-openjre-alpine:21
WORKDIR /app
COPY target/PayFlow-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]