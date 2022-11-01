package co.com.bancolombia.model.customersession.gateways;

import co.com.bancolombia.model.customersession.CustomerSession;
import reactor.core.publisher.Mono;

public interface CustomerSessionRepository {
    Mono<CustomerSession> generateCustomerSession();
}
