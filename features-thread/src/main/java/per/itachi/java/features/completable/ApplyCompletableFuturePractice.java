package per.itachi.java.features.completable;

import lombok.extern.slf4j.Slf4j;
import per.itachi.java.features.vo.OrderVO;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class ApplyCompletableFuturePractice implements CompletableFuturePractice{

    @Override
    public void practice() {
        CompletableFuture<OrderVO> future = CompletableFuture
                .supplyAsync(()->{ // create order
                    OrderVO orderVO = new OrderVO();
//                    OrderVO orderVO = null;
                    orderVO.setOrderNbr(UUID.randomUUID().toString());
                    log.info("supplyAsync: Created order, orderNbr={}. ", orderVO.getOrderNbr());
                    return orderVO;
                })
                .whenCompleteAsync((orderVO, throwable)->{
                    log.info("whenComplete: orderVO={}, throwable={}", orderVO, throwable);
                })
                .exceptionally(throwable -> {
                    if (throwable == null) {
                        log.error("No exception. ");
                    }
                    else {
                        log.error("Error occurred. ", throwable);
                    }
                    return new OrderVO();
                })
                .thenApplyAsync(orderVO -> { // won't execute if exception occurs.
                    orderVO.setUsername(UUID.randomUUID().toString());
                    log.info("thenApplyAsync: Apply user, username={}", orderVO.getUsername());
                    return orderVO;
                })
                .whenCompleteAsync((orderVO, throwable)->{
                    log.info("whenComplete: orderVO={}, throwable={}", orderVO, throwable);
                })
                .exceptionally(throwable -> {
                    if (throwable == null) {
                        log.error("No exception. ");
                    }
                    else {
                        log.error("Error occurred. ", throwable);
                    }
                    return new OrderVO();
                });
        OrderVO orderVO = future.join(); // unchecked exception
//        OrderVO orderVO = future.get(); // checked exception
        log.info("The result is {}. ", orderVO);
    }
}
