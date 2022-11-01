package co.com.bancolombia.consumer;

import co.com.bancolombia.model.customersession.CustomerSession;
import co.com.bancolombia.model.customersession.gateways.CustomerSessionRepository;
import util.LocalDateTimeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FraudMonitoringClient implements CustomerSessionRepository {

    private final WebClient client;


    // these methods are an example that illustrates the implementation of WebClient.
    // You should use the methods that you implement from the Gateway from the domain.

    public Mono<CustomerSessionResponse> testGet() {

        return client
            .get()
            .retrieve()
            .bodyToMono(CustomerSessionResponse.class);

    }

    public Mono<CustomerSessionResponse> testPost() {

        ObjectRequest request = ObjectRequest.builder()
            .val1("exampleval1")
            .val2("exampleval2")
            .build();

        return client
            .post()
            .body(Mono.just(request), ObjectRequest.class)
            .retrieve()
            .bodyToMono(CustomerSessionResponse.class);
    }

    @Override
    public Mono<CustomerSession> generateCustomerSession() {
        LocalDateTimeHelper localDateTimeHelper = new LocalDateTimeHelper();
        return client.get().retrieve().bodyToMono(CustomerSessionResponse.class).map(
                customerSessionResponse -> CustomerSession.builder().csid(customerSessionResponse.getCSID())
                                .date(
                                        localDateTimeHelper.toDate(customerSessionResponse.getDate(),customerSessionResponse.getTime())
                                )
                .build()
        );
    }
}