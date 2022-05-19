package per.itachi.java.features.completion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class CompletionServicePracticeImpl implements CompletionServicePractice{

    @Override
    public void practice() {
        // when the number of threads is less than the number tasks,
        // the results is not in the order of ascending.
        int countOfTasks = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletionService<BigDecimal> completionService = new ExecutorCompletionService<>(executorService);

        for (int i = 0; i <= countOfTasks; ++i) {
            completionService.submit(()->{
                long result = ThreadLocalRandom.current().nextLong(500L, 3000L);
                Thread.sleep(result);
                return BigDecimal.valueOf(result);
            });
        }

        List<BigDecimal> results = new ArrayList<>();
        try {
            for (int i = 0; i <= countOfTasks; ++i) {
                results.add(completionService.take().get());
            }
        }
        catch (InterruptedException| ExecutionException e) {
            e.printStackTrace();
        }

        for (BigDecimal item: results) {
            System.out.println(item);
        }

        executorService.shutdown();
    }
}
