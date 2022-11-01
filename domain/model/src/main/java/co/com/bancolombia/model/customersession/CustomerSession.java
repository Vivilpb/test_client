package co.com.bancolombia.model.customersession;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder(toBuilder = true)
public class CustomerSession {

    private final String csid;
    private final LocalDateTime date;
}
