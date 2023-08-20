package id.rockierocker.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class ElasticsearchKafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchKafkaConsumerApplication.class, args);
	}

}
