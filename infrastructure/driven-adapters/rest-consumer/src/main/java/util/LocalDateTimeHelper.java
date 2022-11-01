package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeHelper {

    public LocalDateTime toDate(String date, String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmmssSSS");
        return LocalDateTime.parse(date+" "+time, formatter);
    }
}
