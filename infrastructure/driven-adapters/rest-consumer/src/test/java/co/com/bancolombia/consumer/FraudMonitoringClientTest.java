package co.com.bancolombia.consumer;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes={co.com.bancolombia.consumer.FraudMonitoringClient.class})
class FraudMonitoringClientTest {
    @Mock
    private WebTestClient client;

    @Test
    void shouldgenerateCustomerSession(){

    }
}