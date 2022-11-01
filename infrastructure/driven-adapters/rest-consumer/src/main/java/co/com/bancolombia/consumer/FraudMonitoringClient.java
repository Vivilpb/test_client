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

    @Override
    public Mono<CustomerSession> generateCustomerSession() {
        LocalDateTimeHelper localDateTimeHelper = new LocalDateTimeHelper();
        return client.get().retrieve().bodyToMono(CustomerSessionResponse.class).map(
                customerSessionResponse -> CustomerSession.builder().csid(customerSessionResponse.getCSID())
                        .date(
                                localDateTimeHelper.toDate(customerSessionResponse.getDate(), customerSessionResponse.getTime())
                        )
                        .build()
        );
    }
}