package by.mikalai.wikimedia.wikimediareader.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaTopicConfig {
    public static final String WIKIMEDIA_TOPIC = "wikimediaTopic";

    @Bean
    public NewTopic wikimediaTopic() {
        return new NewTopic(WIKIMEDIA_TOPIC, 2, (short) 1);
    }
}
