# Reputation System for VoIP Systems

The tutorial is a result of the ARCADIAN-IoT project (arcadian-iot.eu)


## Requirements

- Java OpenJDK11 
if you aim to compile the application on your system

- Python3
- Pika client to connect to RabbitMQ
- Docker
- Docker compose

## Running the code

### Start RabbitMQ and MySQL

```
docker-compose -f docker-composeRedis up
```
If everything is ok, access to the administration interface in your browser
http://localhost:15672
(credentials are in the docker compose file)

On the administration interface you need to create:

- The queue: rs_queue
- The exchanges: reputationUpdates, registration, 5gAUSF, reputationPolicies and associate them to the rs_queue.

### Start the reputation system

#### Using docker

perform the build:
```
docker build -i RepSys .
```

Run the created container
```
docker run --rm -it RepSys 
```

#### Using the command line

Build the reputation system with maven.

Execute the script:
```
run.sh
```

## Tests:



## Credits:
- Bruno Sousa, University of Coimbra
- João Nunes, University of Coimbra
- Daniel Vasconcelos, University of Coimbra