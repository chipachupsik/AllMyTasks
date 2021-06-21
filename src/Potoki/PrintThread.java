package Potoki;

public class PrintThread extends Thread {
    private Object lock;

    public PrintThread(Object lock) {
        this.lock = lock;
    }

    public void run() {
        while(true) {
            synchronized (lock) {
                try {
                    System.out.println(getName());
                    lock.notify();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
