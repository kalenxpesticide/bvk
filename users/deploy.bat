CALL mvn clean package -DskipTests
CALL docker stop bvk-users
CALL docker rm bvk-users
CALL docker build -t springio/bvk-users .
CALL docker run -d -it --name bvk-users --network bvk-network -p 8080:8080 springio/bvk-users