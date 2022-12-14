package co.com.bancolombia.consumer;

    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
public class CustomerSessionResponse {

private String CSID;
private String date;
private String time;

}