package ATM;

public class OperationThread extends Thread {
    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (account) {
                account.setBalance(account.getBalance() + 100);
                System.out.println("Пополнение баланса на 100р");
                System.out.println("Остаток средств на счёте = " + account.getBalance() + "р");
            }
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                }
        }
    }
}
