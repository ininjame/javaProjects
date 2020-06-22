
package app;
public class BankAccount {
    public double balance;
    public String accountOwner;

    public BankAccount(double initBalance, String owner) {
        this.balance = initBalance;
        this.accountOwner = owner;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }
    
    public void withdraw(double amount) {
        balance = balance - amount;
    }

    @Override
    public boolean equals(Object o) {
        return (((BankAccount) o).accountOwner).equals(accountOwner);
    }
}
