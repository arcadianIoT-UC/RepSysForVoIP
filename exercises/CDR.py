import pika
import json
import random
import sys
import string
import time

if len(sys.argv) != 3:
    print("Usage: python3 CDR -n for negative events or -p for positive events IP")
    print("Usage: python3 CDR -p deviceExample1")
    sys.exit(1)

try:
    option = sys.argv[1]
    entityID = sys.argv[2]
except ValueError:
    print("Please provide a valid options. -p and deviceEXample1")
    option="-p"
    entityID="127.0.0.1"
    #sys.exit(1)

credentials = pika.PlainCredentials('noms', 'tutorial')
parameters = pika.ConnectionParameters('localhost', 
                              5672, 'integration_environment',
                              credentials=credentials)

connection = pika.BlockingConnection(parameters)   
channel = connection.channel()

channel.queue_declare(queue='rs_queue', auto_delete=True)

exchangeName = 'CDR'
routingKeyName = 'rs_queue'

if (option == "-p"):
    
    dicio = {
        "IP": entityID,
        "result": "success"
    }

    channel.basic_publish(exchange=exchangeName,
                        routing_key=routingKeyName,
                        body=json.dumps(dicio),
                        properties=pika.BasicProperties(
                            delivery_mode=2,  # make message persistent
                        ))
    print(" Sent %r" % (dicio))


if (option == "-n"):
    dicio = {
        "IP": entityID,
        "result": "not successful",
        "severity": 3.0
    }

    channel.basic_publish(exchange=exchangeName,
                        routing_key=routingKeyName,
                        body=json.dumps(dicio),
                        properties=pika.BasicProperties(
                            delivery_mode=2,  # make message persistent
                        ))
    print(" Sent %r" % ( dicio))
        