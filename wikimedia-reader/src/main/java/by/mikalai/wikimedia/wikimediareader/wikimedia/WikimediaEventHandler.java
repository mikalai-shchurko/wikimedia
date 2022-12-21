package by.mikalai.wikimedia.wikimediareader.wikimedia;

import by.mikalai.wikimedia.wikimediareader.kafka.KafkaService;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;

@Slf4j
@RequiredArgsConstructor
@Component
public class WikimediaEventHandler {
    private final WikimediaClient wikimediaClient;
    private final KafkaService kafkaService;
    private Disposable disposableSubscription;

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        this.disposableSubscription = wikimediaClient.getClient()
                .subscribe(event -> kafkaService.send(event.id(), event.data()),
                        error -> log.error("Event retrieval failed", error));
    }


    //otherwise we have 'Connection prematurely closed DURING response' exception
    @PreDestroy
    public void stop() {
        if (disposableSubscription != null) {
            disposableSubscription.dispose();
        }
    }
}
