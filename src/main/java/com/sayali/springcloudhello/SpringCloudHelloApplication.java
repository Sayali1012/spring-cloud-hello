package com.sayali.springcloudhello;

import org.springframework.boot.CommandLineRunner;
// org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
// org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// org.springframework.context.annotation.Bean;
import com.sayali.springcloudhello.repository.ContactRepository;
import com.sayali.springcloudhello.model.Contact;

import java.util.stream.LongStream;

@SpringBootApplication
public class SpringCloudHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudHelloApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ContactRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11)
					.mapToObj(i -> new Contact(i, "Contact " + i, String.format("contact%d@email.com", i), "(111) 111-111"))
					.map(v -> repository.save(v))
					.forEach(System.out::println);
		};
	}

}
