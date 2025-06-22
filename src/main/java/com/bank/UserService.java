package com.bank;

import java.sql.*;

public class UserService {
    private Connection conn;

    public UserService(Connection conn) {
        this.conn = conn;
    }

    public void register(String name, String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO users (name, password, balance) VALUES (?, ?, ?)");
        ps.setString(1, name);
        ps.setString(2, password);
        ps.setDouble(3, 1000.0);
        ps.executeUpdate();
    }

    public int login(String name, String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "SELECT id FROM users WHERE name = ? AND password = ?");
        ps.setString(1, name);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getInt("id") : -1;
    }
}