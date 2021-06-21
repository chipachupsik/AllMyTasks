package ExecutorsTasks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService fixPool = Executors.newFixedThreadPool(5);

        for(int i = 1; i < 11; i++) {
            final int temp = i;
            fixPool.submit(new Runnable() {
                @Override
                public void run() {
                    doExpensiveOperation(temp);
                }
            });
        }
        fixPool.shutdown();
        fixPool.awaitTermination(5, TimeUnit.SECONDS);

    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + " , localId=" + localId);
    }
}
