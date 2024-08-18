FROM openjdk:17-alpine
EXPOSE 8083
COPY target/order-1.0.0.jar bvk-order.jar
ENTRYPOINT [ "java", "-jar", "bvk-order.jar" ]