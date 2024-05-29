# For Rabitmq
# Activate radministration interface
##  docker exec -it repsys-simplified-rabbitmq-1 /bin/bash
##  (docker) rabbitmq-plugins enable rabbitmq_management
#
# Create Exchanges:
#
## in RMQ_EXCHANGES and associate to rs_queue
## 

# COMPILE
# mvn compile
# mvn package

export ENV="production"
#ENV ENV="development"
export LOCAL_THREADS=3
export INPUT_PATH=/app/resources/test-logs/HDFS
export FILE_NAME=HDFS_2k.log
export MAVEN_OPTS="-Xmx6g -XX:ReservedCodeCacheSize=512m"

# RabbitMQ info
export RMQ_HOST="aiot-rs-prod.dei.uc.pt"
export RMQ_PORT="5672"
export RMQ_VIRTUAL_HOST="/"
export RMQ_QUEUE_NAME="rs_queue"
export RMQ_ROUTING_KEY="#"
export RMQ_PORT=5672
export RMQ_USERNAME="reputation"
export RMQ_PASSWORD="asterisk"

export RMQ_EXCHANGES="IPREPUTATION,USERREPUTATION"

# Mysql INFO
export DB_URL="jdbc:mysql://localhost:3306/dbAsterisk"
export DB_DB="dbAsterisk"
export DB_USER="noms"
export DB_PASSWORD="tutorial"

export EXECUTOR_MEMORY="10g"
export DRIVER_MEMORY="10g"

java -Xmx8096m -jar target/ReputationSystem-1.0-jar-with-dependencies.jar
