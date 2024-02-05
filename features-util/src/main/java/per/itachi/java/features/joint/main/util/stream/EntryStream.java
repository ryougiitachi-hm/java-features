package per.itachi.java.features.joint.main.util.stream;

import per.itachi.java.features.app.core.util.stream.StreamPractice;
import per.itachi.java.features.app.core.util.stream.impl.CollectStreamPractice;
import per.itachi.java.features.app.core.util.stream.impl.PeekStreamPractice;

public class EntryStream {

    public static void main(String[] args) {
//        StreamPractice streamPractice = new PeekStreamPractice();
        StreamPractice streamPractice = new CollectStreamPractice();
        streamPractice.exerciseNormal();
        streamPractice.exerciseException();
    }
}
