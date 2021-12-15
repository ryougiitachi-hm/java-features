package per.itachi.java.features.collection.queue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntryQueue {

    public static void main(String[] args) {
        PriorityQueueInvoker priorityQueueInvoker = new PriorityQueueInvoker();
        priorityQueueInvoker.testOfferAndPoll();
    }
}
