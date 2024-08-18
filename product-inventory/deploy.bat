CALL mvn clean package -DskipTests
CALL docker stop bvk-inventory
CALL docker rm bvk-inventory
CALL docker build -t springio/bvk-inventory .
CALL docker run -d -it --name bvk-inventory --network bvk-network -p 8081:8081 springio/bvk-inventory