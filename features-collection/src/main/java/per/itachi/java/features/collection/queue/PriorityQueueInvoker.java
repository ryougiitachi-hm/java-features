package per.itachi.java.features.collection.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

@Slf4j
public class PriorityQueueInvoker {

    public void testOfferAndPoll() {
        Queue<Integer> queue = new PriorityQueue<>();
        Random random = new Random(System.currentTimeMillis());
        Object element;
        log.info("Start offering int into queue. ");
        for (int i = 0; i < 20; ++i) {
            element = random.nextInt(20);
            queue.offer((Integer) element);
            log.info("{}", element);
        }
        log.info("Start polling int from queue. ");
        // iterator makes no guarantees about priority order.
//        for (Integer item: queue) {
//            log.info("{}", item);
//        }
        // dequeue can make guarantees about order.
        Integer item;
        while ((item = queue.poll()) != null) {
            log.info("{}", item);
        }
    }
}