package main;

import java.util.UUID;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;

public class MqttHive {
	
	Mqtt3AsyncClient client;
	
	public void connect() {
		// creates client
		client = MqttClient.builder()
		        .useMqttVersion3()
		        .identifier(UUID.randomUUID().toString())
		        .serverHost("broker.hivemq.com")
		        .serverPort(1883)
		        .buildAsync();
		
		// connects to broker
		client.connect()
	    .whenComplete((connAck, throwable) -> {
	        if (throwable != null) {
	        	System.out.println("Client connect error: "+throwable);
	        } else {
	        	System.out.println("Client connected succcessfully");
	        }
	    });
	}
	
	public void publish(String topic, String payload) {
		client.publishWith()
	    .topic(topic)
	    .payload(payload.getBytes())
	    .qos(MqttQos.EXACTLY_ONCE)
	    .send()
	    .whenComplete((mqtt3Publish, throwable) -> {
	        if (throwable != null) {
	            // Handle failure to publish
	        	System.out.println("Publish error: "+throwable);
	        } else {
	            // Handle successful publish, e.g. logging or incrementing a metric
	        	System.out.println("Published successfully");
	        }
	    });
	}
	
	public void subscribe(String topic) {
		client.subscribeWith()
        .topicFilter(topic)
        .callback(publish -> {
            // Process the received message
        	System.out.println(topic + ": " + new String(publish.getPayloadAsBytes()));
        })
        .send()
        .whenComplete((subAck, throwable) -> {
            if (throwable != null) {
                // Handle failure to subscribe
            	System.out.println("Subscription to \""+topic+"\" failed: "+throwable);
            } else {
                // Handle successful subscription, e.g. logging or incrementing a metric
            	System.out.println("Subscribed successfully to: "+topic);
            }
        });
	}
	
	public void disconnect() {
		client.disconnect();
	}
}
