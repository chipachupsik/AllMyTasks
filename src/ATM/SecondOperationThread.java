package ATM;

public class SecondOperationThread extends Thread {
    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (account) {
                account.setBalance(account.getBalance() - 100);
                System.out.println("Списание средств в размере 100р");
                System.out.println("Остаток средств на счёте = " + account.getBalance() + "р");
            }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
        }
    }
}
