package com.kafka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	private String topic = "spring-kafka";
	
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {
		
		template.send(topic, "Hi" +name);
		return "Data has been published";
	}
	
	@GetMapping("/publishJson")
	public String publishMessage() {
		User user = new User(123, "Sabeeha", new String[] {"Shimoga", "JH PAtel Nagar", "F Block"});
		template.send(topic, user);
		return "JSON Data has been published";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
