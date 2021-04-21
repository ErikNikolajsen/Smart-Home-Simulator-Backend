package main;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPaho {
	
	private MqttClient client;
	
	public MqttPaho(String host, String port) throws MqttException {
		connect(host, port);
	}
	
	public void connect(String host, String port) throws MqttException {
		client = new MqttClient( 
				"tcp://"+host+":"+port, //URI //"tcp://broker.hivemq.com:1883" 
			    MqttClient.generateClientId(), //ClientId 
			    new MemoryPersistence()); //Persistence
		
		client.setCallback(new MqttCallback() {
		    @Override
		    public void connectionLost(Throwable cause) { //Called when the client lost the connection to the broker 
		    }

		    @Override
		    public void messageArrived(String topic, MqttMessage message) throws Exception {
		        System.out.println(topic + ": " + new String(message.getPayload()));
		    }

		    @Override
		    public void deliveryComplete(IMqttDeliveryToken token) {//Called when a outgoing publish is complete 
		    }
		});
		
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		//options.setConnectionTimeout(10);
		
		client.connect(options);
	}
	
	public boolean isConnected() {
		return client.isConnected();
	}
	
	public void subscribe(String topic) throws MqttException {
		client.subscribe(topic, 2);
	}
	
	
	public void publish(String topic, String payload) throws MqttPersistenceException, MqttException {
		client.publish( 
			    topic, // topic 
			    payload.getBytes(), // payload 
			    2, // QoS 
			    false); // retained? 
	}
	
	public void clearRetainedMessage(String topic) throws MqttPersistenceException, MqttException {
		client.publish( 
			    topic, // topic 
			    "".getBytes(), // payload 
			    2, // QoS 
			    true); // retained? 
	}
	
	public void disconnectMqtt() throws MqttException {
		client.disconnect();
	}
}
