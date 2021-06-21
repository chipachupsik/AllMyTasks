package Threads;

public class RunThreads implements Runnable {
    public static void main(String[] args) {
        RunThreads runner = new RunThreads();
        Thread first = new Thread(runner);
        Thread second = new Thread(runner);
        first.setName("FIRST");
        second.setName("SECOND");
        first.start();
        second.start();
    }

    @Override
    public void run() {
        for(int i = 0; i < 25; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println("Сейчас работает " + threadName);
        }
    }
}
