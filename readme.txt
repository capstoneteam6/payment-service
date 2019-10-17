# Getting Started

# Download and install docker on your system
# Run mysql db for both the microservices, images will be pulled directly from Docker hub
# vdcs_collection_db
docker run --detach --name=mysql-vdcs --publish 3306:3306 --env MYSQL_ROOT_PASSWORD=password --env MYSQL_USER=pingsharad --env MYSQL_PASSWORD=password --env MYSQL_DATABASE=vdcs_collection_db  mysql:5.7
# vdcs_payment_db
docker run --detach --name=mysql-payment --publish 3307:3306 --env MYSQL_ROOT_PASSWORD=password --env MYSQL_USER=pingsharad --env MYSQL_PASSWORD=password --env MYSQL_DATABASE=vdcs_payment_db  mysql:5.7

# Run the microservices app in docker containers 
docker network create dairy
docker container run --publish 7100:7100 --network dairy --name=vdcs-collection pingsharad/vdcs-collection-service:0.0.1-SNAPSHOT
docker container run --publish 8100:8100 --network dairy --env VDS_COLLECTION_SERVICE_URI=http://vdcs-collection:7100 pingsharad/payment-service:0.0.1-SNAPSHOT

# service endpoints for vdcs-collection-service
http://localhost:7100/dairy/api/collection/farmer-list
http://localhost:7100/dairy/api/collection/vdcs-list
http://localhost:7100/dairy/api/collection/vdcs-collection

#health check
http://localhost:7100/dairy/api/collection/manage/health

# service endpoints for payment-service 
http://localhost:8100/dairy/api/payment/unit-rate/15
http://localhost:8100/dairy/api/payment/calculate-payment/farmerId/F0001

#health check
http://localhost:8100/dairy/api/payment/manage/health

# Docker image repos for both microservices
docker push pingsharad/vdcs-collection-service:0.0.1-SNAPSHOT
docker push pingsharad/payment-service:0.0.1-SNAPSHOT

docker ps -a
docker system prune

# AWS Load Balancer URL
http://team6-dairy-microservice-lb-1380634158.us-east-1.elb.amazonaws.com/dairy/api/payment/calculate-payment/farmerId/F0001