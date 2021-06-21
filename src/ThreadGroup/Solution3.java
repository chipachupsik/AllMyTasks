package ThreadGroup;

import java.util.*;
import java.util.concurrent.*;

public class Solution3 {
    public static void main(String[] args) throws InterruptedException {
        Solution3 solution3 = new Solution3();
        List<String> list = new CopyOnWriteArrayList<>();

        solution3.startUpdatingThread(list);
        solution3.copyOnWriteSolution(list);
        solution3.stopUpdatingThread();
    }


        public void copyOnWriteSolution(List<String> list) throws InterruptedException {
            System.out.println("size = " + list.size());
            System.out.println();
            Thread.sleep(20);
            for(String element : list) {
                System.out.println("Element: " + element);
            }
            System.out.println("size = " + list.size());
            System.out.println();
            Thread.sleep(20);

            for(String element : list) {
                System.out.println("Element2: " + element);
            }

            System.out.println("size = " + list.size());
            stopUpdatingThread();
        }

        public void stopUpdatingThread() throws InterruptedException {
           t.stop();
           t.join();
        }

        private Thread t;

        private void startUpdatingThread(final List<String> list) {
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 20; i++) {
                        list.add("s" + ThreadLocalRandom.current().nextInt());
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            });
            t.start();
        }

}

