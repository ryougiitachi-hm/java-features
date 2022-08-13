package per.itachi.java.features.time.formatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class EntryFormatter {

    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.of(1, 0, 0, 0));
//        DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        System.out.println(ldt);
        System.out.println(ZonedDateTime.of(ldt, ZoneId.systemDefault()));
    }
}
