FROM openjdk:11
ADD target/university-app.jar university-app.jar
ENTRYPOINT ["java", "-jar","university-app.jar"]
EXPOSE 8080