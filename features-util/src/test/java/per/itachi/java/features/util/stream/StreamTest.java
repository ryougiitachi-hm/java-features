package per.itachi.java.features.util.stream;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class StreamTest {

    /**
     * Initial operation. create Stream, with an array
     * */
    @Test
    public void testOf() {
        Stream<String> stream = Stream.of("1", "1", "2", "2", "3", "3");
        log.info("The value of stream is [{}]", stream.collect(Collectors.toList()));
    }

    /**
     * Initial operation. create Stream, with a Suppiler.
     * */
//    @Test
    public void testGenerate() {
        Stream<String> stream = Stream.generate(() -> UUID.randomUUID().toString());
        log.info("The value of stream is [{}]", stream.collect(Collectors.toList())); // OutOfMemoryError
    }

    /**
     * Initial operation. create Stream, don't understand
     * */
    @Test
    public void testIterate() {
//        Stream<String> stream = Stream.iterate();
//        log.info("The value of stream is [{}]", stream.collect(Collectors.toList()));
    }

    /**
     * Intermediate operation. ignore
     * */
    @Test
    public void testFilter() {
//        Stream<String> stream = Stream.iterate();
//        log.info("The value of stream is [{}]", stream.collect(Collectors.toList()));
    }

    /**
     * Intermediate operation. distinct with equals and hashCode.
     * */
    @Test
    public void testDistinct() {
        Stream<String> stream = Stream.of("1", "1", "2", "2", "3", "3");
        // invoking separately causes "stream has already been operated upon or closed"
//        stream.distinct();
//        log.info("The value of stream is [{}]", stream.collect(Collectors.toList()));
        log.info("The value of stream is [{}]", stream.distinct().collect(Collectors.toList()));
    }

    /**
     * Intermediate operation.
     * */
    @Test
    public void testLimit() {
        Stream<String> stream = Stream.of("1", "1", "2", "2", "3", "3");
        // invoking separately causes "stream has already been operated upon or closed"
//        stream.limit(3);
//        log.info("The value of stream is [{}]", stream.collect(Collectors.toList()));
        log.info("The value of stream is [{}]", stream.limit(3).collect(Collectors.toList()));
    }

    /**
     * Intermediate operation.
     * */
    @Test
    public void testSkip() {
        Stream<String> stream = Stream.of("1", "1", "2", "2", "3", "3");
        // invoking separately causes "stream has already been operated upon or closed"
//        stream.skip(1);
//        log.info("The value of stream is [{}]", stream.collect(Collectors.toList()));
        log.info("The value of stream is [{}]", stream.skip(1).collect(Collectors.toList()));
    }

    /**
     * Intermediate operation.
     * */
    public void testMap() {}

    /**
     * Intermediate operation.
     * */
    public void testFlatMap() {}

    /**
     * Intermediate operation. java.lang.Comparable
     * */
    @Test
    public void testSorted() {
        Stream<String> stream = Stream.of("1", "2", "20", "10", "3", "1");
        // invoking separately causes "stream has already been operated upon or closed"
//        stream.sorted();
//        log.info("The value of stream is [{}]", stream.collect(Collectors.toList()));
        // [1, 1, 10, 2, 20, 3]
        log.info("The value of stream is [{}]", stream.sorted().collect(Collectors.toList()));
    }
    /**
     * Finalized operations.
     * <ul>
     *     <li>max</li>
     *     <li>min</li>
     *     <li>count</li>
     *     <li>allMatch</li>
     *     <li>anyMatch</li>
     *     <li>foreach</li>
     *     <li>collect</li>
     *     <li>findFirst</li>
     *     <li>reduce</li>
     * </ul>
     * */

    @Test
    public void testReduce() {}

}
