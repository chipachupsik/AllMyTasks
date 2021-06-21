package ATM;

public class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void setBalance(double balance) {
        if(balance < 0) {
            System.out.println("На карте недостаточно средств. Пополните баланс.");
            return;
        }
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
