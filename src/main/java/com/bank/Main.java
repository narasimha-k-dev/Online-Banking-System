package com.bank;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner sc = new Scanner(System.in);
            UserService userService = new UserService(conn);
            AccountService accountService = new AccountService(conn);
            TransactionService transactionService = new TransactionService(conn);

            while (true) {
                System.out.println("\n=== Online Banking System ===");
                System.out.println("1. Register\n2. Login\n3. Exit");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter password: ");
                        String password = sc.nextLine();
                        userService.register(name, password);
                        break;
                    case 2:
                        System.out.print("Enter name: ");
                        String loginName = sc.nextLine();
                        System.out.print("Enter password: ");
                        String loginPass = sc.nextLine();
                        int userId = userService.login(loginName, loginPass);
                        if (userId != -1) {
                            System.out.println("Login successful\nWelcome, " + loginName);
                            while (true) {
                                System.out.println("\n1. Check Balance\n2. Transfer Funds\n3. Transaction History\n4. Logout");
                                int opt = sc.nextInt();
                                sc.nextLine();
                                switch (opt) {
                                    case 1: accountService.checkBalance(userId); break;
                                    case 2:
                                        System.out.print("Enter recipient name: ");
                                        String rec = sc.nextLine();
                                        System.out.print("Enter amount: ");
                                        double amt = sc.nextDouble();
                                        sc.nextLine();
                                        accountService.transferFunds(userId, rec, amt);
                                        break;
                                    case 3: transactionService.viewTransactions(userId); break;
                                    case 4: System.out.println("Logged out"); break;
                                    default: System.out.println("Invalid");
                                }
                                if (opt == 4) break;
                            }
                        } else {
                            System.out.println("Invalid credentials");
                        }
                        break;
                    case 3:
                        System.exit(0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

