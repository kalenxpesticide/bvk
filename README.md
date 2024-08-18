# bvk
Inventory Apps
Step of installation :
1. Build ALL project
    a. Open folder /common -> mvn clean install
    b. Open folder /users -> mvn clean install
    c. Open folder /product-inventory -> mvn clean install
    d. Open folder /order -> mvn clean install
2. Start your docker
3. Deploy to docker
    a. Open folder /users -> deploy.bat
    b. Open folder /product-inventory -> deploy.bat
    c. Open folder /order -> deploy.bat
4. Open Postman
    import project bvk.postman_collection.json in postman