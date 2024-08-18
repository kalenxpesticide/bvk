FROM openjdk:17-alpine
EXPOSE 8080
COPY target/users-1.0.0.jar bvk-user.jar
ENTRYPOINT [ "java", "-jar", "bvk-user.jar" ]