package lesson19customexcptions.PracticeProject.Classes;

import lesson19customexcptions.PracticeProject.exceptions.InsufficientFundsException;
import lesson19customexcptions.PracticeProject.exceptions.InvalidDepositException;
import lesson19customexcptions.PracticeProject.exceptions.InvalidTransferException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

enum BankOption{

    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT

}

public class Main {

    public static void main(String[] args) {

        String osName = System.getProperty("os.name").toLowerCase();

        File myFile;

        if (osName.contains("win")) {

            myFile = new File ("C:/Users/pault/OneDrive/Desktop/2025-RTT-23/JAVA-PRACTICE/BRO-CODE/brocode/src/lesson19customexcptions/PracticeProject/documentation/account-balance.txt");

        }else if (osName.contains("mac")) {

            myFile = new File("/Users/tundapaul/Library/CloudStorage/OneDrive-Personal/Desktop/2025-RTT-23/JAVA-PRACTICE/BRO-CODE/brocode/src/lesson19customexcptions/PracticeProject/documentation/account-balance.txt");

        } else {

            throw new UnsupportedOperationException("OS not supported: " + osName);

        }


        if (myFile.exists()){

            System.out.println("File exists: " + myFile.getName());

        }

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

                    case DEPOSIT -> makeDeposit(bankAccount, sc, myFile);
                    case WITHDRAW -> makeWithdrawal(bankAccount, sc, myFile);
                    case TRANSFER -> makeTransfer(bankAccount, sc, myFile);
                    case EXIT -> System.exit(0);

                }

            } catch (IllegalArgumentException e) {

                System.err.println("Invalid option. You can DEPOSIT, WITHDRAW, or EXIT app.");
                System.out.println();

            }

        }

    }

    private static void makeDeposit(BankAccount bankAccount, Scanner sc, File file) {

        double initialDeposit;

        try {

            System.out.println("Enter deposit amount: ");
            initialDeposit = sc.nextDouble();
            sc.nextLine();

            bankAccount.deposit(initialDeposit);

            try (FileWriter myWriter = new FileWriter(file, true)) {

                myWriter.write("| Deposit | $" + initialDeposit + " | Balance: $" + bankAccount.getBalance() + " |\n");

            }

        }catch (InvalidDepositException e) {

            System.err.println("Error occurred!!!\n" + e.getMessage());

        }catch (IOException e){

            throw new RuntimeException(e);

        }

    }

    private static void makeWithdrawal(BankAccount bankAccount, Scanner sc, File file) {

        double initialWithdrawal;

        try{

            System.out.println("Enter withdrawal amount: ");
            initialWithdrawal = sc.nextDouble();
            sc.nextLine();

            bankAccount.withdraw(initialWithdrawal);

            try (FileWriter myWriter = new FileWriter(file, true)) {

                myWriter.write("| Withdrawal | $" + initialWithdrawal + " | Balance: $" + bankAccount.getBalance() + " |\n");

            }

        }catch (InsufficientFundsException e){

            System.out.println("Error occurred!!!\n" + e.getMessage());

        }catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

    private static void makeTransfer(BankAccount bankAccount, Scanner sc, File file) {

        try {

            System.out.println("Enter amount to transfer: ");
            double initialTransfer = sc.nextDouble();
            sc.nextLine();

            bankAccount.transfer(initialTransfer);

            try (FileWriter myWriter = new FileWriter(file, true)) {

                myWriter.write("| Transfer | $" + initialTransfer + " | Balance: $" + bankAccount.getBalance() + " |\n");

            }

        }catch(InvalidTransferException e){

            System.err.println("Error occurred!!!\n" + e.getMessage());

        }catch (IOException e){

            throw new RuntimeException(e);

        }

    }

}
