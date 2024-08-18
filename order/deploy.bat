CALL mvn clean package -DskipTests
CALL docker stop bvk-order
CALL docker rm bvk-order
CALL docker build -t springio/bvk-order .
CALL docker run -d -it --name bvk-order --network bvk-network -p 8083:8083 springio/bvk-order