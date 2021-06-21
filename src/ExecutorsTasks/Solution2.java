package ExecutorsTasks;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution2 {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Runnable> linkedBlockingDeque = new LinkedBlockingQueue<>();
        AtomicInteger atomicInteger = new AtomicInteger(1);

        for(int i = 0; i < 10; i++) {
            linkedBlockingDeque.add(new Runnable() {
                @Override
                public void run() {
                    doExpensiveOperation(atomicInteger.getAndIncrement());

                }
            });
        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3, 5, 1000, TimeUnit.MILLISECONDS, linkedBlockingDeque
        );

        threadPoolExecutor.prestartAllCoreThreads();
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);
    }


    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
