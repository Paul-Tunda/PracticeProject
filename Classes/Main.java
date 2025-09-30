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

                    case DEPOSIT -> makeDeposit(bankAccount);
                    case WITHDRAW -> makeWithdrawal(bankAccount);
                    case EXIT -> System.exit(0);
                    default -> System.out.println("Invalid option");

                }
            } catch (IllegalArgumentException e) {

                throw new RuntimeException("Invalid option. You can DEPOSIT, WITHDRAW, or EXIT app.");
            }

        }

    }

    private static void makeDeposit(BankAccount bankAccount) {

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

    private static void makeWithdrawal(BankAccount bankAccount) {

        double initialWithdrawal;

        try(Scanner sc = new Scanner(System.in)) {

            System.out.println("Enter withdrawal amount: ");
            initialWithdrawal = sc.nextDouble();
            sc.nextLine();

            bankAccount.withdraw(initialWithdrawal);

        }catch (InsufficientFundsException e){

            System.out.println("Error occurred!!!\n" + e.getMessage());

        }

    }

}
