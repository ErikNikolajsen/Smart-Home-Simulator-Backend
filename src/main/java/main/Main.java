package main;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication @ComponentScan(basePackages = {"controllers"} )
public class Main {
	public static void main(String[] args) throws InterruptedException, MqttException, JsonProcessingException, ClassNotFoundException {
				
		SpringApplication.run(Main.class, args);
		System.out.println(" ___    _____    ____  _                 _       _             \r\n"
				+ "|_ _|__|_   _|  / ___|(_)_ __ ___  _   _| | __ _| |_ ___  _ __ \r\n"
				+ " | |/ _ \\| |____\\___ \\| | '_ ` _ \\| | | | |/ _` | __/ _ \\| '__|\r\n"
				+ " | | (_) | |_____|__) | | | | | | | |_| | | (_| | || (_) | |   \r\n"
				+ "|___\\___/|_|    |____/|_|_| |_| |_|\\__,_|_|\\__,_|\\__\\___/|_|  \r\n"
				+ "============================================================\r\n"
				+ "                                                    (v1.0.0)");
	}
}





















