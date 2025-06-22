package com.bank;

import java.sql.*;

public class TransactionService {
    private final Connection conn;

    public TransactionService(Connection conn) {
        this.conn = conn;
    }

    public void viewTransactions(int userId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "SELECT u.name AS receiver, t.amount, t.timestamp FROM transactions t JOIN users u ON t.receiver_id = u.id WHERE t.sender_id = ? ORDER BY t.timestamp DESC"
        );
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        System.out.println("\n--- Transaction History ---");
        while (rs.next()) {
            System.out.printf("To: %s | Amount: ₹%.2f | On: %s\n",
                    rs.getString("receiver"),
                    rs.getDouble("amount"),
                    rs.getTimestamp("timestamp").toString());
        }
    }
}
