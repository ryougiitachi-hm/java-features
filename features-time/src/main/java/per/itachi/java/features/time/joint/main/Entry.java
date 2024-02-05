package per.itachi.java.features.time.joint.main;

import per.itachi.java.features.time.app.core.time.TimePractice;
import per.itachi.java.features.time.app.core.time.impl.LocalDateTimePractice;

public class Entry {

    public static void main(String[] args) {
        TimePractice timePractice = new LocalDateTimePractice();
        timePractice.doExercise();
    }
}
