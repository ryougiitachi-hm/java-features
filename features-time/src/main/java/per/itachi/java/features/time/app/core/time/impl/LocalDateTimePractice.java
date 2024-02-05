package per.itachi.java.features.time.app.core.time.impl;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import per.itachi.java.features.time.app.core.time.TimePractice;

@Slf4j
public class LocalDateTimePractice implements TimePractice {

    @Override
    public void doExercise() {
        // LocalDateTime for Clock
        Clock clock = Clock.systemDefaultZone();
        LocalDateTime localDateTimeClock = LocalDateTime.now(clock); // alternatively, ZoneId
        log.info("localDateTimeClock={}", localDateTimeClock);
        // LocalDateTime from Date
        Date fromDate = new Date();
        LocalDateTime localDateTimeFromDate = fromDate.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
        log.info("localDateTimeFromDate={}", localDateTimeFromDate);
        // LocalDateTime to Date
        LocalDateTime localDateTimeToDate = LocalDateTime.now();
        Date toDate = Date.from(localDateTimeToDate.atZone(ZoneId.systemDefault()).toInstant());
        log.info("toDate={}", toDate);
    }
}
