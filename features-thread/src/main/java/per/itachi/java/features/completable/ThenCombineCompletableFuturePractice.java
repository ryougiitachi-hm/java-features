package per.itachi.java.features.completable;

import lombok.extern.slf4j.Slf4j;
import per.itachi.java.features.vo.AccountVO;
import per.itachi.java.features.vo.OrderVO;
import per.itachi.java.features.vo.UserVO;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class ThenCombineCompletableFuturePractice implements CompletableFuturePractice{

    @Override
    public void practice() {
        CompletableFuture<OrderVO> futureOrder;
        CompletableFuture<UserVO> futureUser;
        CompletableFuture<AccountVO> futureAccount;
    }
}
