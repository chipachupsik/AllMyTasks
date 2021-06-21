package ATM;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(1000);
        OperationThread thread = new OperationThread();
        SecondOperationThread thread2 = new SecondOperationThread();
        thread.setAccount(account);
        thread2.setAccount(account);

        thread.start();
        thread2.start();
    }
}
