import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String holderName;
    private double balance;
    private ArrayList<String> transactions;

    // Constructor
    public Account(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        transactions.add("Account created with balance: " + initialBalance);
    }

    // Deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: " + amount + " | Balance: " + balance);
            System.out.println("✅ Deposited " + amount);
        } else {
            System.out.println("⚠ Invalid deposit amount!");
        }
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: " + amount + " | Balance: " + balance);
            System.out.println("✅ Withdrawn " + amount);
        } else {
            System.out.println("⚠ Insufficient funds or invalid amount!");
        }
    }

    // Show balance
    public void showBalance() {
        System.out.println("💰 Current Balance: " + balance);
    }

    // Show transactions
    public void showTransactions() {
        System.out.println("\n📜 Transaction History:");
        for (String t : transactions) {
            System.out.println("- " + t);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }
}

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create account first
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        System.out.print("Enter Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        Account account = new Account(accNo, name, balance);

        int choice;
        do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Balance");
            System.out.println("4. Show Transactions");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter deposit amount: ");
                    double depositAmt = sc.nextDouble();
                    account.deposit(depositAmt);
                }
                case 2 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmt = sc.nextDouble();
                    account.withdraw(withdrawAmt);
                }
                case 3 -> account.showBalance();
                case 4 -> account.showTransactions();
                case 5 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("⚠ Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
