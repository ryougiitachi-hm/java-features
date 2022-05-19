package per.itachi.java.features.completable;

import lombok.extern.slf4j.Slf4j;
import per.itachi.java.features.vo.OrderVO;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class ExceptionallyCompletableFuturePractice implements CompletableFuturePractice{

    @Override
    public void practice() {
        CompletableFuture<OrderVO> future = CompletableFuture
                .supplyAsync(()->{
                    log.info("supplyAsync");
                    int i = 1/0;
                    return new OrderVO();
                })
                .exceptionally(throwable -> {
                    // without this block, exception will thrown out of here.
                    log.info("exceptionally. ", throwable);
                    OrderVO orderVO = new OrderVO();
                    return orderVO;
                })
                ;
//        OrderVO result = future.get();
        OrderVO result = future.join();
        log.info("The result is {}. ", result);
    }
}
