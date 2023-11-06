package org.example.Database;

import org.example.models.Role;
import org.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDb {
    public String INSERT_USER = "INSERT INTO users (id,username,email,password) VALUES (?,?,?,?);";

    private String SELECT_USER = "SELECT id, email, username, password FROM users WHERE email=? and password=?";

    private static Connection con = DbConnection.getConection();

    public User loginUser(String email, String password) {
        User user = null;
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_USER);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                if (user.getEmail().equals("admin@admin.com"))
                    user.setRole(Role.ADMIN);
                else
                    user.setRole(Role.USER);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public void registerUser(int id, String name, String email, String password) {
        try {
            PreparedStatement ps = con.prepareStatement(INSERT_USER);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3,email);
            ps.setString(4,password);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
