package by.mikalai.wikimedia.wikimediareader.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KafkaProperties {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
}
