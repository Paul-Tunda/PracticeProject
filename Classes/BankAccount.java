package lesson19customexcptions.PracticeProject.Classes;

import lesson19customexcptions.PracticeProject.exceptions.InsufficientFundsException;
import lesson19customexcptions.PracticeProject.exceptions.InvalidDepositException;
import lesson19customexcptions.PracticeProject.exceptions.InvalidTransactionException;

public class BankAccount {

    private double balance;

    public BankAccount() {

        this.balance = 0.0;

    }

    public double getBalance() {

        return balance;

    }

    public void deposit(double amount) throws InvalidDepositException {

        if (amount <= 0.0) {

            throw new InvalidDepositException("Invalid deposit amount! Deposit amount must be greater than 0.");

        }

        balance += amount;
        System.out.println("Deposit of $" + amount + " successfully processed.");
        System.out.println("Account balance: $" + balance);

    }

    public void withdraw(double amount) throws InsufficientFundsException {

        if (amount > balance) {

            throw new InsufficientFundsException("Insufficient funds. Withdraw cannot exceed your balance.");

        }

        balance -= amount;
        System.out.println("Withdraw of $" + amount + " successfully processed.");
        System.out.println("Account balance: $" + balance);

    }

    public void transfer(double amount) throws InvalidTransactionException {

        if (amount <= 0.0) {

            throw new InvalidTransactionException("Invalid transfer amount! Amount must be greater than 0.");

        }else if(amount > balance) {

            throw new InvalidTransactionException("Invalid transfer amount! Amount must be less than your balance.");

        }

        balance -= amount;
        System.out.println("Transfer of $" + amount + " successfully processed.");
        System.out.println("Account balance: $" + balance);

    }

}
