package lesson19customexcptions.PracticeProject.Classes;

import lesson19customexcptions.PracticeProject.exceptions.InsufficientFundsException;
import lesson19customexcptions.PracticeProject.exceptions.InvalidDepositException;

import java.util.Scanner;

enum BankOption{

    DEPOSIT,
    WITHDRAW,
    EXIT

}

public class Main {

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount();

        System.out.println("Welcome,");

        System.out.println("Account balance: $" + bankAccount.getBalance());

        Scanner sc = new Scanner(System.in);

        while (true) {

            BankOption[] bankOptions = BankOption.values();

            System.out.println();

            for (BankOption bankOption1 : bankOptions) {

                System.out.println(bankOption1.name());

            }

            System.out.print("Choose an option: ");
            String option = sc.nextLine();

            try {

                BankOption bankOption = BankOption.valueOf(option.trim().toUpperCase());

                switch (bankOption) {

                    case DEPOSIT -> makeDeposit(bankAccount, sc);
                    case WITHDRAW -> makeWithdrawal(bankAccount, sc);
                    case EXIT -> System.exit(0);

                }

            } catch (IllegalArgumentException e) {

                System.err.println("Invalid option. You can DEPOSIT, WITHDRAW, or EXIT app.");
                System.out.println();

            }

        }

    }

    private static void makeDeposit(BankAccount bankAccount, Scanner sc) {

        double initialDeposit;

        try {

            System.out.println("Enter deposit amount: ");
            initialDeposit = sc.nextDouble();
            sc.nextLine();

            bankAccount.deposit(initialDeposit);

        }catch (InvalidDepositException e) {

            System.err.println("Error occurred!!!\n" + e.getMessage());

        }

    }

    private static void makeWithdrawal(BankAccount bankAccount, Scanner sc) {

        double initialWithdrawal;

        try{

            System.out.println("Enter withdrawal amount: ");
            initialWithdrawal = sc.nextDouble();
            sc.nextLine();

            bankAccount.withdraw(initialWithdrawal);

        }catch (InsufficientFundsException e){

            System.out.println("Error occurred!!!\n" + e.getMessage());

        }

    }

}
