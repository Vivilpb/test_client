package util;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeHelperTest {

    LocalDateTimeHelper localDateTimeHelper = new LocalDateTimeHelper();

    @Test
    void toDate() {

        String expectedDate = "20201023";
        String expectedTime = "111510051";
        var actualLocalDateTime = localDateTimeHelper.toDate(expectedDate, expectedTime);
        var actualDate = actualLocalDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        var actualTime = actualLocalDateTime.format(DateTimeFormatter.ofPattern("hhmmssSSS"));

        assertEquals(expectedDate, actualDate);
        assertEquals(expectedTime, actualTime);
    }
}