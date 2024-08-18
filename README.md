# BVK Inventory Apps

## Table of Contents

- [Installation](#installation)

## Installation

### Prerequisites

List any prerequisites or dependencies that need to be installed before setting up the project.

### Steps

#### Clone the repository:
    git clone https://github.com/kalenxpesticide/bvk.git    

#### Navigate to the project directory and build ALL project
1. Open folder /common -> mvn clean install
2. Open folder /users -> mvn clean install
3. Open folder /product-inventory -> mvn clean install
4. Open folder /order -> mvn clean install
#### Start your docker
#### Deploy to docker
1. Open folder /users -> deploy.bat
2. Open folder /product-inventory -> deploy.bat
3. Open folder /order -> deploy.bat
#### Open Postman
    import project bvk.postman_collection.json in postman