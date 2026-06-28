package com.nhlaks;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Entry point for the Simple Banking Application.
 *
 * <p>Drives a console-based menu that lets users create accounts, deposit,
 * withdraw, and check balances interactively.</p>
 *
 * @author Nhlalala
 * @version 1.0
 */
public class Main {

    private static final BankingService service = new BankingService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("   Welcome to NhlaBank – Simple Banking App  ");
        System.out.println("==============================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> handleCreateAccount();
                case "2" -> handleDeposit();
                case "3" -> handleWithdraw();
                case "4" -> handleCheckBalance();
                case "5" -> {
                    System.out.println("\nThank you for banking with NhlaBank. Goodbye!");
                    running = false;
                }
                default  -> System.out.println("\n[!] Invalid option. Please choose 1-5.\n");
            }
        }
        scanner.close();
    }

    // -----------------------------------------------------------------------
    // Menu handlers
    // -----------------------------------------------------------------------

    private static void handleCreateAccount() {
        System.out.println("\n--- Create Account ---");
        System.out.print("Enter account number : ");
        String number = scanner.nextLine().trim();

        System.out.print("Enter account holder : ");
        String holder = scanner.nextLine().trim();

        System.out.print("Opening balance (R)  : ");
        BigDecimal opening = readAmount();

        try {
            BankAccount account = service.createAccount(number, holder, opening);
            System.out.printf("%nAccount created successfully!%n%s%n%n", account);
        } catch (IllegalArgumentException e) {
            System.out.println("\n[!] " + e.getMessage() + "\n");
        }
    }

    private static void handleDeposit() {
        System.out.println("\n--- Deposit ---");
        System.out.print("Account number : ");
        String number = scanner.nextLine().trim();

        System.out.print("Amount (R)     : ");
        BigDecimal amount = readAmount();

        try {
            service.deposit(number, amount);
            System.out.printf("%nDeposit successful! New balance: R%.2f%n%n",
                    service.getBalance(number));
        } catch (IllegalArgumentException e) {
            System.out.println("\n[!] " + e.getMessage() + "\n");
        }
    }

    private static void handleWithdraw() {
        System.out.println("\n--- Withdraw ---");
        System.out.print("Account number : ");
        String number = scanner.nextLine().trim();

        System.out.print("Amount (R)     : ");
        BigDecimal amount = readAmount();

        try {
            service.withdraw(number, amount);
            System.out.printf("%nWithdrawal successful! New balance: R%.2f%n%n",
                    service.getBalance(number));
        } catch (InsufficientFundsException e) {
            System.out.println("\n[!] " + e.getMessage() + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println("\n[!] " + e.getMessage() + "\n");
        }
    }

    private static void handleCheckBalance() {
        System.out.println("\n--- Check Balance ---");
        System.out.print("Account number : ");
        String number = scanner.nextLine().trim();

        try {
            BigDecimal balance = service.getBalance(number);
            System.out.printf("%nCurrent balance for account %s: R%.2f%n%n", number, balance);
        } catch (IllegalArgumentException e) {
            System.out.println("\n[!] " + e.getMessage() + "\n");
        }
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private static void printMenu() {
        System.out.println("----------------------------------------------");
        System.out.println(" 1. Create Account");
        System.out.println(" 2. Deposit");
        System.out.println(" 3. Withdraw");
        System.out.println(" 4. Check Balance");
        System.out.println(" 5. Exit");
        System.out.println("----------------------------------------------");
        System.out.print("Select option: ");
    }

    /**
     * Reads a positive {@link BigDecimal} from stdin, re-prompting on invalid input.
     *
     * @return a valid positive amount
     */
    private static BigDecimal readAmount() {
        while (true) {
            try {
                BigDecimal amount = new BigDecimal(scanner.nextLine().trim());
                if (amount.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.print("Amount cannot be negative. Try again: ");
                    continue;
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.print("Invalid amount. Enter a number: ");
            }
        }
    }
}
