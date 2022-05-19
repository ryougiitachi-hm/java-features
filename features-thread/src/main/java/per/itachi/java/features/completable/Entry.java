package per.itachi.java.features.completable;

public class Entry {

    public static void main(String[] args) {
        CompletableFuturePractice practice = null;
//        practice = new ApplyCompletableFuturePractice();
//        practice = new AcceptCompletableFuturePractice();
//        practice = new ExceptionallyCompletableFuturePractice();
//        practice = new ThenCombineCompletableFuturePractice();
        practice = new AllOfCompletableFuturePractice();
        practice.practice();
    }

}
