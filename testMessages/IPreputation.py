import pika
import json
import random
import sys
import string
import time

if len(sys.argv) != 3:
    print("Usage: python3 IPreputation -n for negative events or -p for positive events IP")
    print("Usage: python3 IPreputation -p deviceExample1")
    sys.exit(1)

try:
    option = sys.argv[1]
    entityID = sys.argv[2]
except ValueError:
    print("Please provide a valid options. -p and deviceEXample1")
    option="-p"
    entityID="127.0.0.1"
    #sys.exit(1)

credentials = pika.PlainCredentials('reputation', 'asterisk')
parameters = pika.ConnectionParameters('aiot-rs-prod.dei.uc.pt', 
                              5672, '/',
                              credentials=credentials)

connection = pika.BlockingConnection(parameters)   
channel = connection.channel()

channel.queue_declare(queue='rs_queue', auto_delete=False, durable=True )

exchangeName = 'IPREPUTATION'
routingKeyName = 'rs_queue'

if (option == "-p"):
    #result: "negative"
    dicio = {
        "IP": entityID,
        "result": "positive"
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
        "result": "negative",
    }

    channel.basic_publish(exchange=exchangeName,
                        routing_key=routingKeyName,
                        body=json.dumps(dicio),
                        properties=pika.BasicProperties(
                            delivery_mode=2,  # make message persistent
                        ))
    print(" Sent %r" % ( dicio))
        