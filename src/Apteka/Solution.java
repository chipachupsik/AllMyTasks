package Apteka;

import java.util.*;

public class Solution {
    static List<String> strings = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        new Printer().start();
        new Operator().start();
    }

    public static class Printer extends Thread {

        @Override
        public void run() {
            Scanner in = new Scanner(System.in);

            while(true) {
                synchronized (strings) {
                    strings.add(in.nextLine());
                    strings.notify();
                }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public static class Operator extends Thread {

        @Override
        public void run() {
            while (strings.isEmpty()) {
                synchronized (strings) {
                    try {
                        strings.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(strings.remove(0));
                }
            }
        }
    }
}
