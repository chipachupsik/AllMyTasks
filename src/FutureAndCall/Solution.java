package FutureAndCall;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Solution {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Future<String>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(int i = 1000; i <= 1010; i++) {
            futures.add(executor.submit(getTask(i)));
        }

        futures.add(executor.submit(getTask(10000000)));

        for(Future<String> future : futures) {
            System.out.println(future.get());
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

    }

    public static Callable<String> getTask(final int i) {
        AtomicLong atomic = new AtomicLong(0);

        for(int j = 1; j <= i; j++) {
            atomic.addAndGet(j);
        }


        Callable<String> getResult = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return atomic.toString();
            }
        };


        return getResult;
    }
}
