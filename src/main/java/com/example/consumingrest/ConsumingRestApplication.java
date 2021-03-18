package com.example.consumingrest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class ConsumingRestApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);
	private static int id;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Type the id of the Star Wars character you want to search: ");
		id =Integer.parseInt(s.next());
		SpringApplication.run(ConsumingRestApplication.class, args);
		s.close();

	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}


	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Person person = restTemplate.getForObject(
					("https://swapi.dev/api/people/"+id+"/"), Person.class);
			log.info(person.toString());
		};
	}
}
