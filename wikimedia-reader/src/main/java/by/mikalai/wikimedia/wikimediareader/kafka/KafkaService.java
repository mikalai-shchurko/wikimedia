package by.mikalai.wikimedia.wikimediareader.kafka;

import by.mikalai.wikimedia.wikimediareader.config.KafkaTopicConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String key, String value) {
        kafkaTemplate.send(KafkaTopicConfig.WIKIMEDIA_TOPIC, value).whenComplete((result, exception) -> logCallback(value, result, exception));
    }

    private void logCallback(String value, SendResult<String, String> result, Throwable exception) {
        if (result != null) {
            log.info("Sent message=[{}] with offset=[{}]", value, result.getRecordMetadata().offset());
        } else {
            log.error("Unable to send message=[{}] due to : {}", value, exception);
        }
    }
}
