package com.example.sensor_api;

import com.example.sensor_api.client.AuthClient;
import com.example.sensor_api.client.SensorClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SensorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AuthClient authClient, SensorClient sensorClient) {
		return args -> {
			String username = "admin";
			String password = "password";
			String token = authClient.getJwtToken(username, password);

			if (token == null) {
				System.err.println("Failed to authenticate. Exiting...");
				return;
			}

			sensorClient.setJwtToken(token);

			String sensorName = "TestSensor";
			System.out.println("Registering sensor...");
			sensorClient.registerSensor(sensorName);

			System.out.println("Adding measurements...");
			for (int i = 0; i < 10; i++) {
				double randomValue = -20 + Math.random() * 40;
				boolean raining = Math.random() > 0.5;
				sensorClient.addMeasurement(randomValue, raining, sensorName);
			}

			System.out.println("Fetching all measurements...");
			sensorClient.getAllMeasurements();
		};
	}
}
