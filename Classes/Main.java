package lesson19customexcptions.PracticeProject.Classes;

import lesson19customexcptions.PracticeProject.exceptions.InvalidDepositException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount();

        System.out.println("Account balance: $" + bankAccount.getBalance());

        double initialDeposit;

        try(Scanner sc = new Scanner(System.in)) {

            System.out.println("Enter deposit amount: ");
            initialDeposit = sc.nextDouble();
            sc.nextLine();

            bankAccount.deposit(initialDeposit);

        }catch (InvalidDepositException e) {

            System.err.println("Error occurred!!!\n" + e.getMessage());

        }

    }

}
