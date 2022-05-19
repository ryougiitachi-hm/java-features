package per.itachi.java.features.completable;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import per.itachi.java.features.vo.AccountVO;
import per.itachi.java.features.vo.OrderVO;
import per.itachi.java.features.vo.UserVO;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class ThenCombineCompletableFuturePractice implements CompletableFuturePractice{

    @Override
    public void practice() {
        // initialize.
        CompletableFuture<OrderVO> futureOrder = CompletableFuture
                .supplyAsync(()-> {
                    log.info("Start handling order info vo. ");
                    OrderVO orderVO = new OrderVO();
                    orderVO.setOrderNbr(UUID.randomUUID().toString());
                    return orderVO;
                })
                .exceptionally(throwable -> { // no exceptionallyAsync found ?
                    log.error("Error occurred when handling order. ", throwable);
                    return new OrderVO();
                });
        CompletableFuture<UserVO> futureUser = CompletableFuture
                .supplyAsync(()->{
                    log.info("Start handling user info vo. ");
                    UserVO userVO = new UserVO();
                    userVO.setUsername(String.valueOf(ThreadLocalRandom.current().nextLong(123456L)));
                    userVO.setUserType(ThreadLocalRandom.current().nextInt(1, 10));
                    return userVO;
                })
                .exceptionally(throwable -> {
                    log.error("Error occurred when handling user. ", throwable);
                    return new UserVO();
                });
        CompletableFuture<AccountVO> futureAccount = CompletableFuture
                .supplyAsync(()->{
                    log.info("Start handling account info vo. ");
                    AccountVO accountVO = new AccountVO();
                    accountVO.setAccountNbr(UUID.randomUUID().toString());
                    accountVO.setAccountType(ThreadLocalRandom.current().nextInt(1, 10));
                    accountVO.setCdate(LocalDateTime.now());
                    return accountVO;
                })
                .exceptionally(throwable -> {
                    log.error("Error occurred when handling account. ", throwable);
                    return new AccountVO();
                });

        // compose
        OrderVO result = futureOrder
                .thenCombineAsync(futureUser, (orderVO, userVO) -> {
                    log.info("Combining order vo with user vo. ");
                    orderVO.setUsername(userVO.getUsername());
                    return orderVO;
                })
                .thenCombineAsync(futureAccount, (orderVO, accountVO) -> {
                    log.info("Combining order vo with account vo. ");
                    orderVO.setPaidAccountNbr(accountVO.getAccountNbr());
                    return orderVO;
                })
                .join();

        log.info("The result is {}. ", result);
    }
}
