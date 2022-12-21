package by.mikalai.wikimedia.elkwriter.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ChangesListener {
    public static final String WIKIMEDIA_TOPIC = "wikimediaTopic";
    public static final String GROUP_ID = "elk";

    @KafkaListener(topics = WIKIMEDIA_TOPIC, groupId = GROUP_ID)
    public void listen(@Payload String data) {
        System.out.println(data);
    }
}
