package co.com.bancolombia.consumer;

import co.com.bancolombia.model.customersession.CustomerSession;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;


@SpringBootTest(classes = {co.com.bancolombia.consumer.FraudMonitoringClient.class, co.com.bancolombia.consumer.config.RestConsumerConfig.class})
@TestPropertySource(locations = "classpath:test.properties")
class FraudMonitoringClientTest {
    @Autowired
    private FraudMonitoringClient fraudMonitoringClient;
    public static MockWebServer mockBackEnd;

    @Value("${adapter.restconsumer.port}")
    private static int port=9000;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        System.out.println("------------------>"+ port);
        mockBackEnd.start(port);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }


    @Test
    void shouldGenerateCustomerSession() {


        mockBackEnd.enqueue(new MockResponse()
                .setBody("{\"csid\":\"111111\", \"date\":\"20200405\", \"time\":\"231234333\"}")
                .addHeader("Content-Type", "application/json"));


        //fraudMonitoringClient = new FraudMonitoringClient(WebClient.create(baseUrl));

        Mono<CustomerSession> customerSessionMono = fraudMonitoringClient.generateCustomerSession();


        StepVerifier.create(customerSessionMono)
                .expectNextMatches(customerSession -> customerSession.getCsid().equals("111111"))
                .verifyComplete();

        // test when we are calling a specific route -> /csid
        RecordedRequest recordedRequest;
        try {
            recordedRequest = mockBackEnd.takeRequest();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("este es el resultado-----------------__> " + recordedRequest.getPath());

    }


}