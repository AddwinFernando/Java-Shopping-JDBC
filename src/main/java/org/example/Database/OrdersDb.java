package org.example.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDb {
    private static Connection con = DbConnection.getConection();

    private static String INSERT_ORDER = "INSERT INTO orders (id, userId, name, count) SELECT id, userId, name, count FROM cart WHERE userId=? AND isCheckout=0;";

    private static String UPDATE_CHECKOUT = "UPDATE cart SET isCheckout=1 WHERE userId = ? and isCheckout=0;";

    private static String SELECT_ORDERS = "SELECT id, name, count, status FROM orders WHERE userId=?;";

    public void checkout(int userId){
        try {
            PreparedStatement ps = con.prepareStatement(INSERT_ORDER);
            ps.setInt(1, userId);
            ps.executeUpdate();
            PreparedStatement ps2 = con.prepareStatement(UPDATE_CHECKOUT);
            ps2.setInt(1,userId);
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getOrders(int userId){
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_ORDERS);
            ps.setInt(1,userId);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


}
