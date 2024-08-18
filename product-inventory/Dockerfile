FROM openjdk:17-alpine
EXPOSE 8081
COPY target/product-inventory-1.0.0.jar bvk-inventory.jar
ENTRYPOINT [ "java", "-jar", "bvk-inventory.jar" ]