package per.itachi.java.features.completable;

import lombok.extern.slf4j.Slf4j;
import per.itachi.java.features.vo.OrderVO;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class AcceptCompletableFuturePractice implements CompletableFuturePractice{

    @Override
    public void practice() {
        CompletableFuture<OrderVO> future = CompletableFuture
                .supplyAsync(()->{
                    OrderVO orderVO = new OrderVO();
                    orderVO.setOrderNbr(UUID.randomUUID().toString());
                    return orderVO;
                });
        future.thenAcceptAsync(orderVO -> {
            // Seems like that this part may execute after outter thread.
            log.info("thenAcceptAsync: {}", orderVO);
            orderVO.setUsername(UUID.randomUUID().toString());
            // no any return
        });
        // always returns CompletableFuture<Void>, should be invoked in form of fluent.
        OrderVO orderVO = future.join();
        log.info("The result is {}. ", orderVO);
    }
}
