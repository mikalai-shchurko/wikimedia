package by.mikalai.wikimedia.wikimediareader.wikimedia;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class WikimediaClient {
    private static final String STREAM_HOST = "https://stream.wikimedia.org";
    private static final String STREAM_URI = "/v2/stream/recentchange";
    private static final String MESSAGE_EVENT = "message";

    public Flux<ServerSentEvent<String>> getClient() {
        ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<>() {};
        return createEventClient(type);
    }

    private static Flux<ServerSentEvent<String>> createEventClient(ParameterizedTypeReference<ServerSentEvent<String>> type) {
        return WebClient.create(STREAM_HOST)
                .get()
                .uri(STREAM_URI)
                .retrieve()
                .bodyToFlux(type)
                .filter(event -> MESSAGE_EVENT.equals(event.event()));
    }
}
