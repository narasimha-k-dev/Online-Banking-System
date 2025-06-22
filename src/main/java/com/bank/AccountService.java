package com.bank;

import java.sql.*;

public class AccountService {
    private final Connection conn;

    public AccountService(Connection conn) {
        this.conn = conn;
    }

    public void checkBalance(int userId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT balance FROM users WHERE id = ?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Your balance: ₹" + rs.getDouble("balance"));
        }
    }

    public void transferFunds(int senderId, String recipientName, double amount) throws SQLException {
        conn.setAutoCommit(false);
        try {
            PreparedStatement senderStmt = conn.prepareStatement("SELECT balance FROM users WHERE id = ?");
            senderStmt.setInt(1, senderId);
            ResultSet rsSender = senderStmt.executeQuery();

            if (!rsSender.next() || rsSender.getDouble("balance") < amount) {
                System.out.println("Insufficient balance");
                conn.rollback();
                return;
            }

            PreparedStatement receiverStmt = conn.prepareStatement("SELECT id FROM users WHERE name = ?");
            receiverStmt.setString(1, recipientName);
            ResultSet rsReceiver = receiverStmt.executeQuery();
            if (!rsReceiver.next()) {
                System.out.println("Recipient not found");
                conn.rollback();
                return;
            }
            int receiverId = rsReceiver.getInt("id");

            PreparedStatement withdraw = conn.prepareStatement("UPDATE users SET balance = balance - ? WHERE id = ?");
            withdraw.setDouble(1, amount);
            withdraw.setInt(2, senderId);
            withdraw.executeUpdate();

            PreparedStatement deposit = conn.prepareStatement("UPDATE users SET balance = balance + ? WHERE id = ?");
            deposit.setDouble(1, amount);
            deposit.setInt(2, receiverId);
            deposit.executeUpdate();

            PreparedStatement logTx = conn.prepareStatement("INSERT INTO transactions (sender_id, receiver_id, amount) VALUES (?, ?, ?)");
            logTx.setInt(1, senderId);
            logTx.setInt(2, receiverId);
            logTx.setDouble(3, amount);
            logTx.executeUpdate();

            conn.commit();
            System.out.println("Transfer successful");
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}

