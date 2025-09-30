package lesson19customexcptions.PracticeProject.Classes;

import lesson19customexcptions.PracticeProject.exceptions.InsufficientFundsException;
import lesson19customexcptions.PracticeProject.exceptions.InvalidDepositException;

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

            throw new InsufficientFundsException("Error! Insufficient funds. Withdraw cannot exceed your balance.");

        }

        balance -= amount;
        System.out.println("Withdraw of $" + amount + " successfully processed.");
        System.out.println("Account balance: $" + balance);

    }

}
