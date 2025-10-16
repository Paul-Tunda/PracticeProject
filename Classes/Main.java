package lesson19customexcptions.PracticeProject.Classes;

import lesson19customexcptions.PracticeProject.exceptions.InsufficientFundsException;
import lesson19customexcptions.PracticeProject.exceptions.InvalidDepositException;
import lesson19customexcptions.PracticeProject.exceptions.InvalidTransferException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

enum BankOption{

    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT

}

public class Main {

    public static void main(String[] args) {

        Date date = new Date();
        String[] days = {"Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat"};
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int year = date.getYear() + 1900;

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

                    case DEPOSIT -> makeDeposit(bankAccount, sc, myFile, date, year, days, months);
                    case WITHDRAW -> makeWithdrawal(bankAccount, sc, myFile, date, year, days, months);
                    case TRANSFER -> makeTransfer(bankAccount, sc, myFile, date, year, days, months);
                    case EXIT -> System.exit(0);

                }

            } catch (IllegalArgumentException e) {

                System.err.println("Invalid option. You can DEPOSIT, WITHDRAW, or EXIT app.");
                System.out.println();

            }

        }

    }

    private static void makeDeposit(BankAccount bankAccount, Scanner sc, File file, Date date, int year, String[] days, String[] months) {

        double initialDeposit;

        try {

            String[] reasons = {"ATM Deposit", "Payment from work", "Money received"};

            System.out.println("Enter deposit amount: ");
            initialDeposit = sc.nextDouble();
            sc.nextLine();
            for (int i = 0; i < reasons.length; i++) {

                System.out.println((i + 1) + " - " + reasons[i]);

            }

            System.out.println("Enter reason (select number): ");
            int reason = sc.nextInt();
            sc.nextLine();

            bankAccount.deposit(initialDeposit);

            int day = date.getDate();
            int month = date.getMonth();

            String dateStr = days[day] + " " + months[month] + " " + date.getDate() + ", " + year;
            String description = reasons[reason - 1];
            String type = "Deposit";
            String amountStr = String.valueOf(initialDeposit);
            String balanceStr = String.valueOf(bankAccount.getBalance());

            try (FileWriter myWriter = new FileWriter(file, true)) {

                myWriter.write("| " + padRight(dateStr, 30) + " | " + padRight(description, 26) + " | " + padRight(type, 22) + " | " + padRight(amountStr, 21) + " | " + padRight(balanceStr, 21) + " |\n");

            }

        } catch (InvalidDepositException e) {

            System.err.println("Error occurred!!!\n" + e.getMessage());

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }


    private static void makeWithdrawal(BankAccount bankAccount, Scanner sc, File file, Date date, int year, String[] days, String[] months) {

        double initialWithdrawal;

        try {

            // Possible withdrawal reasons
            String[] withdrawalReasons = {"ATM Withdrawal", "Bill Payment", "Cash Withdrawal", "Online Transfer"};

            System.out.println("Enter withdrawal amount: ");
            initialWithdrawal = sc.nextDouble();
            sc.nextLine();

            // Show withdrawal reasons
            for (int i = 0; i < withdrawalReasons.length; i++) {
                System.out.println((i + 1) + " - " + withdrawalReasons[i]);
            }

            System.out.println("Enter reason (select number): ");
            int reason = sc.nextInt();
            sc.nextLine();

            bankAccount.withdraw(initialWithdrawal);

            int day = date.getDate();
            int month = date.getMonth();

            String dateStr = days[day] + " " + months[month] + " " + date.getDate() + ", " + year;
            String description = withdrawalReasons[reason - 1];
            String type = "Withdrawal";
            String amountStr = String.valueOf(initialWithdrawal);
            String balanceStr = String.valueOf(bankAccount.getBalance());

            try (FileWriter myWriter = new FileWriter(file, true)) {
                myWriter.write("| " + padRight(dateStr, 30) + " | " + padRight(description, 26) + " | " + padRight(type, 22) + " | " + padRight(amountStr, 21) + " | " + padRight(balanceStr, 21) + " |\n");
            }

        } catch (InsufficientFundsException e) {
            System.out.println("Error occurred!!!\n" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void makeTransfer(BankAccount bankAccount, Scanner sc, File file, Date date, int year, String[] days, String[] months) {

        try {

            // Possible transfer reasons
            String[] transferReasons = {"To Savings Account", "To Friend/Family", "To Investment Account", "Loan Payment"};

            System.out.println("Enter amount to transfer: ");
            double initialTransfer = sc.nextDouble();
            sc.nextLine();

            // Show transfer reasons
            for (int i = 0; i < transferReasons.length; i++) {
                System.out.println((i + 1) + " - " + transferReasons[i]);
            }

            System.out.println("Enter reason (select number): ");
            int reason = sc.nextInt();
            sc.nextLine();

            bankAccount.transfer(initialTransfer);

            int day = date.getDate();
            int month = date.getMonth();

            String dateStr = days[day] + " " + months[month] + " " + date.getDate() + ", " + year;
            String description = transferReasons[reason - 1];
            String type = "Transfer";
            String amountStr = String.valueOf(initialTransfer);
            String balanceStr = String.valueOf(bankAccount.getBalance());

            try (FileWriter myWriter = new FileWriter(file, true)) {
                myWriter.write("| " + padRight(dateStr, 30) + " | " + padRight(description, 26) +  " | " + padRight(type, 22) + " | " + padRight(amountStr, 21) + " | " + padRight(balanceStr, 21) + " |\n");
            }

        } catch (InvalidTransferException e) {
            System.err.println("Error occurred!!!\n" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static String padRight(String text, int length) {

        if (text == null) text = "";

        if (text.length() > length) {

            return text.substring(0, length);

        }

        return String.format("%-" + length + "s", text);

    }


}
