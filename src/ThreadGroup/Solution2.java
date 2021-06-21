package ThreadGroup;

import java.util.concurrent.ThreadLocalRandom;

public class Solution2 {
    public static int getRandomIntegerBetweenNumbers(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }

    public static double getRandomDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public static long getRandomLongBetween0AndN(long n) {
        return ThreadLocalRandom.current().nextLong(0, n);
    }

    public static void main(String[] args) {
        System.out.println(getRandomIntegerBetweenNumbers(1, 5));
        System.out.println(getRandomDouble());
        System.out.println(getRandomLongBetween0AndN(6));
    }
}
