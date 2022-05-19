package per.itachi.java.features.completable;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import per.itachi.java.features.vo.AccountVO;
import per.itachi.java.features.vo.OrderVO;
import per.itachi.java.features.vo.UserVO;

@Slf4j
public class AllOfCompletableFuturePractice implements CompletableFuturePractice{

    @Override
    public void practice() {
        // initialize
        CompletableFuture<OrderVO> futurnOrder = CompletableFuture
                .supplyAsync(()->{
                    log.info("Start handling order. ");
                    OrderVO orderVO = new OrderVO();
                    orderVO.setOrderNbr(UUID.randomUUID().toString());
                    return orderVO;
                });
        CompletableFuture<UserVO> futurnUser = CompletableFuture
                .supplyAsync(()->{
                    log.info("Start handling user. ");
                    UserVO userVO = new UserVO();
                    userVO.setUsername(UUID.randomUUID().toString());
                    return userVO;
                });
        CompletableFuture<AccountVO> futurnAccount = CompletableFuture
                .supplyAsync(()->{
                    log.info("Start handling account. ");
                    AccountVO accountVO = new AccountVO();
                    accountVO.setAccountNbr(UUID.randomUUID().toString());
                    return accountVO;
                });

        // executing
        log.info("Executing futures. ");
        CompletableFuture.allOf(futurnOrder, futurnUser, futurnAccount);

        // blocking thread
        OrderVO orderVO = futurnOrder.join();
        UserVO userVO = futurnUser.join();
        AccountVO accountVO = futurnAccount.join();
        orderVO.setUsername(userVO.getUsername());
        orderVO.setPaidAccountNbr(accountVO.getAccountNbr());
        log.info("The result is {}. ", orderVO);
    }
}
