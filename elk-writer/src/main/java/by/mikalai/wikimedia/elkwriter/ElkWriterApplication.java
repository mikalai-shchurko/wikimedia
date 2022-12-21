package by.mikalai.wikimedia.elkwriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ElkWriterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElkWriterApplication.class, args);
	}

}
