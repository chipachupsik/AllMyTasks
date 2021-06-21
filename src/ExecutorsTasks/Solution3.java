package ExecutorsTasks;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Solution3 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for(int i = 1; i <= 10; i++) {
            final int localId = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    doExpensiveOperation(localId);
                }
            });
        }

        List<Runnable> list = executor.shutdownNow();
        for(Runnable r : list) {
            System.out.println(r);
        }

    }

    public static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + " , localId=" + localId);
    }
}
