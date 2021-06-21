public class Solution {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        t.setName("Admin Thread");

        System.out.println("Thread = " + t);

        int count = Thread.activeCount();
        System.out.println("currently active threads = " + count);

        Thread th[] = new Thread[count];
        Thread.enumerate(th);

        for(int i = 0; i < count; i++) {
            System.out.println(i + ": " + th[i]);
        }
    }
}
